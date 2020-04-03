package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import beans.nurse;
import beans.patient;
import beans.person;
import beans.user;

public class patientDB {

	
	
	private static ArrayList<patient> list;
	

	public static ArrayList<patient> getPatients(){
		
		
		String sql = "SELECT * FROM `patient` ,`person` where `patient`.`patient_id` = `person`.`person_id`";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		try {
			
			 ResultSet rs = stm.executeQuery();
			 
			 list = new ArrayList();
			 while(rs.next()) {
				 
				 patient per = 
						 new patient(rs.getString("fullname"),rs.getString("birth_date"),rs.getString("phone_numb"),rs.getString("person_id"));
				 list.add(per);
				 
			 }
			 
			
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Patients query.");
			return null;
		}
		
		
		return list;
	}
	
	
	
	public static boolean insertPatient(patient per) {
		
		personDB.insertPerson((person)per);
		
		String sql = "INSERT IGNORE INTO `patient` (`patient_id`) VALUES ('"+per.getPerson_id()+"');";
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error inserting Patient query.  ");
			return false;
		}
		
		
		return true;
	}
	
	
	
	
	public static boolean removePatient(String id) {
		
		String sql = "delete IGNORE from patient where `patient_id`="+id;
		
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
	
	
	public static patient getPatientById(String patient_id){
		
		String sql = "SELECT * FROM `person`,`patient` where `patient`.`patient_id` = `person`.`person_id` && `patient_id` = "+patient_id+"";
			
			
			PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
			
			
			patient pat=null;
			
			
			try {
					
					ResultSet rs = stm.executeQuery();
					
					if(rs.next()) {
						 pat = new patient( rs.getString("fullname"), rs.getString("birth_date"),rs.getString("phone_numb"),rs.getString("patient_id"));
						 		
					}

		
				
			} catch (SQLException e) {
				
				System.out.println("Error While Getting pat query. "+e.getMessage());
				return null;
			}
			
			
			return pat;
		}
		
	
	
	public static boolean updatePatient(patient p) {
		
		
		String sql = "UPDATE `person` SET fullname=('"+p.getFullname()+"'),phone_numb=('"+p.getPhone_numb()+"'),birth_date=('"+p.getBirth_date()+"') WHERE person_id='"+p.getPerson_id()+"'";
		

		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);


		
		try {
			
		 stm.execute(sql);

			
		} catch (SQLException e) {
			
			System.out.println("Error Updating Patient Data query.");
			return false;
		}
		
		return true;


	}
	

	
}
