package service;

import data.Cashier;
import data.Receipt;
import data.Shop;
import data.Stock;
import exceptions.NotEnoughQuantityException;
import exceptions.StockNotAvailableException;

import java.math.BigDecimal;
import java.util.List;
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
                BigDecimal missingQuantity = quantityToSell.subtract(availableQuantity);
                throw new NotEnoughQuantityException(stock, missingQuantity);
                //To do: primerno ako ima 15 vodi v magazina, a iskam da si kupq 20 vodi -> dali da prodam tezi 15 ili da ne gi prodam? Prompt ot usera v consolata
            }
        }
        else {
            throw new StockNotAvailableException(stock);
        }

    }

    @Override
    public BigDecimal calculateShopExpenses(Shop shop) {
        //Razhodi = zaplati + zakupeni stoki + dopulnitelni (smetki, naem, t.n.)
        BigDecimal expenses = BigDecimal.ZERO;

        for(Cashier cashier: shop.getCashiers()){
            expenses = expenses.add(cashier.getSalary());
        }
        for(Map.Entry<Stock, BigDecimal> entry : shop.getStocks().entrySet()){
            Stock stock = entry.getKey();
            BigDecimal quantity = entry.getValue();

            BigDecimal costOfStocks = stock.getPriceInitial().multiply(quantity);
            expenses = expenses.add(costOfStocks);
        }

        if(shop.getExtraExpenses() != null) expenses = expenses.add(shop.getExtraExpenses());

        return expenses;
    }

    @Override
    public BigDecimal calculateShopIncome(Shop shop) {
        List<Receipt> allShopReceipts = shop.getReceipts();
        BigDecimal totalIncome = BigDecimal.ZERO;
        for(Receipt rec : allShopReceipts){
            totalIncome = totalIncome.add(rec.getTotalSum());
        }
        return totalIncome;
    }

    @Override
    public BigDecimal calculateShopProfit(Shop shop) {
        return calculateShopIncome(shop).subtract(calculateShopExpenses(shop));
    }

}
