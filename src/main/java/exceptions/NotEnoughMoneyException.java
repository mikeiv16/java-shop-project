package exceptions;

import data.Stock;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(BigDecimal cost, BigDecimal givenMoney) {
        String message = "Not enough money! Purchase costs: " + cost + " Given: " + givenMoney + "! You need €" + cost.subtract(givenMoney) + " more to purchase the stock(s)!";
        super(message);
    }
}
