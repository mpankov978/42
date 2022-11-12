package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.collectors.quotes.TwelveDataCollector;
import ru.hackandchallenge.investadvisor.dto.AssetDto;
import ru.hackandchallenge.investadvisor.dto.AssetExtendedDto;
import ru.hackandchallenge.investadvisor.dto.operations.OperationAssetDto;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataDto;
import ru.hackandchallenge.investadvisor.dto.quotes.TwelveDataValueDto;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.entity.PortfolioAsset;
import ru.hackandchallenge.investadvisor.exception.AssetOperationException;
import ru.hackandchallenge.investadvisor.exception.AssetUpdateException;
import ru.hackandchallenge.investadvisor.exception.BalanceOperationException;
import ru.hackandchallenge.investadvisor.repository.AssetsRepository;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ru.hackandchallenge.investadvisor.controller.InvestingNewsController.ITEMS_MAP;

@Service
@AllArgsConstructor
public class AssetsService {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final OperationHistoryService historyService;
    private final AssetsRepository repository;
    private final InvestPortfoliosRepository investPortfoliosRepository;
    private final TwelveDataCollector twelveDataCollector;

    @Transactional
    public void processBuyOperation(Long clientId, OperationAssetDto operationAssetDto) {
        InvestPortfolio investPortfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        Optional<PortfolioAsset> optionalPortfolioAsset = investPortfolio.getPortfolioAssets()
                .stream()
                .filter(asset -> asset.getAsset().getCode().equalsIgnoreCase(operationAssetDto.getCode()))
                .findFirst();
        optionalPortfolioAsset.ifPresentOrElse(pa -> updateAssetData(pa.getAsset()), this::updateAllAssetsData);

        if (optionalPortfolioAsset.isPresent()) {
            PortfolioAsset existingPortfolioAsset = optionalPortfolioAsset.get();
            writeOffMoney(investPortfolio, existingPortfolioAsset.getAsset().getCost(), operationAssetDto.getAmount());

            existingPortfolioAsset.addCount(operationAssetDto.getAmount());
            historyService.logAssetOperation(clientId, OperationHistory.OperationType.BUY, existingPortfolioAsset.getAsset(), operationAssetDto.getAmount());
        } else {
            var buyAsset = repository.findAssetByCode(operationAssetDto.getCode());
            writeOffMoney(investPortfolio, buyAsset.getCost(), operationAssetDto.getAmount());

            var newPortfolioAsset = new PortfolioAsset(operationAssetDto.getAmount(), investPortfolio, buyAsset);
            investPortfolio.getPortfolioAssets().add(newPortfolioAsset);
            historyService.logAssetOperation(clientId, OperationHistory.OperationType.BUY, newPortfolioAsset.getAsset(), operationAssetDto.getAmount());
        }

        investPortfoliosRepository.save(investPortfolio);
    }

    @Transactional
    public void processSellOperation(Long clientId, OperationAssetDto operationAssetDto) {
        InvestPortfolio investPortfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        Optional<PortfolioAsset> portfolioAsset = investPortfolio.getPortfolioAssets()
                .stream()
                .filter(asset -> asset.getAsset().getCode().equalsIgnoreCase(operationAssetDto.getCode()))
                .findFirst();
        portfolioAsset.ifPresentOrElse(pa -> updateAssetData(pa.getAsset()), this::updateAllAssetsData);

        if (portfolioAsset.isPresent()) {
            PortfolioAsset existingPortfolioAsset = portfolioAsset.get();
            enrollMoney(investPortfolio, existingPortfolioAsset.getAsset().getCost(), operationAssetDto.getAmount());

            reduceAssets(operationAssetDto, investPortfolio, portfolioAsset);
            historyService.logAssetOperation(clientId, OperationHistory.OperationType.SELL, existingPortfolioAsset.getAsset(), operationAssetDto.getAmount());
        } else {
            throw new AssetOperationException("Актив не существует в инвестиционном портфеле");
        }
        investPortfoliosRepository.save(investPortfolio);
    }

