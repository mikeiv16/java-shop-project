package service;

import data.CashRegister;
import data.Receipt;
import data.Shop;
import data.Stock;
import exceptions.ExpiredStockException;
import exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashRegisterServiceImpl implements CashRegisterService {

    private StockService stockService;
    private ShopService shopService;

    public CashRegisterServiceImpl(StockService stockService, ShopService shopService) {
        this.stockService = stockService;
        this.shopService = shopService;
    }

    @Override
    public Receipt sellStocks(Shop shop, CashRegister cashRegister, Map<Stock,BigDecimal> stocksToSell, BigDecimal givenMoneyFromCustomer) throws NotEnoughMoneyException {
        //polzvam Map<Stock,BigDecimal>stocksToSell kato danni za kolichkata (kakvo iska da kupi usera) vmesto List<Stock> zashtoto tam da suhranqvam i kolichestvoto produkti, koito shte se prodawat

        for(Stock st : stocksToSell.keySet()){
            if(stockService.isStockExpired(st)) throw new ExpiredStockException(st);
        }


        Map<Stock, BigDecimal> finalPrices = new HashMap<>(); //tova e kolekciq sus stoki i tehnite finalni ceni (sled namaleniq) na koito se prodavat na klientite
        BigDecimal totalSum = BigDecimal.ZERO;
        for(Map.Entry<Stock, BigDecimal> entry : stocksToSell.entrySet()){
            Stock st = entry.getKey();
            BigDecimal quantityToBuy = entry.getValue();

            BigDecimal priceIncreasePercentage = shop.getPriceIncreasesByCategory().get(st.getCategory()); //vzimam % nadcenka sprqmo kategoriqta, primerno 10% FOOD, 5% NON_FOOD
            BigDecimal sellingPrice = stockService.calculateSellingPrice(st, priceIncreasePercentage);

            sellingPrice = stockService.discountSellingPriceWhenExpiring(st, shop.getExpirationThreshold(), shop.getExpirationDiscountPercentage(), sellingPrice);
            finalPrices.put(st, sellingPrice);
            totalSum = totalSum.add(sellingPrice.multiply(quantityToBuy));
            //Budeshta realizaciq: v kasovata belejka da pishe ako stoka e namalena zaradi nablijavasht expiration
        }

        if(givenMoneyFromCustomer.compareTo(totalSum) >= 0){
            BigDecimal change = givenMoneyFromCustomer.subtract(totalSum);

            //namalqvane na stokata ot sklada na magazina:
            for (Map.Entry<Stock,BigDecimal> entry : stocksToSell.entrySet()){
                shopService.reduceStockQuantity(shop, entry.getKey(), entry.getValue());
            }

            return new Receipt(shop.getName(), cashRegister.getWorkingCashier(), cashRegister, stocksToSell, finalPrices, totalSum, givenMoneyFromCustomer, change);
        }
        else throw new NotEnoughMoneyException(totalSum, givenMoneyFromCustomer);


    }
}
