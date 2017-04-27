package org.darebeat.bean;

import java.lang.Comparable;

public class City implements Comparable{
    private int cityId;
    private String cityName;
    private int length;

    /**
	* Default City constructor
	*/
	public City(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.length = cityName.length();
	}

	/**
	* Returns value of cityId
	* @return
	*/
	public int getCityId() {
		return this.cityId;
	}
    
	/**
	* Returns value of cityName
	* @return
	*/
	public String getCityName() {
		return this.cityName;
	}

	/**
	* Returns value of length
	* @return
	*/
	public int getLength() {
		return this.length;
	}

	/**
	* Sets new value of cityId
	* @param
	*/
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	* Sets new value of cityName
	* @param
	*/
	public void setCityName(String cityName) {
		this.cityName = cityName;
        this.length = cityName.length();
	}

    @Override
    public int compareTo(Object o){
        City c = (City) o;
        int result= this.length>c.length ? -1:(this.length==c.length ? 0 : 1);
        if (0==result){
            result=this.cityName.compareTo(c.cityName);
        }
        return result;
    }


	/**
	* Create string representation of City for printing
	* @return
	*/
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", length=" + length + "]";
	}
}
