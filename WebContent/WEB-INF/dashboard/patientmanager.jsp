<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>

			<link href= '/hopefully/assets/css/jqueryUi.css' rel='stylesheet'> 


<%
	String curUserType = (String)request.getSession().getAttribute("userType");
	person per = (person) request.getSession().getAttribute("logged");
	
	if(curUserType.equals("Nurse")){
		
		 response.sendRedirect("/hopefully/dashboard");

	}
	
%>

				<div class="container-fluid">
					
					
					
						<h4 class="page-title">Patient Manager :</h4>
						<div class="row">



							




							<div id="edit-nurse-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="Edit Nurse :" aria-hidden="true">
							  <div class="modal-dialog modal-lg">
							    <div class="modal-content">
							      <div style="margin-bottom:0;" class="card">
							      
										<div class="card-header">
											<div class="card-title">Edit Patient Data :</div>
										</div>
										
										<div class="card-body">
										
											<form id="editNurseForm">

												<div class="form-group">
													<label for="fullname">Full Name : </label>
													<input data-expr="^([\w]{3,})+\s+([\w\s]{3,})+$"  type="text" class="form-control input-square fullname"  name="fullname" placeholder="Full Name">
													<p class="text-danger">Fullname must contain at least first and last name ...</p>
												</div>												
											

												<div class="form-group">
													<label for="phone_numb">Phone Number : </label>
													<input data-expr="^0[1-9][0-9]{8}$" type="text" class="form-control input-square phone_numb" name="phone_numb" placeholder="Phone">
													<p class="text-danger">Phone number is not valid...</p>
												</div>
												
												<div class="form-group">
													<label for="birth_date">Birth Date : </label>
													<input data-expr="^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$"  type="text" class="form-control input-square birth_date"  name="birth_date"   placeholder="Birth Date">
													<p class="text-danger">Birth date should be YYYY-MM-DD...</p>
												</div>

												
											</form>
											
											<div hidden  class="formResult form-group has-success" style="margin:10px 0;">											
												<input type="text" value="Success" class="form-control" readonly disabled>
											</div>
										
																				
										</div>
										
										
										<div class="card-action" style="text-align: center;">
											<button class="btn btn-success">Update</button>
											<button class="btn btn-danger" data-dismiss="modal">Cancel</button>
										</div>
										
										
									</div>
							    </div>
							  </div>
							</div>







							<div class="col-12">
								<div class="card">
									<div class="card-header ">
										<h4 class="card-title">Patient Table</h4>
										<p class="card-category">Change All Patient Informations.</p>
									</div>
									<div class="card-body table-responsive">

									
										<table class="table nurses-table table-bordered table-head-bg-primary mt-4 table-striped table-hover">
											<thead>
												<tr>		
													<th scope="col">National ID</th>											
													<th scope="col">Name</th>
													<th scope="col" >Phone</th>
													<th scope="col" >Birth Date</th>
													<th scope="col">Action</th>
												</tr>
											</thead>
											<tbody>
											
									<% for(patient ite : patientDB.getPatients()    ){%>		
			
												<tr>
												
													<td class="natid"><%= ite.getPerson_id() %></td>
													<td class="fullname"><%= ite.getFullname() %></td>
													<td class="phone" ><%= ite.getPhone_numb() %></td>
													<td class="bdate" ><%= ite.getBirth_date() %></td>
																									
													<td class="td-actions">
														<div class="form-button-action" data-myd="<%=ite.getPerson_id()%>">
															<button id="setToEditBtn" 	type="button" class="btn btn-link btn-simple-primary edit-btn"  data-toggle="modal" data-target="#edit-nurse-modal" data-original-title="Edit User">
																<i class="la la-edit"></i>
															</button>
														</div>
													</td>
													
												</tr>


									<% }%>

											</tbody>

											
										</table>
									</div>
								</div>
							</div>

						</div>
					</div>
					
					<script>
					
					   $(".birth_date").datepicker({dateFormat: 'yy-mm-dd' ,maxDate:'+0d',changeYear: true});
		                
					</script>

					<script src="/hopefully/assets/js/patientmanager.js"></script>
			