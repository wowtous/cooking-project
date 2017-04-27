package org.darebeat.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by darebeat on 4/26/17.
 */
public class Employee {
    private int id;
    private String name;
    private Address address;
    private boolean permanent;
    private long[] mobile;
    private String role;
    private List<String> cities;
    private Map<String,String> properties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long[] getMobile() {
        return mobile;
    }

    public void setMobile(long[] mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID="+getId()+"n");
        sb.append("Name="+getName()+"n");
        sb.append("Permanent="+isPermanent()+"\n");
        sb.append("Mobile="+getMobile()+"\n");
        sb.append("Role="+getRole()+"\n");
        sb.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
        sb.append("Properties="+getProperties()+"\n");
        sb.append("*****************************");

        return sb.toString();
    }
}
