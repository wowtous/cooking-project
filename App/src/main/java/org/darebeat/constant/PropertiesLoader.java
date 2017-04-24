package org.darebeat.constant;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PropertiesLoader {
	private static String filepath = "/jdbc.properties";
    private static Properties pro;

	static{
        try {
            pro = new Properties();
            InputStream in = ClassLoader.class.getResourceAsStream(filepath);
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static String getValue(String key){
        String value = pro.getProperty(key);
        return value;
    }
}
