package ru.hackandchallenge.investadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hackandchallenge.investadvisor.entity.InvestPortfolio;

@Repository
public interface InvestPortfoliosRepository extends JpaRepository<InvestPortfolio, Long> {
    InvestPortfolio findInvestPortfolioByClientId(Long clientId);
}