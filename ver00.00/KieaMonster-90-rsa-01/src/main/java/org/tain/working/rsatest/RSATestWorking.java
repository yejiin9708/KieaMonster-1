package org.tain.working.rsatest;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;
import org.tain.utils.CipherUtils;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RSATestWorking {

	/*
	 *  [ test00 ]
	 *  [ keySize = 1024 ]
	 *  >>>>> 0. 평 문 plainText: 암호화 할 문자열 입니다.
	 *  >>>>> 1. 암호화 encrypted: eQ53lGjAGBAAthwmOAS9DMAsuQb7hG1PzJpXEQZo0bFw0dWEdZmrTMmOdmQIGeKx/o0lmPnvQ+95CQgorGkHxA/j/hfeggmETgmmmNgDK4I8rQVvJKAtPpK5sUACkTfy9ub9uzwa5bp756Cn+h9hXs+e/La8mUXunN56WRQk8vw=
	 *  >>>>> 1. 복호화 decrypted: 암호화 할 문자열 입니다.
	 *  >>>>> 2. Base64 Public  Key: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCE3j7gU9REc06ZiLrI9sW5xFMUTfVdRpjQTfVq9uQx7zdJluWLEMQE/+leLYXsCUMMxD6TspJv0FqDVQk+SGk11I3103g8JL/ckDLnxQrsIH29/WkKBlN6s4d8cgZr6ia2RQUN+8WZeJVFJOb8FGph1jk7OofvpIg17W0wfim2RwIDAQAB
	 *  >>>>> 2. Base64 Private Key: MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAITePuBT1ERzTpmIusj2xbnEUxRN9V1GmNBN9Wr25DHvN0mW5YsQxAT/6V4thewJQwzEPpOykm/QWoNVCT5IaTXUjfXTeDwkv9yQMufFCuwgfb39aQoGU3qzh3xyBmvqJrZFBQ37xZl4lUUk5vwUamHWOTs6h++kiDXtbTB+KbZHAgMBAAECgYAhjIyL0B70+biLA/9F5ymd8niX0+d0mfLUBhtF2vlbsm9fYX6sUptsN8IRSaVbKCuJ+bNjT9+x5AY2yy3+UAJge+xt5BMRTQFAetH+/ST8y3SGFrzx1+QqS+iM1vxYIZV/Oe0EXeUXq4Qh9uYokNK+U5CfEzgTA9gaAOilEMmhgQJBAMRwmdIleR7USYGbwIZNA0FyCbazTGKKtk65mJms2AT+nWkdclzwwkHcFslLWaCIsbDn+9/GszPWApIbzotXvtcCQQCtJ0tKJFSA1mNoZxshOaQNnxRzN46o089YcivhnRpkyoVoD3xDiPNsAW9JGo9Y6Y9gDu66AJE3x5UsnnSNawYRAkBA4B8s9AtITzvnfhTbRdvpZUwn9pcnhEApR8pKFndRTvWVnZADec/uCE3ONQBosDxe/H6GrQzJ9cfrkRtpkZUJAkBlPzG+A6pJv5JW7NsnPV4Y/OjnShgnzdpS/Ed+X++b+fOx2kQt9Tlo8RnSZC5nrgJBB9o9drFzLUej1G+QdYERAkAZquUF9wgwXwB5WJeiLJBYrRWfAPef5BFh3+y9kFGpbjf0WGHaGDZf4Nq9nVjdtIdUzbsqJFp4wqoB7O064dM7
	 *  >>>>> 2. encrypted2: G1TeG9nVflgSt3z77xFDUdI/e2+rIBPK5ych3J/6UX7ai/3jYkpH8K/DmlfYn0Ho82X81sE5qx4l3BLi6k6+N8kAkD5/WfwTBnVAgcYpGX9sAUlxdwqWxa8RoSH6p6c4OKFFauOsog2mkpNKe2uSnmPKgTTk3WLzJAOlRI6EIBc=
	 *  >>>>> 2. decrypted2: 암호화 할 문자열 입니다.
     *  
	 *  [ keySize = 2048 ]
	 *  >>>>> 0. 평 문 plainText: 암호화 할 문자열 입니다.
	 *  >>>>> 1. 암호화 encrypted: EstruhJ0DpYJmVrsUcP5tDLwEB6Jr7PgUKRR0z34vPNkDGZgyZ6YBYMUh9UCq3Fu5kSSMptDON0sD2s694aXx//13ItFrcvQIwdVqo7ZHiHBmyCflKtsq2dshb3uEMPvjcGSUafPuyaHwV1qmPa1j3cq8uVV+8IidrQyiDTy9oYWGiWlwuHD2/XAOUcm6PHPoMIFMqL2KVh7XI/Qh7CA7Wgt55pM0A6izK8rrosvGOdcqKGiJ0N3qtSjnEqBAKzSEcuXb7E2UosqrH5xOo5tipy5TYGWryAa9P9icQpISUcEexSC5VMvBuIRDVIPqS5Tn/huKaRq2f1FgzPOpF6gYQ==
	 *  >>>>> 1. 복호화 decrypted: 암호화 할 문자열 입니다.
	 *  ##### 2. Base64 Public  Key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAydIHuHQg9TarLcUxuyZaRn2ddV7KvnY+8G0XPYKoaF2iew6h6LR+AmCTOLFdUzm8VRh2n9rRFVTHONao74CV+AO38hL6Kc8EPIibjLNzS9Ie5mnXz1kHcleFBvMkTLxjFA5tZYJKdkirznePsQYJS8ZkmkwDaLZmM3ZmT5x1uQrj/wR8Wf5KvmI9Paxh8Oth6pAjpKlvXhvRA+lVujQ59dsrjbNddkCS6GP7hqx+9+Zuen6EOoyjpHXWFTiPcawYVCVR84y5kRhowQC5rH+WRzhBxZakh6EC+kT3xYtjyjv7G+xtLrgalWr2156frNFpVv2bZnFrhtAjSF3sdCWZqQIDAQAB
	 *  ##### 2. Base64 Private Key: MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJ0ge4dCD1NqstxTG7JlpGfZ11Xsq+dj7wbRc9gqhoXaJ7DqHotH4CYJM4sV1TObxVGHaf2tEVVMc41qjvgJX4A7fyEvopzwQ8iJuMs3NL0h7madfPWQdyV4UG8yRMvGMUDm1lgkp2SKvOd4+xBglLxmSaTANotmYzdmZPnHW5CuP/BHxZ/kq+Yj09rGHw62HqkCOkqW9eG9ED6VW6NDn12yuNs112QJLoY/uGrH735m56foQ6jKOkddYVOI9xrBhUJVHzjLmRGGjBALmsf5ZHOEHFlqSHoQL6RPfFi2PKO/sb7G0uuBqVavbXnp+s0WlW/ZtmcWuG0CNIXex0JZmpAgMBAAECggEATn8ERbfVQcq7lZfHGfV200f0QDxYOvAe1sB3c5MoET+p/JbaXnolMKODX1+M3tvr5mUXbmFbRSKuQesr1ct7iPVZTCwZZL2B9lFjvrvlMi5Nmmy8ZYdJIC3uLZ/QFJCJMLnTztF4SL1HJYzXNJEWeIR3iJPcF5Qp+AYrEPfXydpDRD0wwBKxHOfj5ak0YqdeMWg0NhkffDKV410r7YT+3mTADAFhLqyVL/Oi8u6ljI7zr4knfjLRBJU4ZlO9H0UhZSczJTRTxgH64f31IYC5gzfRF9RNPEDolh8qD/T71mWiHdpnPmyztYPy5+4R2bIYLOtPVqTWc9PUTDtj0fMuSQKBgQD0B0C1yOLmvKWx1hnSY6Yl9LCS/PJGHp71mRr2GkoqHpNUSrLinjwjNP951Xq6CMMtvrlsycWnQNzZ3h5DNLBnZZgM7cHiWXbvjDA6Mnshwt9P59VbVFUQOXRP8lK1myVEgPZF7KXWfUsglcVxYfNcyIW634c3MqkoGBO/LWhXCwKBgQDTuLBsL5LP2q27ncEZqq3lPXT4idqT1TC1g82OnqO8Xk37nEBIOczCK3XVASRYxwAPJ9NnG/1oVsKS97BZGnjsLwC5BFn3RY2UPiVpXfVR6KpKjQOezXpMjHj/P+C5eonIWMB4wpBi9vceJbe9ROb9Hru53IA30Sqn3rjup2JymwKBgAJqo/ho4d5ZFeb58M2FkRqlHq6F9Lw/B2H1PzAauItMPDdEehsGaCANIU6Kj6mR9qTYU6vBH8T1Pnql2XTe67RKbEQMgrQEIMpkgAHDiDagYGfBKvq5jhIYD4/PHYSQ4cCY6lds7YN/QCQ1d3wU9T0A+1oLZqJkTQqJmoB1KahbAoGBAL0VuupD6pgbswFWOHjVyAxtwKOfakiRI7lYhyKSZ6T5jCfkOjzG8imPauLa7D0lLNZcOR9jDgQ7R/a9ow9Q5yH4MGsMidSel30Hq53Yvhly83M1UUBoPotTAVTlFaxQ1fDJ4st26ECaU8Tm5pvwh2MUKZAqb5BqC/B0pzDs6K11AoGANJfpCJFEJBKvz7jXD4cv+PUyDwzHpsGzXbQAskyXjI+otOctnGfv0dhqSriCOHcgFn3qDOLApUr/BVU9wIF40QufOpBzO4eXdAKOYuEp5rD4mKcxA89Bxi4VwgsAM1WFrQuexKGdXdywC8YPL/xaPkqKoZfy/yXn9kDRaeRXZkQ=
	 *  >>>>> 2. encrypted2: FQKCRZrl22/eK9LJgMR17t9hgisYx+rh/g2Aj61nXCdRbjeObw3J3cDzrEOdx7gmmK+okBKXkjO8pEbqgTHHTPSwk04Hd8hvZHg4jmUq1sg38tOEEBlrC4RdvUpx/si6pFiNcODaBL0z/TKefKgZsAlQlu9sIQTQLiwQPTZviw+ZA6TmVrBEr8+zdT6k2qlL5ejWXKPUWD8Zdhr3+NBYL4l3OLRHcyyx0uNHOBPHnFlFABSb1R/hTOrjujcgESchNcsydZ9kIb7ax9Cc87OmThHS39xdAf57DnK2kvx4pTJFW06jNLhz3e1TZvnD5YQ7N92an1Yh9n1MXrRCoxLePA==
	 *  >>>>> 2. decrypted2: 암호화 할 문자열 입니다.
	 */
	public void test00() throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			// create KeyPair
			KeyPair keyPair = CipherUtils.generateRSAKeyPair();
			
			PublicKey publicKey = keyPair.getPublic();
			System.out.println(">>>>> a. publicKey.getAlgorithm(): " + publicKey.getAlgorithm());
			System.out.println(">>>>> a. publicKey.getFormat()   : " + publicKey.getFormat());
			System.out.println(">>>>> a. publicKey.toString()    : " + publicKey.toString());
			
			PrivateKey privateKey = keyPair.getPrivate();
			System.out.println(">>>>> b. publicKey.getAlgorithm(): " + privateKey.getAlgorithm());
			System.out.println(">>>>> b. publicKey.getFormat()   : " + privateKey.getFormat());
			System.out.println(">>>>> b. publicKey.toString()    : " + privateKey.toString());
			
			String plainText = "암호화 할 문자열 입니다.";
			System.out.println(">>>>> 0. 평 문 plainText: " + plainText);
			
			// Base64 인코딩된 암호화 문자열 입니다.
			String encrypted = CipherUtils.encryptRSA(plainText, publicKey);
			System.out.println(">>>>> 1. 암호화 encrypted: " + encrypted);
			
			// 복호화 합니다.
			String decrypted = CipherUtils.decryptRSA(encrypted, privateKey);
			System.out.println(">>>>> 1. 복호화 decrypted: " + decrypted);
			
			// 공개키를 Base64 인코딩한 문자열을 만듭니다.
			byte[] bytePublicKey = publicKey.getEncoded();
			String base64PublicKey = Base64.getEncoder().encodeToString(bytePublicKey);
			System.out.println(">>>>> 2. Base64 Public  Key: " + base64PublicKey);
			
			// 개인키를 Base64 인코딩한 문자열을 만듭니다.
			byte[] bytePrivateKey = privateKey.getEncoded();
			String base64PrivateKey = Base64.getEncoder().encodeToString(bytePrivateKey);
			System.out.println(">>>>> 2. Base64 Private Key: " + base64PrivateKey);
			
			// 문자열로부터 PrivateKey와 PubicKey를 얻습니다.
			PrivateKey priKey = CipherUtils.getPrivateKeyFromBase64String(base64PrivateKey);
			PublicKey pubKey = CipherUtils.getPublicKeyFromBase64String(base64PublicKey);
			
			// 공개키로 암호화 합니다.
			String encrypted2 = CipherUtils.encryptRSA(plainText, pubKey);
			System.out.println(">>>>> 2. encrypted2: " + encrypted2);
			
			// 개인키로 복호화 합니다.
			String decrypted2 = CipherUtils.decryptRSA(encrypted2, priKey);
			System.out.println(">>>>> 2. decrypted2: " + decrypted2);
		}
	}
	
	/*
	 * 
	 */
	public void test01() throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			/*
			    File file = new File("C:\\temp-ldi\\pubkey.txt");
			    FileWriter writer = new FileWriter(file);
			    file.createNewFile();
			    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			    generator.initialize(2048, new SecureRandom());
			    KeyPair keyPayr = generator.generateKeyPair();
			    writer.write(new BASE64Encoder().encode(keyPayr.getPublic().getEncoded()));
			    writer.flush();
			    writer.close();
			    file = new File("C:\\temp-ldi\\privkey.txt");
			    writer = new FileWriter(file);
			    file.createNewFile();
			    writer.write(new BASE64Encoder().encode(keyPayr.getPrivate().getEncoded()));
			    writer.flush();
			    writer.close();


			    File privfile = new File("C:\\temp-ldi\\privkey.txt");
			    File pubfile = new File("C:\\temp-ldi\\pubkey.txt");
			    FileReader reader = new FileReader(pubfile);

			    BufferedReader br = new BufferedReader(reader);
			    StringBuilder sb = new StringBuilder();
			    String s;
			    while ((s = br.readLine()) != null) {
			        sb.append(s);
			    }
			    br.close();
			    reader.close();
			    PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(sb.toString())));
			    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			    String encryptedText = new BASE64Encoder().encode(cipher.doFinal("PLAIN TEXT".getBytes("UTF-8")));
			    System.out.println("encrypted: " + encryptedText);
			    reader = new FileReader(privfile);
			    br = new BufferedReader(reader);
			    sb = new StringBuilder();
			    while ((s = br.readLine()) != null) {
			        sb.append(s);
			    }
			    br.close();
			    reader.close();
			    PrivateKey privateKey =  KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(sb.toString())));
			    cipher.init(Cipher.DECRYPT_MODE, privateKey);
			    System.out.println( new String(cipher.doFinal (new BASE64Decoder().decodeBuffer(encryptedText))));
			*/
		}
	}
	
	/*
	 * 
	 */
	public void test02() throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			/* server code */
			/* Key 발급 */
			PublicKey publicKey = null;    // 공개키
			PrivateKey privateKey = null;  // 개인키
		 
			SecureRandom secureRandom = new SecureRandom(); // random number generator(RNG) 
			 
			try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // RSA(1024, 2048), DiffieHellman(1024)
			keyPairGenerator.initialize(1024, secureRandom);
			KeyPair keyPair = keyPairGenerator.generateKeyPair(); // generate key pair
			 
			publicKey  = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			 
			System.out.println(publicKey.getAlgorithm());
			System.out.println(publicKey.getFormat()); // 공개키 표준
			System.out.println(publicKey.toString());
			 
			System.out.println();
			 
			System.out.println(privateKey.getAlgorithm());
			System.out.println(privateKey.getFormat()); // 개인키 표준
			System.out.println(privateKey.toString());
			 
			 
			@SuppressWarnings("unused")
			String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());   // Base64 인코딩
			String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded()); // Base64 인코딩
			System.out.println(privateKeyStr);
			 
			 
			} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			} 
			 
			/* 키발급 종료 */
			 
			/* client code */
			String plainText = "hello world!";
			String encryptedText = "";
			String decryptedText = "";
			 
			try {
			Cipher cipher = Cipher.getInstance("RSA");
			 
			// 개인키 이용 전자서명
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
			System.out.println("****encrypt****");
			System.out.println(encryptedText);
			System.out.println("****decrypt****");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			 
			// 공개키 이용 복호화
			decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText.getBytes())));
			System.out.println(decryptedText);
			 
			 
			 
			} catch (NoSuchAlgorithmException | NoSuchPaddingException |InvalidKeyException |IllegalBlockSizeException | BadPaddingException e) {
			    e.printStackTrace();
			}
		}
	}
}
