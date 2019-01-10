package Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public static String encrypt(String password) {
		try { 
			byte[] pDigest = MessageDigest.getInstance("MD5").digest(password.getBytes()); 
			return new BigInteger(1, pDigest).toString(16);
		} catch (NoSuchAlgorithmException e) { System.out.println("Error: " + e); e.printStackTrace(); return null;}
	}
}