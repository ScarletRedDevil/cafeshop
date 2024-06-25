package com.sds.cafeshop.domain;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_idx;
	private String topname;
	
	private List<SubCategory> subList;
}
