package com.sds.cafeshop.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderSummary {
	private int ordersummary_idx;
	private String orderdate;
	private int total_buy;
	private int total_pay;

	private Paymethod paymethod;
	private Ordernum ordernum;
	private OrderStat orderstat;
	
    private List<OrderDetail> orderDetailList; // 추가된 필드
}
