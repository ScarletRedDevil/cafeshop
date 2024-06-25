package com.sds.cafeshop.model.order;

import java.util.List;

import com.sds.cafeshop.domain.OrderSummary;

import jakarta.servlet.http.HttpSession;

public interface OrderService {
	public List selectPaymethod();//결제 방법 가져오기 
	
	//주문등록
	public void order(OrderSummary orderSummary, HttpSession session);
	
}