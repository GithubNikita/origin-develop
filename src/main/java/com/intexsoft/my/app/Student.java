package com.intexsoft.my.app;

public class Student {
	private String name;
	private int groupNumber;
	
	public Student(String name, int groupNumber){
		this.setName(name);
		this.setGroupNumber(groupNumber);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
}
