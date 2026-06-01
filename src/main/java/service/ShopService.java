package service;

import data.Shop;
import data.Stock;

import java.math.BigDecimal;

public interface ShopService {

    void reduceStockQuantity(Shop shop, Stock stock, BigDecimal quantityToSell);
    BigDecimal calculateShopExpenses(Shop shop);
    BigDecimal calculateShopIncome(Shop shop);
    BigDecimal calculateShopProfit(Shop shop);
}
