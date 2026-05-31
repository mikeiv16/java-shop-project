package data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Receipt implements Serializable {
    private static int counter = 1;

    private final String shopName;
    private final int number;
    private final Cashier cashier;
    private final CashRegister cashRegister;
    private final LocalDateTime localDateTime;

    private final Map<Stock, BigDecimal> soldStocks; //stoka -> quantity
    private final Map<Stock, BigDecimal> itemPrices; //stoka -> cena
    private final BigDecimal totalSum;
    private final BigDecimal givenMoney;
    private final BigDecimal change;

    public Receipt(String shopName, Cashier cashier, CashRegister cashRegister, Map<Stock, BigDecimal> soldStocks,  Map<Stock, BigDecimal> itemPrices, BigDecimal totalSum, BigDecimal givenMoney, BigDecimal change) {
        this.shopName = shopName;
        this.cashier = cashier;
        this.cashRegister = cashRegister;
        this.soldStocks = soldStocks;
        this.itemPrices = itemPrices;
        this.totalSum = totalSum;
        this.givenMoney = givenMoney;
        this.change = change;

        this.localDateTime = LocalDateTime.now();
        this.number = counter++;
    }

    public String getShopName() {
        return shopName;
    }

    public int getNumber() {
        return number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    public Map<Stock, BigDecimal> getItemPrices() {
        return itemPrices;
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

    public BigDecimal getGivenMoney() {
        return givenMoney;
    }

    public BigDecimal getChange() {
        return change;
    }

    @Override
    public String toString() {

        String receiptTxt = "==========\n"
                + shopName.toUpperCase() + "\n" +
                "==========\n";

        for(Map.Entry<Stock,BigDecimal> entry : soldStocks.entrySet()){
            Stock s = entry.getKey();
            BigDecimal q = entry.getValue();
            BigDecimal price = itemPrices.get(s);

            receiptTxt += String.format("%s x%s (€%.2f)\n", s.getName(), q, price);
        }

        receiptTxt += "==========\n";
        receiptTxt += String.format("Total: €%.2f\n", totalSum);
        receiptTxt += String.format("Given: €%.2f\n", givenMoney);
        receiptTxt += String.format("Change: €%.2f\n", change);
        receiptTxt += "==========\n";
        receiptTxt += "Cash register: " + cashRegister.getCashRegisterNumber() + " Cashier: " + cashier.getName() + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");
        receiptTxt += "Time: " + localDateTime.format(formatter) + "\n";
        receiptTxt += "==========\n";

        return receiptTxt;
    }
}
