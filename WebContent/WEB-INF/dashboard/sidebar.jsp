<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>
    
    

    
<div class="sidebar">
		<div class="scrollbar-inner sidebar-wrapper">
			<div class="user">
				<div class="photo">
					<img src="/hopefully/assets/img/<%=userType %>.png">
				</div>
				<div class="info">
					<a class="" data-toggle="collapse" href="#collapseExample" aria-expanded="true">
						<span>
							<%= per.getFullname() %>
							<span class="user-level"><%= userType %></span>
							<span class="caret"></span>
						</span>
					</a>
					<div class="clearfix"></div>

					<div class="collapse in" id="collapseExample" aria-expanded="true" style="">
						<ul class="nav">
							<li>
								<a href="#profile">
									<span class="link-collapse">My Profile</span>
								</a>
							</li>
							<li>
								<a href="#edit">
									<span class="link-collapse">Edit Profile</span>
								</a>
							</li>
							<li>
								<a href="#settings">
									<span class="link-collapse">Settings</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			
			<ul class="nav sider-nav">
				
				
					<li class="nav-item">
					
						<a href="/hopefully/dashboard/">
							<i class="la la la-dashboard"></i>
							<p>Dashboard</p>
							
						</a>
						
					</li>
				
					<li class="nav-item">
					
						<a href="/hopefully/dashboard/appointmanager">
							<i class="la la-calendar-o"></i>
							<p>Appointments Manager</p>
							
						</a>
						
					</li>
					
					
				<%if(userType == "Administrator"){ %>
				
					<li class="nav-item">
					
						<a href="/hopefully/dashboard/nursesmanager">
							<i class="fa fa-user-md"></i>
							<p>Nurses Manager</p>
							<span class="badge badge-count"><%= nurseDB.getNurses().size() %></span>
						</a>
						
					</li>
						
				<% } %>
				
					<li class="nav-item">
					
						<a href="/hopefully/dashboard/patientmanager">
							<i class="la la la-ambulance"></i>
							<p>Patient Manager</p>
						</a>
						
					</li>
	
					<li class="nav-item">
					
						<a href="/hopefully/dashboard/printreports">
							<i class="la la-archive"></i>
							<p>Print Reports</p>
						</a>
						
					</li>
	
	

			</ul>
		</div>
</div>
  