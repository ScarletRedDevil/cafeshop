package com.sds.cafeshop.exception;

public class PsizeException extends RuntimeException{
	
	public PsizeException(String msg) {
		super(msg);
	}
	
	public PsizeException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public PsizeException(Throwable e) {
		super(e);
	}
}
