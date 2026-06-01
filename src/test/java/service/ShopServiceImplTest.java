package service;

import data.Category;
import data.Shop;
import data.Stock;
import exceptions.NotEnoughQuantityException;
import exceptions.StockNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceImplTest {
    private ShopServiceImpl shopService;
    private Shop shop;
    private Stock water;

    @BeforeEach
    void setUp() {
        shopService = new ShopServiceImpl();
        water = new Stock("Voda", BigDecimal.valueOf(1.0), Category.FOOD);

        Map<Stock, BigDecimal> inventory = new HashMap<>();
        inventory.put(water, BigDecimal.valueOf(10));

        shop = new Shop(null, null, "Test Shop", null, inventory, null, 0, null, null);
    }

    @Test
    void reduceStockQuantity_ThrowsNotEnoughQuantityException() {
        BigDecimal requestedQuantity = BigDecimal.valueOf(15);

        assertThrows(NotEnoughQuantityException.class, () -> {
            shopService.reduceStockQuantity(shop, water, requestedQuantity);
        });
    }

    @Test
    void reduceStockQuantity_ThrowsStockNotAvailableException() {
        Stock missingStock = new Stock("Hlqb", BigDecimal.valueOf(1.5), Category.FOOD);

        assertThrows(StockNotAvailableException.class, () -> {
            shopService.reduceStockQuantity(shop, missingStock, BigDecimal.ONE);
        });
    }
}