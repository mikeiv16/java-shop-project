package service;

import data.Stock;

import java.math.BigDecimal;

public interface StockService {
    BigDecimal calculateSellingPrice(Stock stock, BigDecimal priceIncrease);
    boolean isStockExpired(Stock stock);
    BigDecimal discountSellingPriceWhenExpiring(Stock stock, int daysUntilExpiration, BigDecimal discountPercent);
    void decreaseStockQuantity(Stock stock , int quantity);

}
