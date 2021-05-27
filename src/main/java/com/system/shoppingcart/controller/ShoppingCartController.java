package com.system.shoppingcart.controller;

import com.system.shoppingcart.DTO.request.ItemTotalRequest;
import com.system.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

/**
 * This class is used to handle Shopping Cart REST end points.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */

@RestController
@RequestMapping("")
@Slf4j
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping(value = "/get-price-list/{productName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap<String, Double>> getItemPriceList(@PathVariable("productName") String productName) {
        log.info("Get all items: {}");
        try {
            return ResponseEntity.ok(shoppingCartService.getItemPriceList(productName));
        } catch (Exception e) {
            return (ResponseEntity<HashMap<String, Double>>) ResponseEntity.badRequest();
        }
    }
    @GetMapping(value = "/get-item-total", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Double> calculateItemTotal(ItemTotalRequest itemTotalRequest) {
        log.info("Get all items: {}");
        try {
            return ResponseEntity.ok(shoppingCartService.calculateItemTotal(itemTotalRequest.getItemCode(), itemTotalRequest.getQuantity()));
        } catch (Exception e) {
            return (ResponseEntity<Double>) ResponseEntity.badRequest();
        }
    }

}
