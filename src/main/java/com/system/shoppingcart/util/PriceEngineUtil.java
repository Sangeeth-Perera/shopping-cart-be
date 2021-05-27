package com.system.shoppingcart.util;

import com.system.shoppingcart.DTO.Metadata;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class PriceEngineUtil {

    private  PriceEngineUtil(){
    }

    public static Double priceCalculatorEngine(String itemCode, Integer quantity, Double itemPrice) {
        Double calculatedPrice = 0.0;
        Integer cartonQty =1;
        Integer singleUnits;
        Integer cartonsCount;
        Double unitPrice;
        Double discountedItemPrice;
        Double unitPriceRate      = Double.valueOf(Metadata.getInstance().get("single-unit-rate"));
        Integer maxCartonQty      = Integer.valueOf(Metadata.getInstance().get("max-cartons-discount"));
        Double discountPercentage = Double.valueOf(Metadata.getInstance().get("cartons-discount-percentage"));

        if (itemCode.equals("HC") ) {
            cartonQty = Integer.valueOf(Metadata.getInstance().get("horseshoe-carton-count"));
        }
        else if (itemCode.equals("PEC")) {
            cartonQty = Integer.valueOf(Metadata.getInstance().get("penguin-ears-carton-count"));
        }
        else {
            log.info("Item not found");
            return null;
        }

        singleUnits = quantity % cartonQty;
        cartonsCount= quantity / cartonQty;

        if (cartonsCount >= maxCartonQty) {
            discountedItemPrice = itemPrice - (itemPrice * discountPercentage);
        }
        else {
            discountedItemPrice = itemPrice;
        }

        unitPrice = (itemPrice * unitPriceRate)/cartonQty;

        calculatedPrice = (unitPrice*singleUnits) + (discountedItemPrice*cartonsCount);
//        calculatedPrice = Math.round(calculatedPrice * 100.0) / 100.0;

        DecimalFormat df = new DecimalFormat("#.##");
        calculatedPrice = Double.valueOf(df.format(calculatedPrice));

        return calculatedPrice;
    }
}
