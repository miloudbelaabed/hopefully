package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.admin;
import beans.person;


@WebServlet(name="dashboard",urlPatterns = "/dashboard/*")

public class dashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			boolean loggedin = false;

	
			person per =  (person) request.getSession().getAttribute("logged");

			if(per!=null){
				loggedin = true;
			}


	   
			
			if(loggedin) {
				
				
				String dashpart = request.getPathInfo();
				
		
				String urltogo = "";
				
				if(dashpart!=null) {
					
					dashpart = dashpart.replace("/","");	
					

					
					
					if( dashpart.equals("nursesmanager")) {
		        		
		        		if(per instanceof admin)
		        			urltogo="nursesmanager";
		 
		        		else urltogo="errorpage";
		        		
		        		
		        	}else if( dashpart.equals("appointmanager")) {

		        		urltogo =  "appointmanager" ;
		        		
		        	}else if( dashpart.equals("patientmanager")) {

		        		urltogo =  "patientmanager" ;
		        		
		        	}else if( dashpart.equals("printreports")) {

		        		urltogo =  "printreports" ;
	
		        	}else if( dashpart.equals("")) {

		        		urltogo =  "" ;
	
		        	}else  {
		        		urltogo =  "errorpage" ;
		        		
		        	}
		        	
					
				}
				
				request.setAttribute("dashpart", urltogo);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/dashboard/index.jsp");
				rd.forward(request, response);
				
				
			}else {
				response.sendRedirect("/hopefully/login/");
				
			}


	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
