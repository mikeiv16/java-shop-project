package service;

import data.Stock;

import java.math.BigDecimal;

public class StockServiceImpl implements StockService {


    @Override
    public BigDecimal calculateSellingPrice(Stock stock, BigDecimal priceIncrease) {
        return null;
        //тест
    }

    @Override
    public boolean isStockExpired(Stock stock) {
        return false;
    }

    @Override
    public BigDecimal discountSellingPriceWhenExpiring(Stock stock, int daysUntilExpiration, BigDecimal discountPercent) {
        return null;
    }

    @Override
    public void decreaseStockQuantity(Stock stock, int quantity) {

    }
}
