package org.darebeat;

import java.sql.*;
import org.darebeat.constant.PropertiesLoader;

public class App {
    static final String JDBC_DRIVER = PropertiesLoader.getValue("JDBC_DRIVER");
    static final String JDBC_URL = PropertiesLoader.getValue("JDBC_URL");
    static final String USERNAME = PropertiesLoader.getValue("USERNAME");
    static final String PASSWORD = PropertiesLoader.getValue("PASSWORD");

    public static void main( String[] args ) {
        App p = new App();
        p.test();
        p.test1();
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
            System.out.println(" 实例化Statement对...");
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
}