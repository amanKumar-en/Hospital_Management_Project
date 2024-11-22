package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.Entity.Doctor;

public class DoctorDAO {

	private Connection connection;

	public DoctorDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	// admin have control to add doctor
	public boolean registerDoctor(Doctor d) {
		boolean f = false;

		try {
			//create doctor table
			String sql = "insert into doctor(full_name,dob,qualification,specialist,email,mobno,password) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, d.getFullName());
			ps.setString(2, d.getDob());
			ps.setString(3, d.getQualification());
			ps.setString(4, d.getSpecialist());
			ps.setString(5, d.getEmail());
			ps.setString(6, d.getMobNo());
			ps.setString(7, d.getPassword());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Doctor> getAllDoctor() {
		List<Doctor> list = new ArrayList<Doctor>();
		Doctor d = null;
		try {

			String sql = "select * from doctor order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));
				list.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// no need for list because we are get only one doctor details in the edit doctor page(which by uniqueID)
	// when we enter edit doctor button then those doctor details also show in the edit doctor page, that we getting by ID
	public Doctor getDoctorById(int id) {

		Doctor d = null;
		try {

			String sql = "select * from doctor where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	public boolean updateDoctor(Doctor d) {
		boolean f = false;

		try {
			String sql = "update doctor set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=?,password=? where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, d.getFullName());
			ps.setString(2, d.getDob());
			ps.setString(3, d.getQualification());
			ps.setString(4, d.getSpecialist());
			ps.setString(5, d.getEmail());
			ps.setString(6, d.getMobNo());
			ps.setString(7, d.getPassword());
			ps.setInt(8, d.getId());
			
			
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public boolean deleteDoctor(int id) {
		boolean f = false;
		try {
			String sql = "delete from doctor where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

//	public Doctor login(String email, String psw) {
//		
//		Doctor d = null;
//		try {
//
//			String sql = "select * from doctor where email=? and password=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, email);
//			ps.setString(2, psw);
//
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				d = new Doctor();
//				d.setId(rs.getInt(1));
//				d.setFullName(rs.getString(2));
//				d.setDob(rs.getString(3));
//				d.setQualification(rs.getString(4));
//				d.setSpecialist(rs.getString(5));
//				d.setEmail(rs.getString(6));
//				d.setMobNo(rs.getString(7));
//				d.setPassword(rs.getString(8));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return d;
//	}
	
	
public Doctor login(String email, String psw) {
		
		Doctor d = null;
		try {

			String sql = "select * from doctor where email=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String storedHashedPassword = rs.getString("password");
				
				if(BCrypt.checkpw(psw, storedHashedPassword)) {
					d = new Doctor();
					d.setId(rs.getInt(1));
					d.setFullName(rs.getString(2));
					d.setDob(rs.getString(3));
					d.setQualification(rs.getString(4));
					d.setSpecialist(rs.getString(5));
					d.setEmail(rs.getString(6));
					d.setMobNo(rs.getString(7));
					d.setPassword(rs.getString(8));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}

	// dynamic admin dashboard numbers of  doctor, user, specialist
	public int countDoctor() {
		int i = 0;
		try {
			String sql = "select * from doctor";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	public int countAppointment() {
		int i = 0;
		try {
			String sql = "select * from appointment";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}


	public int countUSer() {
		int i = 0;
		try {
			String sql = "select * from user_details";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	public int countSpecialist() {
		int i = 0;
		try {
			String sql = "select * from specialist";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
	
	public int countAppointmentByDoctorId(int did) {
		int i = 0;
		try {
			String sql = "select * from appointment where doctorid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, did);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

//	public boolean checkOldPassword(int userid, String oldPassword) {
//		boolean f = false;
//
//		try {
//			String sql = "select * from doctor where id=? and password=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, userid);
//			ps.setString(2, oldPassword);
//
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				f = true;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return f;
//	}
	
	public boolean checkOldPassword(int userid, String oldPassword) {
	    boolean f = false;
	    
	    try {
	        // Retrieve the hashed password using the user ID
	        String sql = "SELECT password FROM user_details WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, userid);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String storedHashedPassword = rs.getString("password");
	            
	            // Verify the old password against the stored hashed password
	            if (BCrypt.checkpw(oldPassword, storedHashedPassword)) {
	                f = true; // Password matches
	            }
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return f;
	}

//	public boolean changePassword(int userid, String newPassword) {
//		boolean f = false;
//
//		try {
//			String sql = "update doctor set password=? where id=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, newPassword);
//			ps.setInt(2, userid);
//
//			int i = ps.executeUpdate();
//			if (i == 1) {
//				f = true;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return f;
//	}
	
	public boolean changePassword(int userid, String newPassword) {
	    boolean f = false;
	    
	    try {
	        // Hash the new password using BCrypt
	        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
	        
	        // Update the database with the hashed password
	        String sql = "UPDATE user_details SET password=? WHERE id=?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, hashedNewPassword);
	        ps.setInt(2, userid);
	        
	        int i = ps.executeUpdate();
	        
	        if (i == 1) {
	            f = true; // Password updated successfully
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return f;
	}

	public boolean editDoctorProfile(Doctor d) {
		boolean f = false;

		try {
			String sql = "update doctor set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=? where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, d.getFullName());
			ps.setString(2, d.getDob());
			ps.setString(3, d.getQualification());
			ps.setString(4, d.getSpecialist());
			ps.setString(5, d.getEmail());
			ps.setString(6, d.getMobNo());
			ps.setInt(7, d.getId());
			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Doctor> searchDoctor(String ch) {
		List<Doctor> list = new ArrayList<Doctor>();
		Doctor d = null;
		try {
			String sql = "select * from doctor where full_name like ? or specialist like ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + ch + "%");
			ps.setString(2, "%" + ch + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt(1));
				d.setFullName(rs.getString(2));
				d.setDob(rs.getString(3));
				d.setQualification(rs.getString(4));
				d.setSpecialist(rs.getString(5));
				d.setEmail(rs.getString(6));
				d.setMobNo(rs.getString(7));
				d.setPassword(rs.getString(8));
				list.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public String getPasswordByUserId(int userId) {
	    String password = null;
	    try {
	        String sql = "SELECT password FROM doctor WHERE id = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            password = rs.getString("password");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return password;
	}


}
