package br.com.orlandoburli.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Criptografia {

	public static void main(String[] args) {
		// Data CNPJ N. de usuarios
		String dados = "31/03/2013 09492164000158 00050";
		//String dados = "31/12/2012 11111111111111 00050";
		//String dados = "31/12/2012 05020930000120 00050";
		//String dados = "ENTERPRISE EDITION";
		
		String textoCripto = null;
		String key = "4547567786786786783257723";
		try {
			textoCripto = Criptografia.newInstance(key).cripto(dados);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(textoCripto);
		
		dados = null;
		try {
			dados = Criptografia.newInstance(key).decripto(textoCripto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dados);
		
	}

	private Cipher cipher;
	private byte[] encryptKey;
	private KeySpec keySpec;
	private SecretKeyFactory secretKeyFactory;
	private SecretKey secretKey;

	/**
	 * Method that create a new instance of class.
	 * @param key 
	 * 
	 * @return
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static Criptografia newInstance(String key) throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		return new Criptografia(key);
	}

	/**
	 * Default Constructor.
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 */
	private Criptografia(String key) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidKeySpecException {
		
		encryptKey = key.getBytes("UTF-8");
		cipher = Cipher.getInstance("DESede");
		keySpec = new DESedeKeySpec(encryptKey);
		secretKeyFactory = SecretKeyFactory.getInstance("DESede");
		secretKey = secretKeyFactory.generateSecret(keySpec);
	}

	/**
	 * Method that encrypts a value.
	 * 
	 * @param value
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public String cripto(String value) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] cipherText = cipher.doFinal(value.getBytes("UTF-8"));
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(cipherText);
	}

	/**
	 * Methot that decrypts a value.
	 * 
	 * @param value
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public String decripto(String value) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		BASE64Decoder dec = new BASE64Decoder();
		byte[] decipherText = cipher.doFinal(dec.decodeBuffer(value));
		return new String(decipherText);
	}
}