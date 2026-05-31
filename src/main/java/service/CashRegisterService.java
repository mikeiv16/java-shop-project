package service;

import data.CashRegister;
import data.Receipt;
import data.Shop;
import data.Stock;
import exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CashRegisterService {

    Receipt sellStocks(Shop shop, CashRegister cashRegister, Map<Stock, BigDecimal> stocksToSell, BigDecimal givenMoneyFromCustomer) throws NotEnoughMoneyException;

}
