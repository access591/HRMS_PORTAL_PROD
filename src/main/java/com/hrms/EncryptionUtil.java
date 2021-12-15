package com.hrms;

import java.util.Base64;

public class EncryptionUtil {
	public static String encode(final String input)
    {
        String pass = Base64.getEncoder().encodeToString(input.getBytes());
		return pass;
    }
	public static String decode(final String input)
    {
	byte[] actualDate = Base64.getDecoder().decode(input);
       String pass = new String(actualDate);
		return pass;
    }
}
