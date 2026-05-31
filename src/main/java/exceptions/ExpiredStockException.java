package exceptions;

import data.Stock;

public class ExpiredStockException extends RuntimeException {
    public ExpiredStockException(Stock stock) {
        String message = "The stock (" + stock.getName() + ") expired on " + stock.getExpirationDate();
        super(message);
    }
}
