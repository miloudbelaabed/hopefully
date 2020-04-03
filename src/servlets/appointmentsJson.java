package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.nurse;
import beans.person;
import dbmanip.appointmentDB;

/**
 * Servlet implementation class appointmentsJson
 */
@WebServlet("/appointmentsJson")
public class appointmentsJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().write("Error Page You Can't Be Here.");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dep_id = request.getParameter("dep_id");
		String appoint_date = request.getParameter("appoint_date");
		
		person user = (person) request.getSession().getAttribute("logged");
		
		if(user!=null) {
		
			if(user instanceof nurse) {
				dep_id = ((nurse)user).getDep_id()+"";
			}
			
			
			response.setContentType("application/json");
			
		
			if(Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", appoint_date) 
					&& Pattern.matches("^([0-9]{1,2})$", dep_id) ) {
			
				response.getWriter().write(appointmentDB.getAppointmentsJson(appoint_date,dep_id,true).toString());
		
			}else {
				response.getWriter().write("{}");
			}
			
		}else {
			response.getWriter().write("{}");
		}
		
		
	
	
	}

}
