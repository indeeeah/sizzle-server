package com.sizzle.server.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

public class UuidV7 {
	private static final SecureRandom random = new SecureRandom();

	public static UUID randomUuid() {
		byte[] value = randomBytes();

		ByteBuffer buf = ByteBuffer.wrap(value);

		long high = buf.getLong();
		long low = buf.getLong();

		return new UUID(high, low);
	}

	private static byte[] randomBytes() {
		// random bytes
		byte[] value = new byte[16];
		random.nextBytes(value);

		// current timestamp in ms
		ByteBuffer timestamp = ByteBuffer.allocate(Long.BYTES);
		timestamp.putLong(System.currentTimeMillis());

		// timestamp
		System.arraycopy(timestamp.array(), 2, value, 0, 6);

		// version and variant
		value[6] = (byte) ((value[6] & 0x0F) | 0x70);
		value[8] = (byte) ((value[8] & 0x3F) | 0x80);

		return value;
	}
}