package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.user;
import beans.nurse;
import beans.patient;
import beans.person;

public class userDB {

	
	
	private static ArrayList<user> list;
	
	
	public static ArrayList<user> getUsers(){
		
		
		String sql = "SELECT * FROM `user` ,`person` where `user`.`user_id` = `person`.`person_id`";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		try {
			
			 ResultSet rs = stm.executeQuery();
			 
			 list = new ArrayList();
			 while(rs.next()) {

				 user per = 
						 new user(rs.getString("fullname"),
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
	
	
	
	
	public static boolean insertUser(user per) {
		
		personDB.insertPerson( (person) per);
		
		String sql = "INSERT INTO `user` (`user_id`, `email`, `pass`) VALUES ('"+per.getPerson_id()+"', '"+per.getEmail()+"', '"+per.getPass()+"');";
				
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		

		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error inserting user query."); 
			return false;
		}
		
		
		return true;
	}
	
	
	
	
	public static boolean removeUser(String id) {
		
		String sql = "delete from user where `user_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		

		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error removing user query.  ");
			return false;
		}
		
		
		return true;
	}
	
	
	public static boolean updateUser(String id,user p) {
		
		
		String sql = String.format("UPDATE user SET email='%s',pass='%s' WHERE user_id='%s'",p.getEmail(),p.getPass(),id);
		

		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error Updating User Data query.");
			return false;
		}
		
		
		return personDB.updatePerson(id, (person) p);
	}

	
	
}
