package data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Shop {

    private List<Cashier> cashiers;
    private List<CashRegister> cashRegisters;
    private final UUID uuid;
    private String name;
    private List<String> receips;
    private Map<Stock, BigDecimal> Stocks;
    private Map<Category, BigDecimal> priceIncreasesByCategory; //nadcenka po kategoriq, primerno FOOD: 10%; NONFOOD: 5%
}
