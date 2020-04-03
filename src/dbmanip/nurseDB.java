package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.nurse;
import beans.person;
import beans.user;

public class nurseDB {


	
	
	
	private static ArrayList<nurse> list;
	
	

	
	public static ArrayList<nurse> getNurses(){
		
		
		String sql = "SELECT * FROM `nurse`,`person` ,`user` where nurse_id=user_id and user_id=person_id ";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 ResultSet rs = stm.executeQuery();

			 list = new ArrayList();

			 while(rs.next()) {
				 
				 
				 
				 nurse per = 
						 new nurse(rs.getString("fullname"),
								 rs.getString("birth_date"),
								 rs.getString("phone_numb"),
								 rs.getString("person_id"),
								 rs.getString("email"),rs.getString("pass"),
								 rs.getInt("dep_id")				 
								 );
				 
				 list.add(per);
				 
				 
			 }
			
		} catch (SQLException e) {
			
			
			return null;
		}
		

		
		return list;
	}
	

	public static nurse getNurse(String email,String password){
			
			
			String sql = "SELECT * FROM `nurse`,`person`,`user` where nurse_id=user_id and user_id=person_id and email=('"+email+"') and pass=('"+password+"') LIMIT 1";
			
			PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
			nurse per=null;
			
			try {
				
				 ResultSet rs = stm.executeQuery();
	
	
				 rs.next();

				 per = 
					 new nurse(rs.getString("fullname"),
							 rs.getString("birth_date"),
							 rs.getString("phone_numb"),
							 rs.getString("person_id"),
							 rs.getString("email"),rs.getString("pass"),
							 rs.getInt("dep_id")				 
							 );

					 
				
				
			} catch (SQLException e) {
				
				
				return null;
			}
			
	
			
			return per;
	}
		
		
		
	public static boolean insertNurse(nurse per,String dep_id) {
		
		userDB.insertUser((user)per);
		
		String sql = "INSERT INTO `nurse` (`nurse_id`, `dep_id`) VALUES ('"+per.getPerson_id()+"', '"+dep_id+"');";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error inserting Nurse query.");
			return false;
		}
		
		
		return true;
	}
	
	
	
	public static boolean removeNurse(String id) {
		
		String sql = "delete from nurse where `nurse_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			

			
		} catch (SQLException e) {
			
			System.out.println("Error removing nurse query.");
			
			return false;
		}
		
		
		return userDB.removeUser(id);
	}
	
	
	
	public static boolean updateNurse(String id,nurse p) {
		
		
		String sql = String.format("UPDATE nurse SET dep_id='%d' WHERE nurse_id='%s'", p.getDep_id(),id);
		

		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error Updating Nurse Data query.");
			return false;
		}
		
		
		return userDB.updateUser(id,(user) p);
	}
	
	
	
}
