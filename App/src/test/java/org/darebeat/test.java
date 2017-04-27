package org.darebeat;

import java.net.*;
import java.io.*;

public class test{
    public static void main(String args[]) throws Exception {
        URL u=new URL("http://api.k780.com:88/?app=phone.get&phone=13800138000&format=json");
        InputStream in=u.openStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        System.out.println(new String(b,"utf-8"));
    }
}