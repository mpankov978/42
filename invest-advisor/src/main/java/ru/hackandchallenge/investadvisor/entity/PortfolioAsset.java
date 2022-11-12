package ru.hackandchallenge.investadvisor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "invest_portfolio_assets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioAsset extends BaseEntity {

    @NotNull
    @PositiveOrZero
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "invest_portfolio_id")
    private InvestPortfolio investPortfolio;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public void addCount(Integer amount) {
        this.count += amount;
    }

    public void reduceCount(Integer amount) {
        this.count -= amount;
    }

}
