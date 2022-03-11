package com.fabio.spy.mail;
/*
 * Author Fabio Petricola 
 * */
import java.io.File;

public interface SendEmailInterface {
	void sendMailConAttachment(File file) throws Exception;
}
