package br.com.arabella.app.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ValidationException> validationExceptions = new ArrayList<ValidationException>();
	
	public ValidationsException(){
		super("Multiplas exce��es.");
	}
	
	public ValidationsException(ValidationException e){
		this();
		this.add(e);
	}
	
	public ValidationsException add(ValidationException e){
		if(e != null)
			validationExceptions.add(e);
		return this;
	}
	
	public boolean containsException(){
		return !validationExceptions.isEmpty();
	}
	
	public List<ValidationException> getExceptions(){
		return Collections.unmodifiableList(this.validationExceptions);
	}
}