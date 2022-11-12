package ru.hackandchallenge.investadvisor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invest_portfolio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestPortfolio extends BaseEntity {

    private Long clientId;

    @ManyToMany
    @JoinTable(name = "invest_portfolio_assets",
            joinColumns = @JoinColumn(name = "invest_portfolio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "assets_id", referencedColumnName = "id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Asset> assets = new ArrayList<>();

    private BigDecimal balance;
}
