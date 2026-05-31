package exceptions;

import data.Stock;

public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(Stock stock) {
      String message = "Stock " + stock.getName() + " not available in this shop.";
      super(message);
    }
}
