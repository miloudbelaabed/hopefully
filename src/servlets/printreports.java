package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.sun.glass.ui.CommonDialogs.Type;

import beans.nurse;
import beans.person;
import dbmanip.appointmentDB;


@WebServlet("/printreports")
public class printreports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().write("Error Page You Can't Be Here.");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	

		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String dep_id = new String(request.getParameter("dep_id"));
		System.out.println("dep-id :: " + dep_id);
		System.out.println(dep_id.getClass().getName());
		

		
		person user = (person) request.getSession().getAttribute("logged");
		
		if(user!=null) {

			response.setContentType("application/json");
			
		
			if(Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", start_date) 
				&& Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", end_date) 	) {
				
				
					if(dep_id.equals("0")) {

						JSONArray arr  = appointmentDB.getAppointmentsJsonBTW(start_date, end_date);
						String toreturn="{}";
						if (arr!=null) {
							toreturn = arr.toString();
						}
						
						response.getWriter().write(toreturn);
					}else {

						JSONArray arr  = appointmentDB.getAppointmentsJsonBTW(start_date, end_date,dep_id);
						String toreturn="{}";
						if (arr!=null) {
							toreturn = arr.toString();
						}
						
						response.getWriter().write(toreturn);
					}
			
				
				
							
				
			}else {
				response.getWriter().write("{}");
			}
			
		}else {
			response.getWriter().write("{}");
		}

	
	}

	
	
}
