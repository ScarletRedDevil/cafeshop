package com.sds.cafeshop.domain;

import lombok.Data;

@Data
public class Admin {

	private int admin_idx;
	private String uid;
	private String pwd;
	private String username;
	
	private Role role;
	
}
