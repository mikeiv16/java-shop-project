package service;

import data.Shop;
import data.Stock;

import java.math.BigDecimal;

public interface ShopService {

    void reduceStockQuantity(Shop shop, Stock stock, BigDecimal quantityToSell);
}
