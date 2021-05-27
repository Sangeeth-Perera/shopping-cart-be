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
 * This class is used to handle Shopping Cart REST end points.
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

    /**
     * Issue Customer Order Service
     * generatePortId
     *
     * Static method UniqueNumber.createRandomCharSequence is not called
     * Due to above reason, last 4 letters are not in the test response Port Id
     */
    @Test
    public void testGetItemPriceList() {
        String itemCode = "HC";
        Double itemPrice = 300.00;
//        HashMap<String, Double> itemMap = new LinkedHashMap<>();
//        itemMap.put("1", 200.00);
//        itemMap.put("2", 400.00);
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
}