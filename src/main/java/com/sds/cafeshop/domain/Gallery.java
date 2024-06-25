package com.sds.cafeshop.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Gallery {
	
	private String title;
	private MultipartFile file; //실제 파일 담는 객체
	private String filename; //파일명만 담는 변수

}
