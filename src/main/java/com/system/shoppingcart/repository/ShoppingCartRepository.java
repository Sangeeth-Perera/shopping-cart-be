package com.system.shoppingcart.repository;

public interface ShoppingCartRepository {

    Double getItemPrice(String productName) throws Exception;
}
