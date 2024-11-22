package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Appointment;

public class AppointmentDAO {

	private Connection connection;

	public AppointmentDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean addAppointment(Appointment appointment) {
		
		boolean f = false;
		
		try {
			String sql = "insert into appointment(userid,fullname,gender,age,appointmentDate,email,phno,diseases,doctorid,address,status) values(?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, appointment.getUserId());
			ps.setString(2, appointment.getFullName());
			ps.setString(3, appointment.getGender());
			ps.setString(4, appointment.getAge());
			ps.setString(5, appointment.getAppointmentDate());
			ps.setString(6, appointment.getEmail());
			ps.setString(7, appointment.getPhNo());
			ps.setString(8, appointment.getDiseases());
			ps.setInt(9, appointment.getDoctorId());
			ps.setString(10, appointment.getAddress());
			ps.setString(11, appointment.getStatus());
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}	
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return f;
	}
	
	public List<Appointment> getAllAppointmentByLoginUser(int userId){
		
		List<Appointment> list = new ArrayList<Appointment>();
		Appointment ap = null;
		
		try {
			
			String sql = "select * from appointment where userid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				ap = new Appointment();
				ap.setId(rs.getInt(1));
				ap.setUserId(rs.getInt(2));
				ap.setFullName(rs.getString(3));
				ap.setGender(rs.getString(4));
				ap.setAge(rs.getString(5));
				ap.setAppointmentDate(rs.getString(6));
				ap.setEmail(rs.getString(7));
				ap.setPhNo(rs.getString(8));
				ap.setDiseases(rs.getString(9));
				ap.setDoctorId(rs.getInt(10));
				ap.setAddress(rs.getString(11));
				ap.setStatus(rs.getString(12));
				list.add(ap);
				
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	return list;		
	}
	
	
	public List<Appointment> getAllAppointmentByDoctorLogin(int doctorId) {

		List<Appointment> list = new ArrayList<Appointment>();
		Appointment ap = null;

		try {

			String sql = "select * from appointment where doctorid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, doctorId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ap = new Appointment();
				ap.setId(rs.getInt(1));
				ap.setUserId(rs.getInt(2));
				ap.setFullName(rs.getString(3));
				ap.setGender(rs.getString(4));
				ap.setAge(rs.getString(5));
				ap.setAppointmentDate(rs.getString(6));
				ap.setEmail(rs.getString(7));
				ap.setPhNo(rs.getString(8));
				ap.setDiseases(rs.getString(9));
				ap.setDoctorId(rs.getInt(10));
				ap.setAddress(rs.getString(11));
				ap.setStatus(rs.getString(12));
				list.add(ap);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}


	public Appointment getAppointmentById(int id) {

		Appointment ap = null;

		try {

			String sql = "select * from appointment where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ap = new Appointment();
				ap.setId(rs.getInt(1));
				ap.setUserId(rs.getInt(2));
				ap.setFullName(rs.getString(3));
				ap.setGender(rs.getString(4));
				ap.setAge(rs.getString(5));
				ap.setAppointmentDate(rs.getString(6));
				ap.setEmail(rs.getString(7));
				ap.setPhNo(rs.getString(8));
				ap.setDiseases(rs.getString(9));
				ap.setDoctorId(rs.getInt(10));
				ap.setAddress(rs.getString(11));
				ap.setStatus(rs.getString(12));

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ap;
	}
	
	
	public boolean updateCommentStatus(int id, int doctorId, String comm) {
		
		boolean f = false;
		
		try {
			String sql = "update appointment set status=? where id=? and doctorid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, comm);
			ps.setInt(2, id);
			ps.setInt(3, doctorId);
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return f;
	}
	
	
	public List<Appointment> getAllAppointment() {

		List<Appointment> list = new ArrayList<Appointment>();
		Appointment ap = null;

		try {

			String sql = "select * from appointment order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ap = new Appointment();
				ap.setId(rs.getInt(1));
				ap.setUserId(rs.getInt(2));
				ap.setFullName(rs.getString(3));
				ap.setGender(rs.getString(4));
				ap.setAge(rs.getString(5));
				ap.setAppointmentDate(rs.getString(6));
				ap.setEmail(rs.getString(7));
				ap.setPhNo(rs.getString(8));
				ap.setDiseases(rs.getString(9));
				ap.setDoctorId(rs.getInt(10));
				ap.setAddress(rs.getString(11));
				ap.setStatus(rs.getString(12));
				list.add(ap);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	
	
}
