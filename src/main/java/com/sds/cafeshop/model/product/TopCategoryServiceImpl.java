package com.sds.cafeshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.cafeshop.domain.TopCategory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TopCategoryServiceImpl implements TopCategoryService{

	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	public List selectAll() {
		
		List list = topCategoryDAO.selectAll();
		
		return list;
	}

	public TopCategory select(int topcategory_idx) {
		return null;
	}

}
