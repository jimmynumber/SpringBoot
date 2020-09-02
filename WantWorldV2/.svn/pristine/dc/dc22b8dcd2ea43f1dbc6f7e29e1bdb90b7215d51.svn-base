package com.want.wantworld.interceptor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import com.want.wantworld.utils.EncryptUtils;

@Component
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String userName ;
	private static String passWord;

	@SuppressWarnings("static-access")
	@Value("${webservices.interceptor.username}")
    public void setUserName(String userName) {
		this.userName = userName;
	}

	@SuppressWarnings("static-access")
	@Value("${webservices.interceptor.password}")
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	private SAAJInInterceptor saa = new SAAJInInterceptor();
	
	public AuthInterceptor() {
		// 定义在什么阶段进行拦截
		super(Phase.PRE_PROTOCOL);
	}

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
    	logger.debug("userName:{},passWord:{}",userName,passWord);
    	 SOAPMessage mess = soapMessage.getContent(SOAPMessage.class);
         if(mess==null){
             saa.handleMessage(soapMessage);
             mess=soapMessage.getContent(SOAPMessage.class);
         }
         SOAPHeader header =null;
         try {
             header = mess.getSOAPHeader();
         } catch (SOAPException e) {
             logger.error("getSOAPheader error:{}",e.getMessage(),e);
             e.printStackTrace();
         }
         if(header==null){
             throw new Fault(new IllegalAccessException("找不到Header,无法验证用户信息"));
         }
         NodeList username = header.getElementsByTagName("userName");
         NodeList password = header.getElementsByTagName("passWord");
         if(username.getLength()<1){
             throw new Fault(new IllegalAccessException("找不到userName,无法验证用户信息"));
         }
         if(password.getLength()<1){
             throw new Fault(new IllegalAccessException("找不到passWord,无法验证用户信息"));
         }
         String userName = username.item(0).getTextContent().trim();
         String passWord = password.item(0).getTextContent().trim();
         // 解密后密码
         try {
			passWord=new EncryptUtils().decrypt(passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
         if(AuthInterceptor.userName.equals(userName)&&AuthInterceptor.passWord.equals(passWord)){
             logger.debug("admin auth success");
         }else {
             SOAPException soapException = new SOAPException("认证错误");
             logger.debug("admin auth failed");
             throw new Fault(soapException);
         }

    }
}
