package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.departement;
import beans.patient;
import beans.person;

public class departementDB {

	
	
	
	private static ArrayList<departement> list;
	
	
	
	public static ArrayList<departement> getDepartements(){
		
		
		String sql = "SELECT * FROM `departement` ";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		try {
			
			 ResultSet rs = stm.executeQuery();
			 
			 list = new ArrayList();
			 while(rs.next()) {
				 
				 departement dep = new departement(rs.getInt("dep_id"), rs.getString("dep_name"), rs.getString("dep_desc"));
				 
				 
				 list.add(dep);
				 
			 }
			 
			
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Departements query.");
			return null;
		}
		
		
		return list;
	}
	
	
	

	public static departement getDepById(int dep_id){
		
		
		String sql = "SELECT * FROM `departement` where `dep_id`='"+dep_id+"'";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		departement dep=null;
		
		
		try {
				
				ResultSet rs = stm.executeQuery();
				
				if(rs.next()) {
					 dep = new departement(rs.getInt("dep_id"), rs.getString("dep_name"), rs.getString("dep_desc"));
							
				}

	
			
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Dep query. "+e.getMessage());
			return null;
		}
		
		
		return dep;
	}
	
	
	public static boolean insertDepartement(departement dep) {
		
		
		String sql = "INSERT INTO `departement` ( `dep_name`, `dep_desc`) VALUES  ('"
				+dep.getDep_name()+","+dep.getDep_desc() +"');";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error inserting Departement query.");
			return false;
		}
		
		
		return true;
	}
	
	
	public static boolean removeDepartement(String id) {
		String sql = "delete IGNORE from departement where `dep_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error Deleting Departement query.");
			
			return false;
		}
		
		
		return true;	
	}
	
	
}
