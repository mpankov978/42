package ru.hackandchallenge.investadvisor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;
import ru.hackandchallenge.investadvisor.repository.InvestPortfoliosRepository;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class InvestPortfolioService {

    private final InvestPortfoliosRepository investPortfoliosRepository;

    public void enrollBalance(Long clientId, String amount) {
        InvestPortfolio portfolio = investPortfoliosRepository.findInvestPortfolioByClientId(clientId);
        portfolio.addBalance(new BigDecimal(amount));
        investPortfoliosRepository.save(portfolio);
    }
}
