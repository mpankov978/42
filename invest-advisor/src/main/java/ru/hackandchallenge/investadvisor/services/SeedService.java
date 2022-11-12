package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.entity.Asset;
import ru.hackandchallenge.investadvisor.entity.Asset.AssetType;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.entity.OperationHistory;
import ru.hackandchallenge.investadvisor.entity.OperationHistory.OperationType;
import ru.hackandchallenge.investadvisor.entity.PortfolioAsset;
import ru.hackandchallenge.investadvisor.repository.AssetsRepository;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;
import ru.hackandchallenge.investadvisor.repository.OperationHistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class SeedService {

    private final AssetsRepository assetsRepository;
    private final InvestPortfoliosRepository investPortfoliosRepository;
    private final OperationHistoryRepository operationHistoryRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedInvestPortfolio();
        seedAsset();
        seedInvestPortfolioAssets();
        seedHistoryOperation();
    }

    private void seedInvestPortfolio() {
        if (!investPortfoliosRepository.findAll().isEmpty()) {
            return;
        }
        var investPortfolio = new InvestPortfolio();
        investPortfolio.setClientId(100333L);
        investPortfolio.setBalance(new BigDecimal("5000"));
        investPortfolio.setCreated(LocalDateTime.now().minusDays(7));
        investPortfoliosRepository.save(investPortfolio);
    }

    private void seedAsset() {
        if (!assetsRepository.findAll().isEmpty()) {
            return;
        }
        var asset = new Asset();
        asset.setCode("aapl");
        asset.setFullName("APPLE");
        asset.setCost(new BigDecimal("100"));
        asset.setType(AssetType.STOCKS);
        asset.setLastUpdated(LocalDateTime.now());
        assetsRepository.save(asset);
    }

    private void seedInvestPortfolioAssets() {
        InvestPortfolio portfolio = investPortfoliosRepository.findInvestPortfolioByClientId(100333L);
        if (!portfolio.getPortfolioAssets().isEmpty()) {
            return;
        }
        PortfolioAsset portfolioAsset = new PortfolioAsset();
        portfolioAsset.setCount(5);
        portfolioAsset.setInvestPortfolio(portfolio);
        portfolioAsset.setAsset(assetsRepository.findById(1L).get());
        Set<PortfolioAsset> portfolioAssets = new HashSet<>();
        portfolioAssets.add(portfolioAsset);
        portfolio.setPortfolioAssets(portfolioAssets);
        investPortfoliosRepository.save(portfolio);
    }

    private void seedHistoryOperation() {
        if (!operationHistoryRepository.findAll().isEmpty()) {
            return;
        }
        var operation = new OperationHistory();
        operation.setClientId(100333L);
        operation.setOperationType(OperationType.BUY);
        operation.setAsset(assetsRepository.findById(1L).get());
        operation.setAssetCost(new BigDecimal("95"));
        operation.setAssetAmount(1);
        operation.setOperationTime(LocalDateTime.now().minusDays(1));
        operationHistoryRepository.save(operation);
    }
}
