package com.sds.cafeshop.model.product;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.Psize;

@Mapper
public interface PsizeDAO {
	public void insert(Psize psize);
}
