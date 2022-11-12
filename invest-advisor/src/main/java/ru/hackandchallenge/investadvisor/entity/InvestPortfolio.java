package ru.hackandchallenge.investadvisor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @OneToMany(fetch = javax.persistence.FetchType.EAGER, mappedBy = "investPortfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PortfolioAsset> portfolioAssets = new HashSet<>();

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    @NotNull
    private LocalDateTime created;

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void writeOffBalance(BigDecimal amount) {
        this.balance = balance.subtract(amount);
    }

}
