package com.system.shoppingcart;

import com.system.shoppingcart.repository.ShoppingCartRepository;
import com.system.shoppingcart.service.ShoppingCartService;
import com.system.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.system.shoppingcart.util.PriceEngineUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * This class is used to Test Shopping Cart Services.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServicesTest {

    @Mock
    ShoppingCartRepository shoppingCartRepository;


    ShoppingCartService shoppingCartService;


    @BeforeEach
    void setUp() {
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository);
    }


    @Test
    public void testGetItemPriceList() {
        String itemCode = "HC";
        Double itemPrice = 300.00;
        try {
            Mockito.when(shoppingCartRepository.getItemPrice(eq(itemCode))).thenReturn(itemPrice);
            try (MockedStatic<PriceEngineUtil> mocked = Mockito.mockStatic(PriceEngineUtil.class)) {
                shoppingCartService.getItemPriceList(itemCode);
                mocked.verify(() -> PriceEngineUtil.priceCalculatorEngine(any(String.class), any(Integer.class), any(Double.class)), times(50));
                verify(shoppingCartRepository).getItemPrice(eq(itemCode));
            }
        } catch (Exception e) {
            fail("testGetItemPriceList method test failed \n " + e);
            e.printStackTrace();
        }
    }
    @Test
    public void testCalculateItemTotal() {
        String itemCode = "HC";
        Integer quantity=10;
        Double itemPrice = 300.00;
        try {
            Mockito.when(shoppingCartRepository.getItemPrice(eq(itemCode))).thenReturn(itemPrice);
            try (MockedStatic<PriceEngineUtil> mocked = Mockito.mockStatic(PriceEngineUtil.class)) {
                shoppingCartService.calculateItemTotal(itemCode,quantity);
                mocked.verify(() -> PriceEngineUtil.priceCalculatorEngine(any(String.class), any(Integer.class), any(Double.class)),times(1));
                verify(shoppingCartRepository).getItemPrice(eq(itemCode));
            }
        } catch (Exception e) {
            fail("testCalculateItemTotal method test failed \n " + e);
            e.printStackTrace();
        }
    }
}