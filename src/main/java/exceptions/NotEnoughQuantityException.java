package exceptions;

import data.Stock;

import java.math.BigDecimal;

public class NotEnoughQuantityException extends RuntimeException {

    public NotEnoughQuantityException(Stock stock, BigDecimal quantity) {
        String message = "Not enough quantity! Stock: " + stock.getName() + " Missing quantity: " + quantity;
        super(message);
    }
}
