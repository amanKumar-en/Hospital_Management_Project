package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Specialist;

public class SpecialistDAO {

	private Connection connection;

	public SpecialistDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean addSpecialist(String specialistDocs) {
		
		boolean f = false;
		
		try {
			// create table for specialist
			String query = "insert into specialist(specialistDocs_name) values(?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, specialistDocs);
			
			int affected = ps.executeUpdate();
			if(affected == 1) {
				f = true;
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return f;
	}
	
	public List<Specialist> getAllSpecialist(){
		
		List<Specialist> list = new ArrayList<Specialist>();
		Specialist s = null;
		
		try {
			String query = "select * from specialist";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Specialist();
				s.setId(rs.getInt(1));
				s.setSpecialistName(rs.getString(2));
				list.add(s);
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
}
