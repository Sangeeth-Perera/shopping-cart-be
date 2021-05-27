package com.system.shoppingcart;

import com.system.shoppingcart.repository.ShoppingCartRepository;
import com.system.shoppingcart.repository.impl.ShoppingCartRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShoppingCartRepositoryTests {

    @Mock
    NamedParameterJdbcTemplate readJdbcTemplate;

    ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    void setUp() {
        shoppingCartRepository = new ShoppingCartRepositoryImpl(readJdbcTemplate);
    }

    @Test
    public void testGetItemPrice() {
        String itemCode = "HC";
        Double itemPrice = 20.00;
        String sql = "SELECT item_price FROM items WHERE item_code = :itemCode;" ;
        try {
            Mockito.when(readJdbcTemplate.queryForObject(eq(sql), any(MapSqlParameterSource.class), eq(Double.class))).thenReturn(itemPrice);
            Double fetchedItemPrice = shoppingCartRepository.getItemPrice(itemCode);
            verify(readJdbcTemplate).queryForObject(eq(sql), any(MapSqlParameterSource.class), eq(Double.class));
            assertEquals(itemPrice, fetchedItemPrice);
        } catch (Exception e) {
            fail("testGetItemPrice method test failed \n " + e);
            e.printStackTrace();
        }
    }
}