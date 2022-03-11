/*
 * Author Fabio Petricola 
 * */

package com.fabio.spy.config;

import java.util.Properties;

public class ConfigProperty {
	
	private static ConfigProperty configProperty;
	private Properties properties;
	
	private ConfigProperty() {
		properties=new Properties();
		properties.put("mail.smtp.host","");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.username", "");
        properties.put("mail.password", "");
	}
	
	public static ConfigProperty getInstance() {
		if(configProperty==null) {
			configProperty=new ConfigProperty();
		}
		return configProperty;
	}
	
   
	
	
	public static ConfigProperty getConfigProperty() {
		return configProperty;
	}

	public static void setConfigProperty(ConfigProperty configProperty) {
		ConfigProperty.configProperty = configProperty;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
}
