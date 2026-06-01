package data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Shop {

    private List<Cashier> cashiers;
    private List<CashRegister> cashRegisters;
    private final UUID uuid;
    private String name;
    private List<Receipt> receipts;
    private Map<Stock, BigDecimal> stocks; //sudurja stoki i kolichestvoto im
    private Map<Category, BigDecimal> priceIncreasesByCategory; //nadcenka po kategoriq, primerno FOOD: 10%; NONFOOD: 5%
    private int expirationThreshold;
    private BigDecimal expirationDiscountPercentage;
    private BigDecimal extraExpenses; //dopulnitelni razhodi po magazina (smetki, naem, etc)

    public Shop(List<Cashier> cashiers, List<CashRegister> cashRegisters, String name, List<Receipt> receipts, Map<Stock, BigDecimal> stocks, Map<Category, BigDecimal> priceIncreasesByCategory, int expirationThreshold, BigDecimal expirationDiscountPercentage, BigDecimal extraExpenses) {
        this.uuid = UUID.randomUUID();
        this.cashiers = cashiers;
        this.cashRegisters = cashRegisters;
        this.name = name;
        this.receipts = receipts;
        this.stocks = stocks;
        this.priceIncreasesByCategory = priceIncreasesByCategory;
        this.expirationThreshold = expirationThreshold;
        this.expirationDiscountPercentage = expirationDiscountPercentage;
        this.extraExpenses = extraExpenses;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public List<CashRegister> getCashRegisters() {
        return cashRegisters;
    }

    public void setCashRegisters(List<CashRegister> cashRegisters) {
        this.cashRegisters = cashRegisters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Map<Stock, BigDecimal> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Stock, BigDecimal> stocks) {
        this.stocks = stocks;
    }

    public Map<Category, BigDecimal> getPriceIncreasesByCategory() {
        return priceIncreasesByCategory;
    }

    public void setPriceIncreasesByCategory(Map<Category, BigDecimal> priceIncreasesByCategory) {
        this.priceIncreasesByCategory = priceIncreasesByCategory;
    }

    public int getExpirationThreshold() {
        return expirationThreshold;
    }

    public void setExpirationThreshold(int expirationThreshold) {
        this.expirationThreshold = expirationThreshold;
    }

    public BigDecimal getExpirationDiscountPercentage() {
        return expirationDiscountPercentage;
    }

    public void setExpirationDiscountPercentage(BigDecimal expirationDiscountPercentage) {
        this.expirationDiscountPercentage = expirationDiscountPercentage;
    }

    public BigDecimal getExtraExpenses() {
        return extraExpenses;
    }

    public void setExtraExpenses(BigDecimal extraExpenses) {
        this.extraExpenses = extraExpenses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(uuid, shop.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "cashiers=" + cashiers +
                ", cashRegisters=" + cashRegisters +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", receipts=" + receipts +
                ", stocks=" + stocks +
                ", priceIncreasesByCategory=" + priceIncreasesByCategory +
                ", expirationThreshold=" + expirationThreshold +
                ", expirationDiscountPercentage=" + expirationDiscountPercentage +
                '}';
    }
}
