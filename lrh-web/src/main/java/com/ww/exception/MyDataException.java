package com.ww.exception;
/**
 * 自定义异常类
 * @author root
 *
 */
public class MyDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyDataException(String mes,Exception e){
		super(mes,e);
	}
}
