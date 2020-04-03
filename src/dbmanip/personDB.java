package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.admin;
import beans.nurse;
import beans.person;

public class personDB {

	
	private static ArrayList<person> list;
	
	public static person getPersonPrevilg(String email,String password) {
		
		String sql = "SELECT * FROM `admin`,`person` ,`user` where admin_id=user_id and user_id=person_id and email='"+email+"' and pass='"+password+"'";
		String sqlnurse = "SELECT * FROM `nurse`,`person` ,`user` where nurse_id=user_id and user_id=person_id and email='"+email+"' and pass='"+password+"'";

		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		person per=null;
		
		try {
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				
				 per = 
						 new admin(rs.getString("fullname"),
								 rs.getString("birth_date"),
								 rs.getString("phone_numb"),
								 rs.getString("person_id"),rs.getString("email"),rs.getString("pass"));

				
			}else {
				 stm = (PreparedStatement) DBConnect.getStatment(sqlnurse);
				 rs = stm.executeQuery();
				 if(rs.next()) {
					 per = new nurse(rs.getString("fullname"),
								 rs.getString("birth_date"),
								 rs.getString("phone_numb"),
								 rs.getString("person_id"),
								 rs.getString("email"),rs.getString("pass"),
								 rs.getInt("dep_id")				 
							 );
				 }					
			}
			
		} catch (SQLException e) {

			System.out.println("Error On getPersonPrevilg");
			return null;
		}
		
		return per;
	}
	
	
	public static ArrayList<person> getPersons(){

		String sql = "select * from `person`";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		try {
			
			 ResultSet rs = stm.executeQuery();
			 
			 list = new ArrayList();
			 while(rs.next()) {
				 
				 person per = 
						 new person(rs.getString("fullname"),rs.getString("birth_date"),rs.getString("phone_numb"),rs.getString("person_id"));
				 
				 list.add(per);
				 
			 }
			 
			
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Persons query.");
			return null;
		}
		
		
		
		
		return list;
	}
	
	public static boolean insertPerson(person per) {
		
		String sql = "INSERT IGNORE INTO `person` (`fullname`, `birth_date`, `phone_numb`, `person_id`) VALUES ('"+per.getFullname()+"', '"+per.getBirth_date()+"', '"+per.getPhone_numb()+"', '"+per.getPerson_id()+"');";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error inserting person query.");
			return false;
		}
		
		
		return true;
	}

	
	public static boolean removePerson(String id) {
		
		String sql = "delete IGNORE from person where `person_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error removing person query.");
			return false;
		}
		
		
		return true;
	}
	
	
	
	
	public static boolean updatePerson(String id,person p) {
		
		String sql = String.format("UPDATE person SET fullname='%s' , phone_numb='%s',birth_date='%s' WHERE person.person_id='%s'", p.getFullname(),
				p.getPhone_numb(),p.getBirth_date()	,id);
		

		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
			stm.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Updating person query.");
			return false;
		}
		
		
		return true;
	}
	
	
	
}
