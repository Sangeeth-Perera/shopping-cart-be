package com.system.shoppingcart.DTO.request;

import lombok.Data;

@Data
public class ItemTotalRequest {
    private String itemCode;
    private Integer quantity;
}
