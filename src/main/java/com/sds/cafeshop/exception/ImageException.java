package com.sds.cafeshop.exception;

public class ImageException extends RuntimeException{
	
	public ImageException(String msg) {
		super(msg);
	}
	
	public ImageException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public ImageException(Throwable e) {
		super(e);
	}
}
