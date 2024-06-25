package com.sds.cafeshop.model.product;

import java.util.List;

public interface SubCategoryService {
	public List selectAll(); 
	public List selectAllByTopIdx(int topcategory_idx);//부모에 소속된 목록 가져오기

}
