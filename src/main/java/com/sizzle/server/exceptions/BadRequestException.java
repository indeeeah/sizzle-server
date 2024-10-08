package com.sizzle.server.exceptions;

import java.io.Serial;

public class BadRequestException extends Exception {

	@Serial
	private static final long serialVersionUid = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
