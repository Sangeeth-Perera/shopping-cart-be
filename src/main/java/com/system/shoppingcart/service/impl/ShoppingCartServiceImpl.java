package com.system.shoppingcart.service.impl;

import com.system.shoppingcart.DTO.Metadata;
import com.system.shoppingcart.repository.ShoppingCartRepository;
import com.system.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.system.shoppingcart.util.PriceEngineUtil.priceCalculatorEngine;

/**
 *  This class use to handle Shopping Cart Services.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public HashMap<String, Double> getItemPriceList(String itemCode) throws Exception {
        Double itemPrice = shoppingCartRepository.getItemPrice(itemCode);
        HashMap<String, Double> newList =new  HashMap<String, Double>();

        for (int count=1; count<=50 ; count++){
            double price = priceCalculatorEngine(itemCode, count, itemPrice);
            newList.put(String.valueOf(count), price);
        }

        return newList;
    }

    @Override
    public Double calculateItemTotal(String itemCode, Integer quantity) throws Exception {
        Double itemPrice = shoppingCartRepository.getItemPrice(itemCode);
        return priceCalculatorEngine(itemCode, quantity, itemPrice);
    }


}
