package com.sds.cafeshop.model.order;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.Ordernum;

@Mapper
public interface OrdernumDAO {
	int insertOrdernum();
	int updateOrdernum();
	Ordernum selectLastOrdernum();
}
