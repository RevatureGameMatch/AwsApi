package com.revature.g2g.aws.services.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.aspectj.weaver.ast.Test;

public class PropertiesSingleton {
	private static Properties properties;
	private static LoggerSingleton loggerSingleton;
	private PropertiesSingleton() {
	}
	public static Properties getPropValues(){
		if(loggerSingleton == null) {
			loggerSingleton = new LoggerSingleton();
		}
		if(properties == null) {
			Properties newProperties = new Properties();
			String file = "/config.properties";
			try (InputStream inputStream = Test.class.getResourceAsStream(file)){
				newProperties.load(inputStream);
				properties = newProperties;
			} catch (FileNotFoundException e) {
				String relPath = new File(".").getAbsolutePath();
				loggerSingleton.getExceptionLogger().warn("properties file \"" + relPath.substring(0, relPath.length() - 1) + file + "\" not found",e);
			} catch (IOException e) {
				loggerSingleton.getExceptionLogger().warn("Properties retrieval failed: ", e);
			}
		}
		return properties;
	}
}
