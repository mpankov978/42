package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.dto.AssetDto;
import ru.hackandchallenge.investadvisor.dto.InvestPortfolioDto;
import ru.hackandchallenge.investadvisor.dto.operations.OperationHistoryDto;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.exception.BalanceOperationException;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class InvestPortfolioService {

    private final InvestPortfoliosRepository investPortfoliosRepository;
    private final AssetsService assetsService;
    private final OperationHistoryService operationHistoryService;

    @Transactional
    public void enrollBalance(Long clientId, String stringAmount) {
        InvestPortfolio portfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        BigDecimal amount = new BigDecimal(stringAmount);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceOperationException("Попытка зачисления отрицательной суммы на счет");
        } else {
            portfolio.addBalance(amount);
            operationHistoryService.logEnrollOperation(clientId, amount);
        }
        investPortfoliosRepository.save(portfolio);
    }

    public InvestPortfolioDto getInfo(Long clientId) {
        assetsService.updateAllAssetsData();

        InvestPortfolio investPortfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        List<AssetDto> assets = investPortfolio.getPortfolioAssets()
                .stream()
                .map(portfolioAsset -> {
                    Asset entity = portfolioAsset.getAsset();
                    return new AssetDto(entity.getId(), entity.getCode(), entity.getFullName(), entity.getCost(), portfolioAsset.getCount(), entity.getLastUpdated());
                }).toList();

        BigDecimal assetSum = assets.stream()
                .map(assetDto -> assetDto.getCost().multiply(BigDecimal.valueOf(assetDto.getAmount())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalSum = investPortfolio.getBalance().add(assetSum);

        return new InvestPortfolioDto(investPortfolio.getId(), investPortfolio.getClientId(), assets, investPortfolio.getBalance(), assetSum, totalSum);
    }

    public BigDecimal getTotalInvestments(Long clientId, Integer days, boolean all) {
        return operationHistoryService.getHistory(clientId, days, all).stream()
                .filter(v -> OperationHistory.OperationType.ENROLL.equals(v.operationType()))
                .map(OperationHistoryDto::assetCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
