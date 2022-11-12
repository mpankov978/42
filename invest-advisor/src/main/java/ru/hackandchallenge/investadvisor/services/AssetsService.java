package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.operations.OperationAssetDto;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.PortfolioAsset;
import ru.hackandchallenge.investadvisor.exception.AssetOperationException;
import ru.hackandchallenge.investadvisor.exception.NotEnoughBalanceException;
import ru.hackandchallenge.investadvisor.repository.AssetsRepository;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssetsService {

    private final AssetsRepository repository;
    private final InvestPortfoliosRepository investPortfoliosRepository;

    public void processBuyOperation(Long clientId, OperationAssetDto operationAssetDto) {
        InvestPortfolio investPortfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        Optional<PortfolioAsset> portfolioAsset = investPortfolio.getPortfolioAssets()
                .stream()
                .filter(asset -> asset.getAsset().getCode().equalsIgnoreCase(operationAssetDto.getCode()))
                .findFirst();
        portfolioAsset.ifPresentOrElse(pa -> updateAssetData(pa.getAsset()), this::updateAllAssetsData);

        if (portfolioAsset.isPresent()) {
            writeOffMoney(investPortfolio, portfolioAsset.get().getAsset().getCost(), operationAssetDto.getAmount());

            portfolioAsset.get().addCount(operationAssetDto.getAmount());
        } else {
            Asset buyAsset = repository.findAssetByCode(operationAssetDto.getCode());
            writeOffMoney(investPortfolio, buyAsset.getCost(), operationAssetDto.getAmount());

            PortfolioAsset newPortfolioAsset = new PortfolioAsset(operationAssetDto.getAmount(), investPortfolio, buyAsset);
            investPortfolio.getPortfolioAssets().add(newPortfolioAsset);
        }
        investPortfoliosRepository.save(investPortfolio);
    }

    public void processSellOperation(Long clientId, OperationAssetDto operationAssetDto) {
        InvestPortfolio investPortfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        Optional<PortfolioAsset> portfolioAsset = investPortfolio.getPortfolioAssets()
                .stream()
                .filter(asset -> asset.getAsset().getCode().equalsIgnoreCase(operationAssetDto.getCode()))
                .findFirst();
        portfolioAsset.ifPresentOrElse(pa -> updateAssetData(pa.getAsset()), this::updateAllAssetsData);

        if (portfolioAsset.isPresent()) {
            enrollMoney(investPortfolio, portfolioAsset.get().getAsset().getCost(), operationAssetDto.getAmount());

            reduceAssets(operationAssetDto, investPortfolio, portfolioAsset);
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

    private static void writeOffMoney(InvestPortfolio investPortfolio, BigDecimal cost, Integer amount) {
        BigDecimal sum = cost.multiply(BigDecimal.valueOf(amount));
        if (investPortfolio.getBalance().subtract(sum).compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughBalanceException();
        } else {
            investPortfolio.writeOffBalance(sum);
        }
    }

    private static void enrollMoney(InvestPortfolio investPortfolio, BigDecimal cost, Integer amount) {
        BigDecimal sum = cost.multiply(BigDecimal.valueOf(amount));
        investPortfolio.addBalance(sum);
    }

    public void updateAssetData(Asset oldAsset) {
        // todo: логика получения и сохранения актуальной инфы об активе
        Asset newAsset = repository.findAssetByCode(oldAsset.getCode());
        oldAsset.setFullName(newAsset.getFullName());
        oldAsset.setCost(newAsset.getCost());
        oldAsset.setLastUpdated(newAsset.getLastUpdated());
        repository.save(newAsset);
    }

    private void updateAllAssetsData() {
        // todo: логика получения и сохранения актуальной инфы об всех активах
    }


}
