package com.trading.marketdataservice.service.impl;

import com.trading.marketdataservice.dto.StockPrice;
import com.trading.marketdataservice.entity.Stock;
import com.trading.marketdataservice.repository.StockRepository;
import com.trading.marketdataservice.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketDataServiceImpl implements MarketDataService {

    private final StockRepository stockRepository;

//     concurrentHashMap - for thread safety
    private final Map<String,StockPrice> stockPrices = new ConcurrentHashMap<>();

//     initialize stock prices
//    postconstruct -> runs when context is ready

    public void initializePrice(){
        log.info("Loading stock data ....");
        List<Stock> stocks = stockRepository.findByActiveTrue();
        if(stocks.isEmpty()){
            log.warn("No active stocks found!");
            return;
        }

        stocks.forEach(stock -> {
            StockPrice stockPrice = new StockPrice();
            stockPrice.setSymbol(stock.getSymbol());
            stockPrice.setCompanyName(stock.getCompanyName());
            stockPrice.setPrice(stock.getInitialPrice());
            stockPrice.setOpen(stock.getInitialPrice());
            stockPrice.setHigh(stock.getInitialPrice());
            stockPrice.setLow(stock.getInitialPrice());
            stockPrice.setChange(BigDecimal.ZERO);
            stockPrice.setChangePercent(BigDecimal.ZERO);
            stockPrice.setVolume(0L);
            stockPrice.setTimestamp(LocalDateTime.now());

            stockPrices.put(stock.getSymbol(), stockPrice);
            log.info("Loaded stock: {} at {} ",stock.getSymbol(),stock.getInitialPrice());
        });

        log.info("Initialized {} stocks ", stockPrices.size());
    }


    @Override
    public List<StockPrice> getAllPrices() {
        return List.of();
    }

    @Override
    public StockPrice getPrice(String upperCase) {
        return null;
    }

    @Override
    public List<Stock> getAllStocks() {
        return List.of();
    }
}
