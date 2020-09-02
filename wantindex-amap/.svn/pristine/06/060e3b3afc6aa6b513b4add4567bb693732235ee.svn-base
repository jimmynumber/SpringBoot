/**
 * -------------------------------------------------------
 * @FileName：SendMailUtils.java
 * @Description：简要描述本文件的内容
 * @Author：Dirk.Lee
 * @Copyright  www.want-want.com  Ltd.  All rights reserved.
 * 注意：本内容仅限于旺旺集团内部传阅，禁止外泄以及用于其他商业目的
 * -------------------------------------------------------
 */
package com.want.amap.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

public class SendMailUtils {
	private static SendMailUtils instance;

	/**
	 * @return the instance
	 */
	public static SendMailUtils getInstance() {
		if (instance == null) {
			instance = new SendMailUtils();
		}
		return instance;
	}

	public void send(String host, String user, String password, String from, String[] emails, String subject, String text,
			File file) {
		Properties properties = new Properties();
		properties.put("mail.host", host);
//		properties.put("mail.smtp.auth", "true");
		properties.put("mail.transport.protocol", "smtp");

		Authenticator authenticator = new SimpleAuthenticator(user, password);
		Session session = Session.getInstance(properties, authenticator);
		MimeMessage mimeMsg = new MimeMessage(session);
		try {
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);
			messageBodyPart.setText(text);
			if (file != null) {
				DataSource source = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(file.getName());
			}

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			mimeMsg.setSubject(subject);
			mimeMsg.setFrom(new InternetAddress(from));
			InternetAddress[] addresses = new InternetAddress[emails.length];
			for (int i = 0;i < emails.length;i++) {
				addresses[i] = new InternetAddress(emails[i]);
			}
			mimeMsg.setRecipients(RecipientType.TO, addresses);
//			mimeMsg.setContent(multipart);
			 mimeMsg.setContent(text, "text/html;charset=utf-8");
			Transport.send(mimeMsg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void send(String host, String user, String password, String from, String[] to, String[] cc, String subject,
			String text, String[] fileName, byte[][] bytes) throws UnsupportedEncodingException {
		Properties properties = new Properties();
		properties.put("mail.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.transport.protocol", "smtp");

		Authenticator authenticator = new SimpleAuthenticator(user, password);
		Session session = Session.getInstance(properties, authenticator);
		MimeMessage mimeMsg = new MimeMessage(session);
		try {
			mimeMsg.setSubject(subject);
			mimeMsg.setFrom(new InternetAddress(from));
			for (int i = 0; i < to.length; i++) {
				mimeMsg.addRecipient(RecipientType.TO,
						new InternetAddress(MimeUtility.encodeText(to[i].substring(0, to[i].indexOf("<") - 1))
								+ to[i].substring(to[i].indexOf("<"), to[i].length())));
			}
			for (int i = 0; i < cc.length; i++) {
				mimeMsg.addRecipient(RecipientType.CC,
						new InternetAddress(MimeUtility.encodeText(cc[i].substring(0, cc[i].indexOf("<") - 1))
								+ cc[i].substring(cc[i].indexOf("<"), cc[i].length())));
			}

			Multipart multipart = new MimeMultipart();
			if (text != null) {
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(text,  "text/html;charset=UTF-8");
				multipart.addBodyPart(messageBodyPart);
			}
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i] != null) {
					DataSource source = new ByteArrayDataSource(bytes[i], "application/excel");
					BodyPart dataBodyPart = new MimeBodyPart();
					dataBodyPart.setDataHandler(new DataHandler(source));
					dataBodyPart.setFileName(MimeUtility.encodeText(fileName[i]));
					multipart.addBodyPart(dataBodyPart);
				}
			}
			mimeMsg.setContent(multipart);

			Transport.send(mimeMsg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
