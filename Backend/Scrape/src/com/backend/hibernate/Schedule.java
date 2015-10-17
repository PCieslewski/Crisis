package com.backend.hibernate;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

	private List<YourClass> classList = new ArrayList<YourClass>();
	private int id;
	private String zzz;
	private List<Temp> temp;
	
	public Schedule() {
		
	}
	
	public List<YourClass> getClassList() {
		return classList;
	}
	
	public void setClassList(List<YourClass> classList) {
		this.classList = classList;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getZzz() {
		return zzz;
	}
	
	public void setZzz(String zzz) {
		this.zzz = zzz;
	}

	public List<Temp> getTemp() {
		return temp;
	}
	
	public void setTemp(List<Temp> temp) {
		this.temp = temp;
	}
	
}
