package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.admin;
import beans.nurse;
import beans.user;

public class adminDB {


	private static ArrayList<admin> list;
	
	public static ArrayList<admin> getAdmins(){
		
				
		String sql = "SELECT * FROM `admin`,`person` ,`user` where admin_id=user_id and user_id=person_id ";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		try {
			
			 ResultSet rs = stm.executeQuery();
			 
			 list = new ArrayList();
			 while(rs.next()) {

				 admin per = 
						 new admin(rs.getString("fullname"),
								 rs.getString("birth_date"),
								 rs.getString("phone_numb"),
								 rs.getString("person_id"),rs.getString("email"),rs.getString("pass"));
				 
				 list.add(per);
				 
			 }
			 
			
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Patients query.");
			return null;
		}
				
		return list;
	}
	
	
	
	public static admin getAdmin(String email,String password){
		
		
		String sql = "SELECT * FROM `admin`,`person`,`user` where admin_id=user_id and user_id=person_id and email=('"+email+"') and pass=('"+password+"') LIMIT 1";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		admin per=null;
		
		try {
			
			 ResultSet rs = stm.executeQuery();


			 rs.next();

			 per = 
				 new admin(rs.getString("fullname"),
						 rs.getString("birth_date"),
						 rs.getString("phone_numb"),
						 rs.getString("person_id"),
						 rs.getString("email"),rs.getString("pass")			 
						 );

				 
			
			
		} catch (SQLException e) {
			
			
			return null;
		}
		

		
		return per;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean existAdmin(String email){
	
	
		String sql = "SELECT * FROM `admin`,`person`,`user` where admin_id=user_id and user_id=person_id and email=('"+email+"') LIMIT 1";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);

		
		try {
			
			 ResultSet rs = stm.executeQuery();

			 return rs.next();

			
			
		} catch (SQLException e) {
			
			
			return false;
		}
	

	}
	
	
	
	
	
	
	
	
	
	
	
	public static boolean insertAdmin(admin per) {
		
		userDB.insertUser((user)per);
		
		String sql = "INSERT IGNORE INTO `admin` (`admin_id`) VALUES ('"+per.getPerson_id()+"');";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
		} catch (SQLException e) {
			System.out.println("Error inserting Patient query.");
			return false;
		}
		
		
		return true;
	}
	
	
	
	
	public static boolean removeAdmin(String id) {
		
		String sql = "delete IGNORE from admin where `admin_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error removing admin query.");
			return false;
		}
		
		
		return userDB.removeUser(id);
	}
	
	
	
}
