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
import beans.person;
import dbmanip.adminDB;
import dbmanip.departementDB;
import dbmanip.nurseDB;
import jdk.internal.dynalink.beans.StaticClass;
import statics.jsonHandlers;

@WebServlet("/nurseUpdate")
public class nurseUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		person user = (person) req.getSession().getAttribute("logged");
		


		if(user!=null && user instanceof admin) {
			

			
			String id = req.getParameter("id");
			
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String pass = req.getParameter("pass");
			String phone_numb = req.getParameter("phone_numb");
			String birth_date = req.getParameter("birth_date");
			int dep_id = Integer.parseInt(req.getParameter("dep_id"));
			String ope = req.getParameter("ope");
			

			
			if( Pattern.matches("^[0-9]{9}$", id) && departementDB.getDepById(dep_id)!=null) {
				
				

		
				
					String jsonObject=""
							+ "\"fullname\":"+Pattern.matches("^([\\w]{3,})+\\s+([\\w\\s]{3,})+$", fullname)+","
							+ "\"email\":"+Pattern.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", email)+","
							+ "\"pass\":"+Pattern.matches("^[0-9a-zA-Z+*@!#%/-]{6,}$", pass)+","
							+ "\"phone_numb\":"+Pattern.matches("^0[1-9][0-9]{8}$", phone_numb)+","
							+ "\"birth_date\":"+Pattern.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", birth_date)+","
							+ "\"dep_id\":"+ Pattern.matches("^([0-9]{1,2})$", dep_id+"")+"}";
					

					resp.setContentType("application/json");
					
					if(jsonObject.contains("false")) {
		
						jsonObject = "{\"success\":false,\"connection\":true,"+jsonObject;
						resp.getWriter().print(jsonObject);

						
					}else {	
						nurse nurseData = new nurse(fullname, birth_date, phone_numb, id, email, pass, dep_id);
						boolean querystate=false;
						
						
						if(ope.equals("ed")) {
							querystate = nurseDB.updateNurse(id, nurseData);	
						}else if (ope.equals("add")) {	

							
							if( !(adminDB.existAdmin(nurseData.getEmail())) ){
								querystate = nurseDB.insertNurse(nurseData, nurseData.getDep_id()+"");	

							}else {
								querystate = false;
							}
							
						}
						
						resp.getWriter().print("{\"success\":"+querystate+",\"connection\":true,\"adminprob\":true}");									
						
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
