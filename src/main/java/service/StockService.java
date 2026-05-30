package service;

import data.Stock;

import java.math.BigDecimal;

public interface StockService {
    BigDecimal calculateSellingPrice(Stock stock, BigDecimal priceIncreasePercentage);
    boolean isStockExpired(Stock stock);
    BigDecimal discountSellingPriceWhenExpiring(Stock stock, int expirationThreshold, BigDecimal discountPercent, BigDecimal sellingPrice); //expirationThreshold e praga, sled koito nachislqvam namalenie; discountPercent e procenta namalenie

}
