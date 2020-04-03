package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbmanip.appointmentDB;

/**
 * Servlet implementation class payappointment
 */
@WebServlet("/payappointment")
public class payappointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().write("Error Page You Can't Be Here.");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String appoint_id = request.getParameter("appoint_id");
		
		response.setContentType("application/json");
		
	
		
		if( Pattern.matches("^([0-9]{1,})$", appoint_id)  ) {
			
			boolean payed =  appointmentDB.payAppointment(appoint_id);
			
			response.getWriter().write("{\"paid\":"+payed+"}");
						

			
		}else {
			response.getWriter().write("{\"paid\":false}");
		}
	
		
	
	
	}

}
