package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import beans.*;


public class DBHandler {
	
	private static String selectUsers="select * from users";
	
	
	public static LinkedList<userInfo> loadUsers (){
		
		LinkedList<userInfo> list = new LinkedList<>();
		try {
			ResultSet res = DBConnect.getStatment(selectUsers).executeQuery();
			while(res.next()) {
				userInfo user = new userInfo(
						res.getString("id"),
						res.getString("name"),
						res.getString("email"),
						res.getString("password")
						,res.getString("departement")) ;	
				list.add(user);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return list;
	}
	  
	public static boolean deleteUserid(String id) {
		
		try {
			 
			PreparedStatement stm = (PreparedStatement) DBConnect.getStatment("delete from users where `id`=?");
			stm.setString(1,id);
			return stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	public static boolean insertUser(String id) {
		
		try {
			 
			PreparedStatement stm = (PreparedStatement) DBConnect.getStatment("delete from users where `id`=?");
			stm.setString(1,id);
			return stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public static boolean updateUser(String id) {
		
		try {
			 
			PreparedStatement stm = (PreparedStatement) DBConnect.getStatment("delete from users where `id`=?");
			stm.setString(1,id);
			return stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
}
