package com.trading.marketdataservice.service;

import com.trading.marketdataservice.dto.StockPrice;
import com.trading.marketdataservice.entity.Stock;

import java.util.List;

public interface MarketDataService {
    List<StockPrice> getAllPrices();

    StockPrice getPrice(String upperCase);

    List<Stock> getAllStocks();
}
