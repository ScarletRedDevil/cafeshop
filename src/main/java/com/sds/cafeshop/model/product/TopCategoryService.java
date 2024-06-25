package com.sds.cafeshop.model.product;

import java.util.List;

import com.sds.cafeshop.domain.TopCategory;

public interface TopCategoryService {
	
	public List selectAll();
	public TopCategory select(int topcategory_idx);

}
