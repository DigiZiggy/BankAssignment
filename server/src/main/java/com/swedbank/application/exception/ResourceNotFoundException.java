package com.swedbank.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(Long id) {
		super("Account id not found : " + id);
	}

}
