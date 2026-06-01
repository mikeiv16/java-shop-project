package service;

import data.*;
import exceptions.ExpiredStockException;
import exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class CashRegisterServiceImplTest {
    @Mock
    private StockService stockService;

    @Mock
    private ShopService shopService;

    private CashRegisterServiceImpl cashRegisterService;
    private Shop shop;
    private CashRegister register;
    private Stock milk;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cashRegisterService = new CashRegisterServiceImpl(stockService, shopService);
        milk = new Stock("Mlqko", BigDecimal.valueOf(2.0), Category.FOOD, LocalDate.now().minusDays(1));

        Map<Category, BigDecimal> markups = new EnumMap<>(Category.class);
        markups.put(Category.FOOD, BigDecimal.valueOf(10));

        shop = new Shop(null, null, "Test Shop", null, new HashMap<>(), markups, 3, BigDecimal.ZERO, BigDecimal.ZERO);
        register = new CashRegister(1, new Cashier("Pesho", BigDecimal.valueOf(1000)));
    }

    @Test
    void sellStocks_ThrowsExpiredStockException() {
        Map<Stock, BigDecimal> cart = new HashMap<>();
        cart.put(milk, BigDecimal.ONE);

        when(stockService.isStockExpired(milk)).thenReturn(true);

        assertThrows(ExpiredStockException.class, () -> {
            cashRegisterService.sellStocks(shop, register, cart, BigDecimal.valueOf(100));
        });
    }

    @Test
    void sellStocks_ThrowsNotEnoughMoneyException() {
        Map<Stock, BigDecimal> cart = new HashMap<>();
        cart.put(milk, BigDecimal.ONE);


        when(stockService.isStockExpired(milk)).thenReturn(false);
        when(stockService.calculateSellingPrice(any(), any())).thenReturn(BigDecimal.valueOf(5.0));
        //when(stockService.discountSellingPriceWhenExpiring(any(), any(), any(), any())).thenReturn(BigDecimal.valueOf(5.0));
        when(stockService.discountSellingPriceWhenExpiring(any(), anyInt(), any(), any())).thenReturn(BigDecimal.valueOf(5.0));

        BigDecimal givenMoney = BigDecimal.valueOf(2.0);

        assertThrows(NotEnoughMoneyException.class, () -> {
            cashRegisterService.sellStocks(shop, register, cart, givenMoney);
        });
    }
}