package com.te.librarybookrent.book.exception;

@SuppressWarnings("serial")
//The @SuppressWarnings("serial") annotation is typically used on a class declaration to
//inform the compiler that the class intentionally does not define a serialVersionUID field.
public class SameBookException extends RuntimeException {
	
	public SameBookException(String message) {
		super(message);
	}
	

}
