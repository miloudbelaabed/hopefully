package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.admin;
import beans.nurse;
import beans.patient;
import beans.person;
import dbmanip.adminDB;
import dbmanip.departementDB;
import dbmanip.nurseDB;
import dbmanip.patientDB;
import jdk.internal.dynalink.beans.StaticClass;
import statics.jsonHandlers;

@WebServlet("/patientUpdate")
public class patientUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		person user = (person) req.getSession().getAttribute("logged");
		


		if(user!=null) {
			

			
			String id = req.getParameter("id");
			String fullname = req.getParameter("fullname");
			String phone_numb = req.getParameter("phone_numb");
			String birth_date = req.getParameter("birth_date");



			
			if( Pattern.matches("^[0-9]{9}$", id) ) {
				

					String jsonObject="{"
							+ "\"fullname\":"+Pattern.matches("^([\\w]{3,})+\\s+([\\w\\s]{3,})+$", fullname)+","
							+ "\"phone_numb\":"+Pattern.matches("^0[1-9][0-9]{8}$", phone_numb)+","
							+ "\"birth_date\":"+Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", birth_date)
							+ "}";
					
					
					

					resp.setContentType("application/json");
					
					if(jsonObject.contains("false")) {
		
						jsonObject = "{\"success\":false,\"connection\":true,"+jsonObject;
						resp.getWriter().print(jsonObject);

						
					}else {	
						patient pat = new patient(fullname, birth_date, phone_numb, id);
						
						
						boolean querystate = patientDB.updatePatient(pat) ;

						resp.getWriter().print("{\"success\":"+querystate+",\"connection\":true}");
						
	
					}


			}
			
		}else {

			String jsonObject = "{\"success\":false,\"connection\":false}";


			resp.getWriter().print(jsonObject);

		}
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.getWriter().print("Permessions Error.");
		
		
	}

}
