package com.iiht.forum.postmicro.exception;

public class PostNotFoundException extends RuntimeException{
	public PostNotFoundException(String message) {
		super(message);
	}
}
