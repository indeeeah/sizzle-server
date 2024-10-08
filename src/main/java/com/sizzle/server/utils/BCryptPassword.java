package com.sizzle.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPassword {
	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

	public static String hash(String plainPassword) {
		return encoder.encode(plainPassword);
	}

	public static boolean check(String plainPassword, String hashedPassword) {
		return encoder.matches(plainPassword, hashedPassword);
	}
}
