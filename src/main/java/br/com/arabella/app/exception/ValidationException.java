package br.com.arabella.app.exception;

public class ValidationException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String entity;
	private String field;
	
	public ValidationException(String code, String entity, String field, String message){
		super(message);
		this.code = code;
		this.entity = entity;
		this.field = field;
	}
	
	public String getEntity(){
		return entity;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getField(){
		return field;
	}
}