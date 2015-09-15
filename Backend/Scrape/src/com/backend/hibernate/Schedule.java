package com.backend.hibernate;

import java.util.List;

public class Schedule {
	private List<Class> classList;
	
	public Schedule() {
		
	}
	
	public Schedule(List<Class> classList) {
		this.classList = classList;
	}
	
	public List<Class> getClassList() {
		return classList;
	}
	
	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}
	
}
