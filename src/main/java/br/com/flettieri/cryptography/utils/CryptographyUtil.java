package br.com.flettieri.cryptography.utils;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class CryptographyUtil {
	
	public static String encryptSHA512(String input) {
		return DigestUtils.sha512Hex(input);
	}

}
