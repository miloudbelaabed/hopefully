package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.admin;
import beans.person;
import beans.nurse;
import dbmanip.adminDB;
import dbmanip.appointmentDB;
import dbmanip.nurseDB;
import jdk.nashorn.internal.parser.JSONParser;
import statics.jsonHandlers;

/**
 * Servlet implementation class loginDash
 */
@WebServlet("/loginDash")
public class loginDash extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().write("Error Page You Can't Be Here.");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean emptydata=true;
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			emptydata=false;
			response.setContentType("application/json");
			
			
			if(Pattern.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", email) 
					&& Pattern.matches("^[0-9a-zA-Z+*@!#%/-]{6,}$", password) ) {
				
				admin adminFinder =  adminDB.getAdmin(email, password);
				nurse nurseFinder = nurseDB.getNurse(email, password);;
				
				if(adminFinder==null && nurseFinder==null) {
					
					response.getWriter().write("{\"found\":false,\"connection\":true}");
				}else {	
					
					response.getWriter().write("{\"found\":true}");
					
					person per = (adminFinder!=null)? adminFinder:nurseFinder;
					String userType =  (adminFinder!=null)? "Administrator":"Nurse";
					
					request.getSession().setAttribute("logged", per);
					request.getSession().setAttribute("userType", userType);

					
					
				}

			}
			
		}catch(Exception ex) {
			response.getWriter().write("{\"found\":false,\"connection\":false}");
		}
		
	
	
	}


}
