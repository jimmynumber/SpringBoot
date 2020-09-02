package com.want.wantworld.service;
 
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface ICryptoService {
	/**
	 * @see 取得加密結果文字
	 * @param base64Text base64未加密文本
	 * @return
	 */
	public String getEncryptText(String base64Text);
	
	/**
	 * @see 取得加密結果文字
	 * @param base64Text base64未加密文本
	 * @return
	 */
	public String getEncryptText(String base64Text,String base64PublicKey);
	
	

	/**
	 * @see 取得解密結果文字
	 * @param encryptBase64Text base64加密文本
	 * @return
	 */
	public String getDecryptText(String encryptBase64Text);
	
	/**
	 * @see 取得解密結果文字
	 * @param encryptBase64Text base64加密文本
	 * @return
	 */
	public String getDecryptText(String encryptBase64Text,String base64PrivateKey);

	/**
	 * @see 產生RSA金鑰
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> createRSAKey();

}
