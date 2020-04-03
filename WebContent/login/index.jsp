<%@page import="beans.nurse"%>
<%@page import="beans.admin"%>
<%@page import="beans.person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%
	person per =  (person) request.getSession().getAttribute("logged");

	if(per!=null){
		
	    response.sendRedirect("/hopefully/dashboard");

	}


%>

<!DOCTYPE html>
<html>
<head><title>Admin Panel</title>
<meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css" type="text/css">
  <link rel="stylesheet" href="/hopefully/css/bootstrap.min.css" type="text/css">

</head>
<body>
<div class="logo">
    
  </div> 
 <h3> 

</h3>

<div class="login-post">
  <center><h1>Sign in</h1></center>
  
  <form  method="post" id="login-form">
  
    <div class="form-group">
      <label for="emailInp">Email address</label>
      <input data-expr="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" name="email" type="email" class="form-control" id="emailInp" aria-describedby="emailHelp" placeholder="Enter email" required>
      <p class="text-danger text-hidden" style="display: block;">Email not valid ...</p>
      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
      <label for="passInp">Password</label>
      <input data-expr="^[0-9a-zA-Z+*@!#%/-]{6,}$" name="password" type="password" id="passInp" class="form-control" placeholder="Password" required>
	  <p class="text-danger text-hidden" style="display: block;">Password must contain at least 5 characters ...</p>
    </div>
    
    <div class="form-group result-login">
    
	    <p class="text-danger 	text-hidden text-center">Entred Data Doesn't Exist ...</p>
		<p class="text-success text-hidden text-center">Login Success Redirecting...</p>
	 	<p class="text-warning text-hidden text-center">Please Verify Your Internet Connection.</p>
	</div>
    
    <center><button id="login-btn" type="button" onclick="login()" class="btn btn-primary" disabled>Submit</button></center>
  </form>
</div>

</div>
<footer><p>Copyright Web Up 2019 By MILOUD BELLABED && KARIM OUCIF</p></footer>


<script src="/hopefully/js/jquery-3.3.1.min.js"></script>
<script src="script.js"></script>



</body>
</html>
