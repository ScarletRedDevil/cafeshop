package com.sds.cafeshop.model.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.OrderSummary;

@Mapper
public interface OrderSummaryDAO {
	
	public void insert(OrderSummary orderSummary);
    public List<OrderSummary> selectByOrderStatIdx(int stat);
    
}
