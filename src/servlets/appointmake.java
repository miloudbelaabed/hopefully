package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.appointment;
import beans.patient;
import dbmanip.DBHandler;
import dbmanip.appointmentDB;
import dbmanip.departementDB;
import dbmanip.patientDB;
import statics.jsonHandlers;


@WebServlet("/appointmake")

public class appointmake extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		boolean empty=true;
		try {
			
			int dep_id = Integer.parseInt(request.getParameter("dep_id"));			
			String phone_numb = request.getParameter("phone_numb");
			String full_name = request.getParameter("full_name");
			String person_id = request.getParameter("person_id");
			String birth_date = request.getParameter("birth_date");
			String appoint_date = request.getParameter("appoin_date");
			
			empty=false;
			boolean depid_bol = (departementDB.getDepById(dep_id)!=null );
			
			String jsonObject=""
					+ "\"fullname\":"+Pattern.matches("^([\\w]{3,})+\\s+([\\w\\s]{3,})+$", full_name)+","
					+ "\"phone_numb\":"+Pattern.matches("^0[1-9][0-9]{8}$", phone_numb)+","
					+ "\"person_id\":"+Pattern.matches("^[0-9]{9}$", person_id)+","
					+ "\"birth_date\":"+Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", birth_date)+","
					+ "\"appoint_date\":"+Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", appoint_date)+","
					+ "\"dep_id\":"+ depid_bol +"}";
			
			
			resp.setContentType("application/json");
			
			if(jsonObject.contains("false")) {
				
				
				
				String responseJson = jsonHandlers.getJsonResponse("danger", "Empty Data Entered", "Please Fill All The Data Input Fields.");

				resp.getWriter().print(responseJson);
				
			}else {	
				
				patient pat = new patient(full_name, birth_date, phone_numb, person_id);
				
				boolean inserper = patientDB.insertPatient(pat);

				appointment app = new appointment(1,person_id,dep_id,appoint_date);
				
				boolean inserapp = appointmentDB.insertAppointment(app);
				String responseJson ="";
				
				
				if(!inserapp && !inserper) {
					
					responseJson = jsonHandlers.getJsonResponse("danger", "Database Connection Error", "Please Verify Your Internet Connection.");
					
					
				}else {
					
					if(!inserapp) {
						responseJson = jsonHandlers.getJsonResponse("success", "Appointment Not Booked", "The Appointment Already Exists.");

					}else {
						responseJson = jsonHandlers.getJsonResponse("success", "Appointment Booked Successfully", "The Appointment Has Been Booked Successfully.");
					}

				}
				
				resp.getWriter().print(responseJson);

			}
			
			
			
		}catch(Exception ex) {
			
			resp.setContentType("application/json");
			String responseJson="";
			if(empty) {
				responseJson =  jsonHandlers.getJsonResponse("danger", "Empty Data Entered", "Please Fill All The Data Input Fields.");
			}else {
				responseJson = jsonHandlers.getJsonResponse("danger", "Database Connection Error", "Please Verify Your Internet Connection.");
			}
			resp.getWriter().print(responseJson);
		}

		
		
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("Error You Can't Be Here.");
	}
}
