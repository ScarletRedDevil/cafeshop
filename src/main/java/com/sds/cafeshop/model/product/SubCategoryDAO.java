package com.sds.cafeshop.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.cafeshop.domain.SubCategory;

@Mapper
public interface SubCategoryDAO {
	public List selectAll(); 
	public List<SubCategory> selectAllByTopIdx(int topcategory_idx);

}
