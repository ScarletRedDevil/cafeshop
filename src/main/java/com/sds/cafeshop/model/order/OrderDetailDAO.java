package com.sds.cafeshop.model.order;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.OrderDetail;

@Mapper
public interface OrderDetailDAO {
	public void insert(OrderDetail orderDetail);
}
