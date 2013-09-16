package br.com.orlandoburli.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Teste {

	public static void main(String[] args) {
		
//		System.out.println(Utils.encode64("1234".getBytes()));
		String texto = "1234";
		
		MessageDigest cript;
		try {
			cript = MessageDigest.getInstance("SHA-1");
	        cript.reset();
	        cript.update(texto.getBytes());
	        
	        System.out.println(cript.digest());
	        
	        System.out.println(Utils.encode64(cript.digest()));
	        
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
