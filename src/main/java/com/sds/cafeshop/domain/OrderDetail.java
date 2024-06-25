package com.sds.cafeshop.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int orderdetail_idx;
	private int ea;
	private String product_name;
	private int price;

	private OrderSummary orderSummary; //어떤 주문에 대한 것인지..
	private Product product;

}