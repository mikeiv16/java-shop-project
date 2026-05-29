package service;

import data.CashRegister;
import data.Stock;
import java.math.BigDecimal;
import java.util.List;

public interface CashRegisterService {

    void sellStocks(CashRegister cashRegister, List<Stock> stocksToSell, BigDecimal givenMoneyFromCustomer);
    //maybe throw Exception?
}
