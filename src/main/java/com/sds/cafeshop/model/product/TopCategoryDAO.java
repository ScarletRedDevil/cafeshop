package com.sds.cafeshop.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.TopCategory;

@Mapper
public interface TopCategoryDAO {
	
	public List selectAll();
	public TopCategory select(int topcategory_idx);

}
