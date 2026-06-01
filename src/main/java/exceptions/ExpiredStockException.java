package exceptions;

import data.Stock;

public class ExpiredStockException extends RuntimeException {
    private Stock stock;

    public ExpiredStockException(Stock stock) {
        super("Stokata (" + stock.getName() + ") e s iztekul srok ot " + stock.getExpirationDate());
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}
