package org.darebeat.effective;

import java.io.*;
import java.util.*;

import static java.lang.System.out;


/**
 * Created by darebeat on 5/6/17.
 */
public class IntSum {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        test();
//        testList();
        new IntSum().copy();
        long end = System.currentTimeMillis();
        out.println(end-start);

    }

    public static void test(){
        IntSum its = new IntSum();
        its.readFile3();
    }

    public static void testList(){
        ListTest lt = new ListTest();
        lt.testAdd();
        lt.testGet();
        lt.testIterator();
        lt.testModify();
        lt.testForeach();
        lt.testRemove();
    }

    public void copy(){
        byte[] buf = new byte[4096];
        try {
            FileInputStream in = new FileInputStream(IntSum.class.getResource("/employee.json").getPath());
            int len = in.read(buf);
            while (len != -1){
                out.write(buf,0,len);
                len = in.read(buf);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum DayOfWeek {
        // 存储枚举常量时使用
        MONDAY, TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUMDAY
    }

    private EnumSet weekend = EnumSet.of(DayOfWeek.SATURDAY,DayOfWeek.SUMDAY);

    public int sum(int a){
        return 0;
    }

    /**
     * 可变参数 jdk1.5
     * 使用可变参数需要隐式的创建匿名数组并初始化，因此会影响性能，在性能优先的情况下慎用。
     * @param intArray
     * @return
     */
    public int sum(int... intArray){
        int sumInt = 0;
        for (int t : intArray){
            sumInt += t;
        }
        return sumInt;
    }

    public void test0(){
        // 使用基本类型优先于包装类
        // Long sum = 0L; // 9795
        long sum = 0; // 1549
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        out.println(sum);
    }

    public void test1(){
        out.println(this.sum(1));
        out.println(this.sum(1,2));
        out.println(this.sum(1,2,3));
        out.println(this.sum(1,2,3,4));
    }

    public void test2(){
        out.println(this.weekend);
    }

    public void readFile(){
        try {
            FileInputStream fis = new FileInputStream(IntSum.class.getResource("/employee.json").getPath());
            int data = 0;
            while ((data = fis.read())!=-1){
                out.print((char)data);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile2(){
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(IntSum.class.getResource("/employee.json").getPath()));
            int data = 0;
            while ((data = bis.read())!=-1){
                out.print((char)data);
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile3(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(IntSum.class.getResource("/employee.json").getPath()));
            while (br.readLine() != null && br.readLine() != "null"){
                out.println(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student {
    public String id;
    public String name;

    public Student(String id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return this.id+" -- "+this.name;
    }
}

class ListTest{
    List<Student> students;

    public ListTest(){
        students = new ArrayList<Student>();
    }

    public void testAdd(){
        Student st1 = new Student("1", "张三");
        students.add(st1);

        Student tmp = students.get(0);
        out.println(tmp.toString());

        Student st2 = new Student("2", "李四");
        students.add(0, st2);
        Student temp2 = students.get(0);
        out.println(temp2.toString());

        Student[] student = { new Student("3", "王五"), new Student("4", "马六") };
        students.addAll(Arrays.asList(student));
        Student temp3 = students.get(2);
        Student temp4 = students.get(3);
        out.println(temp3.toString());
        out.println(temp4.toString());

        Student[] student2 = { new Student("5", "周七"), new Student("6", "赵八") };
        students.addAll(2, Arrays.asList(student2));
        Student temp5 = students.get(2);
        Student temp6 = students.get(3);
        out.println(temp5.toString());
        out.println(temp6.toString());
    }

    public void testGet(){
        int size = students.size();
        for (int i=0;i<size;i++){
            Student s = students.get(i);
            out.println(i+" "+s.toString());
        }
    }

    public void testIterator(){
        Iterator<Student> its = students.iterator();
        while (its.hasNext()){
            Student s = its.next();
            out.println(s.toString());
        }
    }

    public void testForeach(){
        for (Student s: students) {
            out.println(s.toString());
        }
    }

    public void testModify(){
        students.set(4,new Student("4","heartbeat"));
    }

    public void testRemove(){
        Student s = students.get(4);
        students.remove(s);
        testForeach();
    }
}
