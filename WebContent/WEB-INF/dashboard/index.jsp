
<!DOCTYPE html>
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>
<%
	person per =  (person) request.getSession().getAttribute("logged");
	String userType = "Nurse";
	
	if(per instanceof admin ){
		
		userType = "Administrator";
	}

	String pageToload =(String) request.getAttribute("dashpart");
	
	

%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>Hopefully Dashboard</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<link rel="stylesheet" href="/hopefully/assets/css/bootstrap.min.css">
	
	<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	 -->
	 
	 <link rel="stylesheet" href="/hopefully/assets/css/nunito.css">
	 
	<link rel="stylesheet" href="/hopefully/assets/css/ready.css">
	<link rel="stylesheet" href="/hopefully/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/hopefully/assets/css/demo.css">
	<script src="/hopefully/assets/js/core/jquery.3.2.1.min.js"></script>
	<script src="/hopefully/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="/hopefully/assets/js/core/popper.min.js"></script>
	<script src="/hopefully/assets/js/core/bootstrap.min.js"></script>
	<script src="/hopefully/assets/js/main.js"></script>
	
</head>
<body>


	<div class="wrapper">
		<div class="main-header">
			<div class="logo-header">
				<a href="/hopefully/dashboard/" class="logo">
					HopeFully
				</a>

				
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<button class="topbar-toggler more"><i class="la la-ellipsis-v"></i></button>
			</div>
			<nav class="navbar navbar-header navbar-expand-lg">
				<div class="container-fluid">
					<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">


						<li class="nav-item dropdown">
							<a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#" aria-expanded="false"> <img src="/hopefully/assets/img/<%=userType %>.png" alt="user-img" width="36" class="img-circle"><span ><%= per.getFullname() %></span></span> </a>
							<ul class="dropdown-menu dropdown-user">
								<li>
									<div class="user-box">
										<div class="u-img"><img src="/hopefully/assets/img/<%=userType %>.png" alt="user"></div>
										<div class="u-text">
											<h4><%= per.getFullname() %></h4>
											<p class="text-muted"><%= ((user)per).getEmail() %></p><a href="profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a></div>
										</div>
									</li>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#"><i class="ti-user"></i> My Profile</a>
									<a class="dropdown-item" href="#"></i> My Balance</a>									
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#"><i class="ti-settings"></i> Account Setting</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/hopefully/logout"><i class="fa fa-power-off"></i> Logout</a>
								</ul>
								<!-- /.dropdown-user -->
							</li>
						</ul>
					</div>
				</nav>
			</div>




			<%@include file="sidebar.jsp" %>
	





			<div class="main-panel">
				<div class="content">
					

					
					<% if(pageToload.equals("") ){ %>
					
						<%@include file="defaultDash.jsp" %>
						
					<% }else { 
						
						 pageContext.include(pageToload+".jsp");

						
					} %>
				
			
				</div>
				

			</div>
		</div>
	</div>





<script src="/hopefully/assets/js/plugin/chartist/chartist.min.js"></script>
<script src="/hopefully/assets/js/plugin/chartist/plugin/chartist-plugin-tooltip.min.js"></script>
<script src="/hopefully/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>
<script src="/hopefully/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="/hopefully/assets/js/plugin/jquery-mapael/jquery.mapael.min.js"></script>
<script src="/hopefully/assets/js/plugin/jquery-mapael/maps/world_countries.min.js"></script>
<script src="/hopefully/assets/js/plugin/chart-circle/circles.min.js"></script>
<script src="/hopefully/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="/hopefully/assets/js/ready.min.js"></script>






</body>


</html>