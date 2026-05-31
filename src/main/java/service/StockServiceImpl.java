package service;

import data.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockServiceImpl implements StockService {


    @Override
    public BigDecimal calculateSellingPrice(Stock stock, BigDecimal priceIncreasePercentage) {
        BigDecimal priceIncrease = stock.getPriceInitial().multiply(priceIncreasePercentage).divide(BigDecimal.valueOf(100));
        return stock.getPriceInitial().add(priceIncrease);
    }

    @Override
    public boolean isStockExpired(Stock stock) {
        if (stock.getExpirationDate() == null) return false;
        else {
            LocalDate currentDate = LocalDate.now();
            return stock.getExpirationDate().isBefore(currentDate);
        }
    }

    @Override
    public BigDecimal discountSellingPriceWhenExpiring(Stock stock, int expirationThreshold, BigDecimal discountPercent, BigDecimal sellingPrice) {
        LocalDate stockExpirationDate = stock.getExpirationDate();
        LocalDate currentDate = LocalDate.now();
        if(stockExpirationDate==null) return sellingPrice;

        int daysLeft = (int) (stockExpirationDate.toEpochDay() - currentDate.toEpochDay());
        if(daysLeft <= expirationThreshold) {
            BigDecimal discount = sellingPrice.multiply(discountPercent).divide(BigDecimal.valueOf(100));
            return sellingPrice.subtract(discount);
        }
        else return sellingPrice;
        ///TO DO: da ne zabravq v CashRegisterService predi da vikna tazi funkciq da proverq isStockExpired() poneje stoki s iztekal srok ne se prodavat
    }

}
