package exceptions;

import data.Stock;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(BigDecimal cost, BigDecimal givenMoney) {
        //String message = "Nedostatuchno pari! Obshto struva: " + cost + " Dadeni: " + givenMoney + "! Nedostigat €" + cost.subtract(givenMoney);
        super("Nedostatuchno pari! Obshto struva: " + cost + " Dadeni: " + givenMoney + "! Nedostigat €" + cost.subtract(givenMoney));
    }
}
