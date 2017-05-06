package org.darebeat.effective;

import org.darebeat.utils.PropertiesLoader;

import java.sql.*;

/**
 * Created by darebeat on 5/6/17.
 */
public class JdbcTest {
    public static void main(String[] args) {
        String filepath = JdbcTest.class.getClass().getResource("/config.properties").getPath();
        PropertiesLoader pl = PropertiesLoader.getInstance(filepath);

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(pl.getValue("JDBC_DRIVER"));
            conn = DriverManager.getConnection(pl.getValue("JDBC_URL"),pl.getValue("USERNAME"),pl.getValue("PASSWORD"));
            stmt = conn.createStatement();

            String sql = "SELECT cityId,cityName from dimcity";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                // 通过字段检索
                int id  = rs.getInt("cityId");
                String name = rs.getString("cityName");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 城市名称: " + name);
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
