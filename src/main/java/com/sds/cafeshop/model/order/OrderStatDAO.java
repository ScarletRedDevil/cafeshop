package com.sds.cafeshop.model.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.OrderStat;

@Mapper
public interface OrderStatDAO {
	public OrderStat select();

}
