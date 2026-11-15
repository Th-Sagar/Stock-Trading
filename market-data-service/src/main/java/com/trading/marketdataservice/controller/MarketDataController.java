package com.trading.marketdataservice.controller;

import com.trading.marketdataservice.dto.StockPrice;
import com.trading.marketdataservice.entity.Stock;
import com.trading.marketdataservice.service.MarketDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/market")
@RequiredArgsConstructor
@Tag(name = "Market data",description = "Market Data Operations")
public class MarketDataController {

    private final MarketDataService marketDataService;

//    Get all stocks with current prices

    @Operation(summary = "Get all stocks")
    @GetMapping("/stocks")
    public ResponseEntity<List<StockPrice>> getAllStocks(){
        return ResponseEntity.ok(marketDataService.getAllPrices());
    }


//    Get current price for specific stock

    @Operation(summary = "Get stock by symbol")
    @GetMapping("/stocks/{symbol}")
    public ResponseEntity<StockPrice> getStockPrice(@PathVariable String symbol){
        return ResponseEntity.ok(marketDataService.getPrice(symbol.toUpperCase()));
    }

//    shows which stocks are available for trading...

    @Operation(summary = "Holding stocks name")
    @GetMapping("/stocks/list")
    public ResponseEntity<List<Stock>> getStockList(){
        return ResponseEntity.ok(marketDataService.getAllStocks());
    }

}
