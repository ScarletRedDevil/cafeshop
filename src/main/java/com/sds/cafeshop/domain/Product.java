package com.sds.cafeshop.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_idx;
	private String product_name;
	private int price;
	private String detail;
	
	private String title;
	private MultipartFile file; 
	private String filename;
	
	List<Psize> psizeList; 
	
	//부모를 참조 
	private SubCategory subCategory;
	
}

