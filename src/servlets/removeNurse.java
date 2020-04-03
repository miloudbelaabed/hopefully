package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

import beans.admin;
import beans.person;
import dbmanip.nurseDB;
import statics.jsonHandlers;
import sun.security.provider.certpath.OCSPResponse.ResponseStatus;

/**
 * Servlet implementation class removeNurse
 */
@WebServlet("/removeNurse")
public class removeNurse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonObject="";
		
		String idToRemove = request.getParameter("id");
		
		person user = (person) request.getSession().getAttribute("logged");
		
		if(user!=null && user instanceof admin) {			
		
			if(idToRemove.length()==9) {
				
				
				if(nurseDB.removeNurse(idToRemove)) {
	
	
					jsonObject = jsonHandlers.getJsonResponse("success", "Remove Successfully", "The User Removed Successfully.");
							
						
	
	
				}else {
	
					jsonObject = jsonHandlers.getJsonResponse("danger", "Remove Error", "Error While Removing The User Verify Your Connection.");
							
	
				}
		
				
			}else {
				
	
				jsonObject = jsonHandlers.getJsonResponse("danger", "Remove Error", "This User Doesn't Exist Or Db Not Connected.");
				
		
			}
		

		}else {
			jsonObject = jsonHandlers.getJsonResponse("danger", "Remove Error", "Nurse Can't modify/delete other Nurses Data.");
		}
		
		response.getWriter().write(jsonObject);
		
	}

}
