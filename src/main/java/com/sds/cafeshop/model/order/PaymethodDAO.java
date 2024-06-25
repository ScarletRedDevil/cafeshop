package com.sds.cafeshop.model.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymethodDAO {
	public List selectAll();
}
