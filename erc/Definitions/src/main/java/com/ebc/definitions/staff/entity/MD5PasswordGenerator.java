package com.ebc.definitions.staff.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordGenerator {

	public static String passwordGenerator(String pass) {
		try {
			MessageDigest messageDigestNesnesi = MessageDigest
					.getInstance("MD5");
			messageDigestNesnesi.update(pass.getBytes());
			byte messageDigestDizisi[] = messageDigestNesnesi.digest();
			StringBuffer sb32 = new StringBuffer();
			for (int i = 0; i < messageDigestDizisi.length; i++) {
				sb32.append(Integer.toString(
						(messageDigestDizisi[i] & 0xff) + 0x100, 32));
			}
			pass = String.valueOf(sb32);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return pass;
	}
	
	public static void main(String[] args){
		
		
		
	}

}