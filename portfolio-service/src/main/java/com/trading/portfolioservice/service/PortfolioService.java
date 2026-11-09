package com.trading.portfolioservice.service;

import java.util.Map;

public interface PortfolioService {
    Map<String, Object> getPortfolio(String userId);
}
