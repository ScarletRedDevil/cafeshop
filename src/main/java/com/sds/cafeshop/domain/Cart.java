package com.sds.cafeshop.domain;

import lombok.Data;

@Data
public class Cart {
    private int product_idx;
    private int ea;
    
    private Product product;
}
