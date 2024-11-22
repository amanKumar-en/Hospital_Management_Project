package com.Entity;

public class Specialist {

	private int id;
	private String SpecialistName;
	public Specialist(int id, String SpecialistName) {
		super();
		this.id = id;
		SpecialistName = SpecialistName;
	}
	
	public Specialist() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecialistName() {
		return SpecialistName;
	}
	public void setSpecialistName(String specialistName) {
		SpecialistName = specialistName;
	}
	
	
	
}
