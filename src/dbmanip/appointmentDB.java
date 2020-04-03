package dbmanip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.mysql.jdbc.PreparedStatement;

import beans.appointment;
import beans.departement;
import beans.patient;
import jdk.nashorn.internal.parser.JSONParser;

public class appointmentDB {

	
	
	
	private static ArrayList<appointment> list;
	

	
	public static JSONArray getAppointmentsJson(String date,String dep_id,boolean onlyNotPaid){
		
																	
		String onlyNotPaidq=" and appoint_paid = 0  ";
		
		String sql = "SELECT * FROM `appointment` join person WHERE dep_id='"+dep_id+"' and	 person.person_id=appointment.patient_id and appoint_date='"+date+"'";

		if(onlyNotPaid) {
			sql += onlyNotPaidq;
		}
		
		sql+= "ORDER by book_time asc";
		
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		JSONArray arr;
		
		try {
			
			 ResultSet rs = stm.executeQuery();

			 
			 list = new ArrayList();
			 while(rs.next()) {
				
				 appointment appoint = new appointment(rs.getInt("appoint_id"),rs.getString("patient_id"),rs.getInt("dep_id"),rs.getString("appoint_date"),rs.getString("book_time"),rs.getBoolean("appoint_paid"));
				 
				 appoint.pat = new patient( rs.getString("fullname"),rs.getString("birth_date"),rs.getString("phone_numb"), rs.getString("patient_id") );			

						
				 
				 list.add(appoint); 
			 }
			 
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Appointments query. "+e.getMessage());
			return null;
		} 
		
		arr = new JSONArray(list);
		

		
		return arr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static JSONArray getAppointmentsJsonBTW(String startdate,String enddate){
		
		

		String sql = "SELECT * FROM `appointment` join person WHERE person.person_id=appointment.patient_id and appoint_date>='"+startdate+"' and   appoint_date<='"+enddate+"'";


		
		sql+= "  ORDER by book_time asc";
		
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		JSONArray arr;
		
		try {
			
			 ResultSet rs = stm.executeQuery();

			 
			 list = new ArrayList();
			 
			 while(rs.next()) {
				
				 appointment appoint = new appointment(rs.getInt("appoint_id"),rs.getString("patient_id"),rs.getInt("dep_id"),rs.getString("appoint_date"),rs.getString("book_time"),rs.getBoolean("appoint_paid"));
				 
				 appoint.pat = new patient( rs.getString("fullname"),rs.getString("birth_date"),rs.getString("phone_numb"), rs.getString("patient_id") );			

						
				 
				 list.add(appoint); 
			 }
			 
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Appointments query. "+e.getMessage());
			return null;
		} 
		
		arr = new JSONArray(list);
		
		
		return arr;
	}
	
	
	
	
	
	public static JSONArray getAppointmentsJsonBTW(String startdate,String enddate,String depar_id){
		
		

		String sql = "SELECT * FROM `appointment` join person WHERE `person`.`person_id`=appointment.patient_id and `appoint_date`>='"+startdate+"' and `appoint_date`<='"+enddate+"' and `dep_id` ='"+depar_id+"'";


		
		sql+= "  ORDER by book_time asc";
		
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		JSONArray arr;
		
		try {
			
			 ResultSet rs = stm.executeQuery();

			 
			 list = new ArrayList();
			 
			 while(rs.next()) {
				 System.out.println(rs.getInt("dep_id"));
				
				 appointment appoint = new appointment(rs.getInt("appoint_id"),rs.getString("patient_id"),rs.getInt("dep_id"),rs.getString("appoint_date"),rs.getString("book_time"),rs.getBoolean("appoint_paid"));
				 
				 appoint.pat = new patient( rs.getString("fullname"),rs.getString("birth_date"),rs.getString("phone_numb"), rs.getString("patient_id") );			

						
				 
				 list.add(appoint); 
			 }
			 
		} catch (SQLException e) {
			
			System.out.println("Error While Getting Appointments query. "+e.getMessage());
			return null;
		} 
		
		arr = new JSONArray(list);
		
		
		return arr;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	public static boolean insertAppointment(appointment app) {
		
		
		String sql = "INSERT INTO `appointment` ( `patient_id`, `dep_id`,`appoint_date`) VALUES ( '"+app.getPatient_id()+"', '"+app.getDep_id()+"', '"+app.getAppoint_date()+"');";
		
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error While inserting Appointments query.");
			return false;
		}
		
		
		return true;
	}
	
	
	public static boolean payAppointment(String id) {
		
		String sql = "update `appointment` set appoint_paid=true where `appoint_id`="+id;
		
		PreparedStatement stm = (PreparedStatement) DBConnect.getStatment(sql);
		
		
		try {
			
			 stm.execute();
			
			
		} catch (SQLException e) {
			
			System.out.println("Error Whilme Paying Appointment query.");
			
			return false;
		}
		
		
		return true;	
	}
	
	
	
	
	
}
