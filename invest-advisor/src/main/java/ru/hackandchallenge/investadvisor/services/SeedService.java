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
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

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
        investPortfolio.setBalance(new BigDecimal("4525"));
        investPortfolio.setCreated(LocalDateTime.now().minusDays(7));
        investPortfoliosRepository.save(investPortfolio);
    }

    private void seedAsset() {
        if (!assetsRepository.findAll().isEmpty()) {
            return;
        }
        var aapl = new Asset("aapl", "Apple Inc (AAPL)", new BigDecimal("100"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var ibm = new Asset("ibm", "International Business Machines (IBM)", new BigDecimal("110"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var ozon = new Asset("ozon", "Ozon Holdings PLC (OZON)", new BigDecimal("140"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var yndx = new Asset("yndx", "Яндекс Н.В. (YNDX)", new BigDecimal("180"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var hhr = new Asset("hhr", "HeadHunter Group PLC ADR (HHR)", new BigDecimal("120"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        assetsRepository.saveAll(List.of(aapl, ibm, ozon, yndx, hhr));
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

        var operationEnroll = new OperationHistory();
        operationEnroll.setClientId(100333L);
        operationEnroll.setOperationType(OperationType.ENROLL);
        operationEnroll.setCost(new BigDecimal("5000"));
        operationEnroll.setOperationTime(LocalDateTime.now().minusDays(2));

        var operationBuy = new OperationHistory();
        operationBuy.setClientId(100333L);
        operationBuy.setOperationType(OperationType.BUY);
        operationBuy.setAsset(assetsRepository.findById(1L).get());
        operationBuy.setCost(new BigDecimal("95"));
        operationBuy.setAssetAmount(1);
        operationBuy.setOperationTime(LocalDateTime.now().minusDays(1));
        operationHistoryRepository.saveAll(asList(operationEnroll, operationBuy));
    }
}
