package ru.hackandchallenge.investadvisor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invest_portfolio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestPortfolio extends BaseEntity {

    @NotNull
    private Long clientId;

    @OneToMany(mappedBy = "investPortfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PortfolioAsset> portfolioAssets = new HashSet<>();

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void writeOffBalance(BigDecimal amount) {
        this.balance = balance.subtract(amount);
    }

}
