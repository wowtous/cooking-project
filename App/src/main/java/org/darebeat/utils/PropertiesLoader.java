package org.darebeat.utils;

import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class PropertiesLoader {
    private static PropertiesLoader propertiesLoader;
	private static Properties pro;

    public static PropertiesLoader getInstance(String filepath){
        if (null == propertiesLoader) propertiesLoader = new PropertiesLoader();
        try {
            pro = new Properties();
            FileInputStream fis = new FileInputStream(new File(filepath));
            pro.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesLoader;
    }

	public String getValue(String key){
        return pro.getProperty(key);
    }
}
