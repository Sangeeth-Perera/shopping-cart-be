package com.system.shoppingcart.service;

import java.util.HashMap;

/**
 *  This interface use to handle Shopping Cart Services.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */
public interface ShoppingCartService {
    /**
     * This method will help to read metadata from database and save it to the singleton.
     *
     * @param productName Product Name
     * @return {@link HashMap<>}
     */
    HashMap<String,Double> getItemPriceList(String productName) throws Exception;

    Double calculateItemTotal(String itemCode, Integer quantity) throws Exception;


}
