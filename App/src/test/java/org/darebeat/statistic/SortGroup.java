package org.darebeat.statistic;

import org.darebeat.bean.City;
import org.darebeat.utils.PropertiesLoader;

import java.sql.*;
import java.util.*;

/**
 * Created by darebeat on 4/27/17.
 * 分组排序
 */
public class SortGroup {
    public static void main(String[] args) {
        String filepath = SortGroup.class.getResource("/config.properties").getPath();;
        PropertiesLoader p = PropertiesLoader.getInstance(filepath);

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(p.getValue("JDBC_DRIVER"));
            conn = DriverManager.getConnection(p.getValue("JDBC_URL"),p.getValue("USERNAME"),p.getValue("PASSWORD"));
            stmt = conn.createStatement();
            String sql = "select cityId,cityName,provinceId from dimcity";

            ResultSet rs = stmt.executeQuery(sql);
            HashMap<Integer,List> hm = new HashMap<Integer, List>();

            while (rs.next()){
                int id  = rs.getInt("cityId");
                String name = rs.getString("cityName");
                int provinceId = rs.getInt("provinceId");

                List ls;
                if (hm.get(provinceId) != null){
                    ls = hm.get(provinceId);
                    ls.add(new City(id,name));
                }else{
                    ls = new ArrayList();
                    ls.add(new City(id,name));
                }
                hm.put(new Integer(provinceId),ls);
            }

            for (Integer s : hm.keySet()){
                List l = hm.get(s);
                Collections.sort(l);
                System.out.println("ProvinceID="+s+" city="+l.toString());
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
