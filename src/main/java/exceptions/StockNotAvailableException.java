package exceptions;

import data.Stock;

public class StockNotAvailableException extends RuntimeException {

    private Stock stock;

    public StockNotAvailableException(Stock stock) {

      //String message = "Stokata " + stock.getName() + " ne se prodava v magazina.";
      super("Stokata " + stock.getName() + " ne se prodava v magazina.");
      this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}
