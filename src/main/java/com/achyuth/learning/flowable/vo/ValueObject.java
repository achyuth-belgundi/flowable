package com.achyuth.learning.flowable.vo;

import java.io.Serializable;

public class ValueObject implements Serializable {

	private String id;
	private String name;
	private String city;
	private int daysOfHolidays;
	private String description;
	
	public ValueObject() {
		
	}
	
	
	public ValueObject(String id, String name, String city, int daysOfHolidays, String description) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.daysOfHolidays = daysOfHolidays;
		this.description = description;
	}


	public int getDaysOfHolidays() {
		return daysOfHolidays;
	}
	public void setDaysOfHolidays(int daysOfHolidays) {
		this.daysOfHolidays = daysOfHolidays;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
