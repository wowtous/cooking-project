package org.darebeat.utils;

import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class PropertiesLoader {
	private static Properties pro;

	static{
        try {
            pro = new Properties();
            FileInputStream fis = new FileInputStream(new File("config.properties"));
            pro.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static String getValue(String key){
        String value = pro.getProperty(key);
        return value;
    }
}
