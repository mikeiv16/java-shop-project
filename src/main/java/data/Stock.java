package data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Stock implements Serializable {
    private final UUID uuid;
    private String name;
    private BigDecimal priceInitial;
    private BigDecimal priceSelling; //prodajna cena spored uslovieto
    private Category category;
    private LocalDate expirationDate;
    private int quantity;

    public Stock(String name, BigDecimal priceInitial, BigDecimal priceSelling, Category category, LocalDate expirationDate, int quantity) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.priceInitial = priceInitial;
        this.priceSelling = priceSelling;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceInitial() {
        return priceInitial;
    }

    public void setPriceInitial(BigDecimal priceInitial) {
        this.priceInitial = priceInitial;
    }

    public BigDecimal getPriceSelling() {
        return priceSelling;
    }

    public void setPriceSelling(BigDecimal priceSelling) {
        this.priceSelling = priceSelling;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(uuid, stock.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", priceInitial=" + priceInitial +
                ", priceSelling=" + priceSelling +
                ", category=" + category +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                '}';
    }
}
