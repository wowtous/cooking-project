package org.darebeat;

import org.darebeat.bean.City;
import org.darebeat.bean.MailSender;
import org.darebeat.utils.PropertiesLoader;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    static final String JDBC_DRIVER = PropertiesLoader.getValue("JDBC_DRIVER");
    static final String JDBC_URL = PropertiesLoader.getValue("JDBC_URL");
    static final String USERNAME = PropertiesLoader.getValue("USERNAME");
    static final String PASSWORD = PropertiesLoader.getValue("PASSWORD");

    public static void main( String[] args ) {
        App p = new App();
        // p.test();
        // p.test1(); // just for test database connection
        // p.test2(); // test class wraper
        // p.test3(); // get the city length and print the top 10
        p.test4();
    }

    public void test(){
        System.out.println(PropertiesLoader.getValue("JDBC_DRIVER"));
        System.out.println(PropertiesLoader.getValue("JDBC_URL"));
        System.out.println(PropertiesLoader.getValue("USERNAME"));
        System.out.println(PropertiesLoader.getValue("PASSWORD"));
    }

    public void test1(){
        Connection conn = null;
        Statement stmt = null;

        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);

            // 执行查询
            System.out.println("实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT cityId,cityName from dimcity";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("cityId");
                String name = rs.getString("cityName");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 城市名称: " + name);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public void test2(){
        Connection conn = null;
        Statement stmt = null;

        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);

            // 执行查询
            System.out.println("实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT cityId,cityName from dimcity";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                City c = new City(rs.getInt("cityId"),rs.getString("cityName"));
                System.out.println(c.toString());
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public void test3(){
        Connection conn = null;
        Statement stmt = null;

        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);

            // 执行查询
            System.out.println("实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT cityId,cityName from dimcity";
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<City> cts= new ArrayList<City>();
            // 展开结果集数据库
            while(rs.next()){
                City c = new City(rs.getInt("cityId"),rs.getString("cityName"));
                cts.add(c);
            }
            Collections.sort(cts);
            int i=0;
            for(City c : cts){
                if(++i>10) break;
                System.out.println(c.toString());
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public void test4(){
        final String MAIL_FROM = PropertiesLoader.getValue("MAIL_FROM");
        final String MAIL_PASS = PropertiesLoader.getValue("MAIL_PASS");
        final String MAIL_TO = PropertiesLoader.getValue("MAIL_TO");

        MailSender sms = MailSender.getSender(MAIL_FROM,MAIL_PASS);
        String recipients[] = MAIL_TO.split("[\\s,;':\\|]+");
        List<String> rss = new ArrayList<String>();
        for (String recipient : recipients) {
            rss.add(recipient);
        }
        try {
            sms.send(rss, "main subject for up","Hi content");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
