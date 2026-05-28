package service;

import data.Stock;

import java.math.BigDecimal;

public interface StockService {
    BigDecimal priceSelling(Stock stock, BigDecimal priceIncreaseFood, BigDecimal priceIncreaseNotFood);
    boolean isStockExpired(Stock stock);
    BigDecimal discountSellingPriceWhenExpiring(Stock stock, int daysUntilExpiration, BigDecimal discountPercent);
    void decreaseStockQuantity(Stock stock , int quantity);

}
