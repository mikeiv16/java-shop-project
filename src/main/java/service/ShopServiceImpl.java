package service;

import data.Shop;
import data.Stock;
import exceptions.NotEnoughQuantityException;
import exceptions.StockNotAvailableException;

import java.math.BigDecimal;
import java.util.Map;

public class ShopServiceImpl implements ShopService {


    @Override
    public void reduceStockQuantity(Shop shop, Stock stock, BigDecimal quantityToSell) {
        Map<Stock, BigDecimal> stocksMap = shop.getStocks();

        if(stocksMap.containsKey(stock)){
            BigDecimal availableQuantity = stocksMap.get(stock);
            if(availableQuantity.compareTo(quantityToSell) >= 0){
                BigDecimal newQuantity = availableQuantity.subtract(quantityToSell);
                stocksMap.replace(stock, newQuantity);
            }
            else {
                BigDecimal missingQuantity = availableQuantity.subtract(quantityToSell);
                throw new NotEnoughQuantityException(stock, missingQuantity);
                //To do: primerno ako ima 15 vodi v magazina, a iskam da si kupq 20 vodi -> dali da prodam tezi 15 ili da ne gi prodam? Prompt ot usera v consolata
            }
        }
        else {
            throw new StockNotAvailableException(stock);
        }

    }
}
