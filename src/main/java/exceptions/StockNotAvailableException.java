package exceptions;

import data.Stock;

public class StockNotAvailableException extends RuntimeException {

    private Stock stock;

    public StockNotAvailableException(Stock stock) {
      this.stock = stock;
      String message = "Stock " + stock.getName() + " not available in this shop.";
      super(message);
    }

    public Stock getStock() {
        return stock;
    }
}
