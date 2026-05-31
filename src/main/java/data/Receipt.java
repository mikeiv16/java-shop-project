package data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class Receipt implements Serializable {
    private static int counter = 1;

    private final int number;
    private final Cashier cashier;
    private final LocalDateTime localDateTime;

    private final Map<Stock, BigDecimal> soldStocks;
    private final BigDecimal totalSum;

    public Receipt(Cashier cashier, Map<Stock, BigDecimal> soldStocks, BigDecimal totalSum) {
        this.cashier = cashier;
        this.soldStocks = soldStocks;
        this.totalSum = totalSum;

        this.localDateTime = LocalDateTime.now();
        this.number = counter++;
    }

    public int getNumber() {
        return number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Map<Stock, BigDecimal> getSoldStocks() {
        return soldStocks;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "number=" + number +
                ", cashier=" + cashier +
                ", localDateTime=" + localDateTime +
                ", soldStocks=" + soldStocks +
                ", totalSum=" + totalSum +
                '}';
    }
}
