<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>
<style>


.chose-traitment .chose-item{
	background: #FFFFFF;
    text-align: center;
 
    min-height: 325px;
	transition:all .3s ;
 	margin-bottom: 20px;
	
}

.chose-traitment .chose-item:hover{
	box-shadow: 0 2px 14px 0 rgba(0,0,0,.12);
}

.chose-traitment .chose-item a {

    display: block;
    height: 100%;
    width: 100%;
    padding: 50px 30px;  
   	color: #0000048a;
 	
}



.chose-traitment .chose-item *:hover {
	text-decoration:none; 
}
.chose-traitment .chose-item  h4{
    margin: 10px 0;
}
.chose-traitment .chose-item  span{
    margin-top: 30px;
    display: block;
}

.chose-traitment .chose-item  .fa{
      color: #1c6e9ea6;
      font-size:5em;
      margin-bottom:10px;
      transition:all .3s;
}
.chose-traitment .chose-item:hover  .fa{

	color: #1c6e9e;

}



</style>



				<div class="container-fluid">
					
					
					
						<h4 class="page-title">HopeFully Dashboard :</h4>
						<div class="row">




							<div class="col-12">

											
											
										<div class="container chose-traitment">
											<div class="row">
											
												<%if(userType == "Administrator"){ %>
												
													<div class="col-sm-6 col-md-4">
														<div class="chose-item">
															<a href="/hopefully/dashboard/nursesmanager">
																<i class="fa fa-user-md"></i>
																<h4><strong>Nurses</strong></h4>
																<span>Manage all nurses information and user data.</span>
															</a>
														</div>
													</div>
													
												<% } %>
											
												<div class="col-sm-6 col-md-4">
													<div class="chose-item">
														<a href="/hopefully/dashboard/appointmanager">
															<i class="fa fa-calendar"></i>
															<h4><strong>Appointments</strong></h4>
															<span>Manage all appointments and pay todays ones.</span>
														</a>
													</div>
												</div>
												
												
												<div class="col-sm-6 col-md-4">
													<div class="chose-item">
														<a href="/hopefully/dashboard/patientmanager">
															<i class="fa  fa-ambulance"></i>
															<h4><strong>Patients</strong></h4>
															<span>Manage all Patient personal data.</span>
														</a>
													</div>
												</div>
					
															
												<div class="col-sm-6 col-md-4">
													<div class="chose-item">
														<a href="/hopefully/dashboard/printreports">
															<i class="fa  fa-archive"></i>
															<h4><strong>Reports</strong></h4>
															<span>Print Reports By Date.</span>
														</a>
													</div>
												</div>
													


											</div>
										</div>



							</div>

						</div>
					</div>

			