    private static void reduceAssets(OperationAssetDto operationAssetDto, InvestPortfolio investPortfolio, Optional<PortfolioAsset> portfolioAsset) {
        if (portfolioAsset.get().getCount() - operationAssetDto.getAmount() > 0) {
            portfolioAsset.get().reduceCount(operationAssetDto.getAmount());
        } else if (portfolioAsset.get().getCount() - operationAssetDto.getAmount() == 0) {
            investPortfolio.getPortfolioAssets().remove(portfolioAsset.get());
        } else if (portfolioAsset.get().getCount() - operationAssetDto.getAmount() < 0) {
            throw new AssetOperationException("Количество продаваемых активов превышает количество существующих в инвестиционном портфеле");
        }
    }

    public void updateAssetData(Asset asset) {
        Set<TwelveDataDto> twelveDataDtos = twelveDataCollector.getItems(Collections.singleton(asset.getCode()), "1min");
        TwelveDataValueDto lastAssetInfo = getForAsset(asset, twelveDataDtos);
        updateAssetData(asset, lastAssetInfo);
    }

    private void updateAssetData(Asset asset, TwelveDataValueDto lastAssetInfo) {
        asset.setCost(lastAssetInfo.getClose());
        asset.setLastUpdated(lastAssetInfo.getDatetime().split(" ").length > 1
                ? LocalDateTime.parse(lastAssetInfo.getDatetime(), MINUTE_FORMATTER)
                : LocalDate.parse(lastAssetInfo.getDatetime(), DAY_FORMATTER).atStartOfDay());
        repository.save(asset);
    }

    public void updateAllAssetsData() {
        List<Asset> assets = repository.findAll();
        Set<TwelveDataDto> twelveDataDtos = twelveDataCollector.getItems(ITEMS_MAP.keySet(), "1min");
        for (Asset asset : assets) {
            updateAssetData(asset, getForAsset(asset, twelveDataDtos));
        }
    }

    public List<AssetDto> getAssets() {
        updateAllAssetsData();

        return repository.findAll()
                .stream()
                .map(asset -> new AssetDto(asset.getId(), asset.getCode(), asset.getFullName(),
                        asset.getCost(), null, asset.getLastUpdated()))
                .toList();
    }

    public AssetExtendedDto getAssetByCode(String code) {
        Asset asset = repository.findAssetByCode(code);

        List<TwelveDataValueDto> twelveDataValueDtos = twelveDataCollector.getItems(Collections.singleton(asset.getCode()), "1day")
                .stream()
                .filter(dto -> dto.getMeta().getSymbol().equalsIgnoreCase(asset.getCode()))
                .flatMap(v -> v.getValues().stream())
                .toList();

        if (!twelveDataValueDtos.isEmpty()) {
            updateAssetData(asset, twelveDataValueDtos.get(0));
            var dto = new AssetExtendedDto();
            dto.setId(asset.getId());
            dto.setCode(asset.getCode());
            dto.setFullName(asset.getFullName());
            dto.setCost(asset.getCost());
            dto.setLastUpdated(asset.getLastUpdated());
            dto.setQuotes(twelveDataValueDtos);

            return dto;
        } else {
            throw new AssetUpdateException("Не получены свежие данные об активе");
        }
    }

    private static void writeOffMoney(InvestPortfolio investPortfolio, BigDecimal cost, Integer amount) {
        BigDecimal sum = cost.multiply(BigDecimal.valueOf(amount));
        if (investPortfolio.getBalance().subtract(sum).compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceOperationException("Недостаточно средств на счету");
        } else {
            investPortfolio.writeOffBalance(sum);
        }
    }

    private static void enrollMoney(InvestPortfolio investPortfolio, BigDecimal cost, Integer amount) {
        BigDecimal sum = cost.multiply(BigDecimal.valueOf(amount));
        investPortfolio.addBalance(sum);
    }

    private TwelveDataValueDto getForAsset(Asset asset, Collection<TwelveDataDto> from) {
        return from
                .stream()
                .filter(dto -> dto.getMeta().getSymbol().equalsIgnoreCase(asset.getCode()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new)
                .getValues()
                .get(0);
    }
}
