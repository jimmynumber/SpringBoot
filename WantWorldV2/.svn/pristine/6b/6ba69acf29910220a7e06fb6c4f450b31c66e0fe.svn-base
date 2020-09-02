package com.want.wantworld.service.impl;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.want.wantworld.service.ICryptoService;

@Service
public class CryptoServiceImpl implements ICryptoService {
	private static final Logger logger = LoggerFactory.getLogger(CryptoServiceImpl.class);
	public static final String OUTPUT_STRING_BASE64_PRIVATE_KEY = "OUTPUT_STRING_BASE64_SSO_PRIVATE_KEY";
	public static final String OUTPUT_STRING_BASE64_PUBLIC_KEY = "OUTPUT_STRING_BASE64_SSO_PUBLIC_KEY";
	private static final String CIPHER_INSTANCE = "RSA";
	@Value("${cryptoKeyInfo.base64PublicKey}")
	private String Base64PublicKey ;
	@Value("${cryptoKeyInfo.base64PrivateKey}")
	private String Base64PrivateKey ;
	
	@Override
	public String getEncryptText(String base64Text) {
		// 加密
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(CIPHER_INSTANCE);
			PublicKey key = generatePublicKey(Base64PublicKey);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(cipher.doFinal(base64Text.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getEncryptText(String base64Text, String base64PublicKey) {
		// 加密
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(CIPHER_INSTANCE);
			PublicKey key = generatePublicKey(base64PublicKey);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(cipher.doFinal(base64Text.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getDecryptText(String encryptBase64Text) {
		try {
			// 解密
			encryptBase64Text = encryptBase64Text.replace(" ", "+");
			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
			PrivateKey key = generatePrivateKey(Base64PrivateKey);
			cipher.init(Cipher.DECRYPT_MODE, key);
			Base64.Decoder decoder = Base64.getDecoder();
			return new String(cipher.doFinal(decoder.decode(encryptBase64Text.getBytes("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getDecryptText(String encryptBase64Text, String base64PrivateKey) {
		try {
			// 解密
			encryptBase64Text = encryptBase64Text.replace(" ", "+");
			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
			PrivateKey key = generatePrivateKey(base64PrivateKey);
			cipher.init(Cipher.DECRYPT_MODE, key);
			Base64.Decoder decoder = Base64.getDecoder();
			return new String(cipher.doFinal(decoder.decode(encryptBase64Text.getBytes("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 產生私鑰
	private PrivateKey generatePrivateKey(String base64privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		Base64.Decoder decoder = Base64.getDecoder();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoder.decode(base64privateKey));
		KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_INSTANCE);
		PrivateKey privateKey = keyFactory.generatePrivate(spec);
		return privateKey;
	}

	// 產生公鑰
	private PublicKey generatePublicKey(String base64PublicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		Base64.Decoder decoder = Base64.getDecoder();
		X509EncodedKeySpec spec = new X509EncodedKeySpec(decoder.decode(base64PublicKey));
		KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_INSTANCE);
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}

	@Override
	public Map<String, Object> createRSAKey() {
		logger.info("createRSAKey start...");
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance(CIPHER_INSTANCE);
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			Map<String, Object> keyMap = new HashMap<String, Object>();
			Base64.Encoder encoder = Base64.getEncoder();

			String base64PrivateKey = encoder.encodeToString(privateKey.getEncoded());
			String base64PublicKey = encoder.encodeToString(publicKey.getEncoded());

			keyMap.put(OUTPUT_STRING_BASE64_PRIVATE_KEY, base64PrivateKey);
			keyMap.put(OUTPUT_STRING_BASE64_PUBLIC_KEY, base64PublicKey);
			logger.info("createRSAKey end...");
			return keyMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		CryptoServiceImpl obj = new CryptoServiceImpl();
		Map<String, Object> map = obj.createRSAKey();
		for (Map.Entry<String, Object> it : map.entrySet()) {
			System.out.println("key:" + it.getKey() + ",value:" + it.getValue());
		}
		String Base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCs82wdtaImVQrC7waQ7a98pA7+otyfQGnpNBMzxOOqZww60FCEPqb9D89DPJ80CAYXy5eDiSoFpzVf+33otRba1jr1WuTTRlnpWhzakthewHRZ91cYVPeXZEkYqpcMTfIf3H4gZTCKdI1yHf7wfFkZL0F7lZkFaVwtQF3XO7InhwIDAQAB";
		String Base64PrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKzzbB21oiZVCsLvBpDtr3ykDv6i3J9Aaek0EzPE46pnDDrQUIQ+pv0Pz0M8nzQIBhfLl4OJKgWnNV/7fei1FtrWOvVa5NNGWelaHNqS2F7AdFn3VxhU95dkSRiqlwxN8h/cfiBlMIp0jXId/vB8WRkvQXuVmQVpXC1AXdc7sieHAgMBAAECgYBSwXuHT8UR8HlgwzzyFjl1O49NOqK0tP64rslNaTXC8OICNnGAhfYjevAi7RwEqp43vpx2PSjVYagBkHYz5orTSrQDZomTNShwkaYNXnHYgg0V5cxrzcBoh9TqqsgQof3HvhqrfWROFm5z6zCeKBIklBtrzFEA7iC3tq7HykJtwQJBAPAiZ0kZG/w0RzBxEZfSNV5FsiqML1R9LH7V3mP6yAjxG8ysSrjU4Lv71cnRKx9ZEI9cl+IPgSf41faMHDmSuucCQQC4YK/g2wp+lY6/yw81ErnH4wM9ld/+/7WVGd2ZCBQ39I1l0ay5eoA4QhyD8oaBtYUXgY5zpqqjpampyR9OFzphAkEAl67TmSvgLoYDNckmOMVSarMRoKieOPs8gP2tFXBSrLtIORfXtzrYnkWsPGgAmjOTL0tosSXkCpC27p6AManKnwJAXs/xgXqCEggMaRodjtg7Ssu1LeU1U6bhFHRBnOEnrIKId7Rz0sloyAVPLpb3jhnu/+ByhYxOMWsOLS4sd/a/4QJAHUUG6LyYauj4f6E2XRZCMfcjbKhO1ozjX2uI6mKqRile9Sqi2ZirvEPmkPfUTi8yo5O9vTrAfljlv13DoCie3w==";

		String str = "00300409";
		System.out.println("----111-----:" + str);
		str = obj.getEncryptText(str,Base64PublicKey);
		System.out.println("----222-----:" + str);
		str = obj.getDecryptText(str,Base64PrivateKey);
		System.out.println("----333-----:" + str);

		str = "P@ssw0rd";
		str = obj.getEncryptText(str,Base64PublicKey);
		System.out.println("----444-----:" + str);
		str = obj.getDecryptText(str,Base64PrivateKey);
		System.out.println("----555-----:" + str);
	}

}
