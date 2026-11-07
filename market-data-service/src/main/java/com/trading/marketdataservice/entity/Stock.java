package com.trading.marketdataservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @Column(nullable = false,unique = false)
    private String symbol;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,precision = 15, scale = 2)
    private BigDecimal initialPrice;

    @Column(nullable = false)
    private String exchange;

    @Column(nullable = false)
    private String currency;

    private boolean active=true;
}
