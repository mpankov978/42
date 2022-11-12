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
        var aapl = new Asset("aapl", "Apple Inc (AAPL)", new BigDecimal("100"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var ibm = new Asset("ibm", "International Business Machines (IBM)", new BigDecimal("110"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var hhr = new Asset("hhr", "HeadHunter Group PLC ADR (HHR)", new BigDecimal("120"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var hydr = new Asset("hydr", "ПАО «РусГидро» (HYDR)", new BigDecimal("130"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var ozon = new Asset("ozon", "Ozon Holdings PLC (OZON)", new BigDecimal("140"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var tatn = new Asset("tatn", "ОАО Татнефть (TATN)", new BigDecimal("150"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var chmf = new Asset("chmf", "ОАО Северсталь (CHMF)", new BigDecimal("160"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var mgnt = new Asset("mgnt", "ОАО Магнит (MGNT)", new BigDecimal("170"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var yndx = new Asset("yndx", "Яндекс Н.В. (YNDX)", new BigDecimal("180"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var aflt = new Asset("aflt", "ОАО Аэрофлот (AFLT)", new BigDecimal("190"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var afks = new Asset("afks", "ОАО АФК Система (AFKS)", new BigDecimal("200"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var rtkm = new Asset("rtkm", "Ростелеком ПАО (RTKM)", new BigDecimal("210"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var nvtk = new Asset("nvtk", "ОАО НОВАТЭК (NVTK)", new BigDecimal("220"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var pikk = new Asset("pikk", "ОАО Группа Компаний ПИК (PIKK)", new BigDecimal("230"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var nlmk = new Asset("nlmk", "Новолипецкий металлургический комбинат НЛМК (nlmk)", new BigDecimal("240"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        var dsky = new Asset("dsky", "Детский мир ПАО (DSKY)", new BigDecimal("250"), AssetType.STOCKS, LocalDateTime.now(), new HashSet<>());
        assetsRepository.saveAll(List.of(aapl, ibm, hhr, hydr, ozon, tatn, chmf, mgnt, yndx, aflt, afks, rtkm, nvtk, pikk, nlmk, dsky));
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
