package com.backend.hibernate;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

	private List<YourClass> classList = new ArrayList<YourClass>();
	private int id;
	
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
	
}
