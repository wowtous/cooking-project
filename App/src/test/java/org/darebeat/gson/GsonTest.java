package org.darebeat.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.darebeat.bean.Address;
import org.darebeat.bean.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darebeat on 4/26/17.
 */
public class GsonTest {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        System.out.println(GsonTest.class.getResource("").getPath());
        System.out.println(GsonTest.class.getResourceAsStream(""));
        System.out.println(GsonTest.class.getResource("/").getPath());
        System.out.println(GsonTest.class.getResource("/employee.json").getPath());
        System.out.println(GsonTest.class.getClassLoader().getResource("").getPath());
    }

    public static void test1() {
        String sTotalString = "{\"list\": [" +
                "{\"id\":\"1\",\"name\":\"Tom\",\"age\":\"12\"}," +
                "{\"id\":\"2\",\"name\":\"Marry\",\"age\":\"18\"}" +
                "]}";
        Gson gson = new Gson();
        GsonDataAnalysis gda = gson.fromJson(sTotalString, GsonDataAnalysis.class);
        for(int i = 0; i < gda.getList().size(); i ++) {
            System.out.print("ID号：" + gda.getList().get(i).getId() + " ");
            System.out.print("姓 名：" + gda.getList().get(i).getName() + " ");
            System.out.println("年 龄：" + gda.getList().get(i).getAge());
        }
    }

    public static void test2(){
        Employee emp = createEmployee();
        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        try {
            String fileData = new String(Files.readAllBytes(Paths.get(GsonTest.class.getResource("/employee.json").getPath())));
            // parse json string to object
            Employee emp1 = gson.fromJson(fileData, Employee.class);

            // print object data
            System.out.println("nnEmployee Objectnn" + emp1);

            // create JSON String from Object
            String jsonEmp = gson.toJson(emp);
            System.out.print(jsonEmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Employee createEmployee() {
        Employee emp = new Employee();
        emp.setId(1000);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setMobile(new long[] { 123456, 987654 });
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        List<String> cities = new ArrayList<String>();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);

        Map<String, String> props = new HashMap<String, String>();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);

        return emp;
    }
}
