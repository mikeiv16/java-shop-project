package exceptions;

import data.Stock;

import java.math.BigDecimal;

public class NotEnoughQuantityException extends RuntimeException {

    private Stock stock;
    private BigDecimal quantity;

    public NotEnoughQuantityException(Stock stock, BigDecimal quantity) {
        //String message = "Nqma dostatuchno kolichestvo. Stoka: " + stock.getName() + " Lipsvashto kolichestvo: " + quantity;
        super("Nqma dostatuchno kolichestvo. Stoka: " + stock.getName() + " Lipsvashto kolichestvo: " + quantity);
        this.stock  = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
