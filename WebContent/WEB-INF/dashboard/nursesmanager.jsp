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
					
					
					
						<h4 class="page-title">Nurse Manager :</h4>
						<div class="row">




							<div class="modal fade" id="remove-user-modal" tabindex="-1" role="dialog" aria-labelledby="Remove Nurse : " aria-hidden="true">
								<div class="modal-dialog" role="document">
								  <div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Remove Nurse</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										Are You sure to remove <span id="nameToChange"></span>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
										<button type="button" class="btn btn-danger confirm-remove" data-dismiss="modal">Remove</button>
									</div>
								</div>
							</div>
							</div>
							




							<div id="edit-nurse-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="Edit Nurse :" aria-hidden="true">
							  <div class="modal-dialog modal-lg">
							    <div class="modal-content">
							      <div style="margin-bottom:0;" class="card">
										<div class="card-header">
											<div class="card-title">Edit Nurse Data :</div>
										</div>
										<div class="card-body">
										
											<form id="editNurseForm">

												<div class="form-group">
													<label for="fullname">Full Name : </label>
													<input data-expr="^([\w]{3,})+\s+([\w\s]{3,})+$"  type="text" class="form-control input-square fullname"  name="fullname" placeholder="Full Name">
													<p class="text-danger">Fullname must contain at least first and last name ...</p>
												</div>												
											
												<div class="form-group">
													<label for="email">Email : </label>
													<input data-expr="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$"  type="email" class="form-control input-square email" name="email"  placeholder="Email">
													<p class="text-danger">Email not valid ...</div>
	
												<div class="form-group">
													<label for="pass">Password : </label>
													<input data-expr="^[0-9a-zA-Z+*@!#%/-]{6,}$" type="password" class="form-control input-square pass" name="pass"   placeholder="password">
													<p class="text-danger">Password must contain at least 5 characters ...</p>
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
	
												<div class="form-group">
													<label for="departements">Square Select</label>
													<select required class="form-control input-square departements" name="dep_id"  >
													       
											       			
											       			
										                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																
										                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
										                     	</option>
										 						
															<%}%>
										 						
																							
													
													</select>
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




							<div id="add-nurse-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="Add New Nurse :" aria-hidden="true">
							  <div class="modal-dialog modal-lg">
							    <div class="modal-content">
							      <div style="margin-bottom:0;" class="card">
										<div class="card-header">
											<div class="card-title">Add New Nurse:</div>
										</div>
										<div class="card-body">
										
										
										
										
											<form id="addNurseForm">
												
												<div class="form-group">
													<label for="fullname">Full Name : </label>
													<input data-expr="^([\w]{3,})+\s+([\w\s]{3,})+$"  type="text" class="form-control input-square fullname"  name="fullname" placeholder="Full Name">
													<p class="text-danger">Fullname must contain at least first and last name ...</p>
												</div>
	
	
												<div class="form-group">
													<label for="natid">National id : </label>
													<input data-expr="^[0-9]{9}$"  type="text" class="form-control input-square natid"  placeholder="National id">
													<p class="text-danger">National id must contain 9 numbers ...</p>
												</div>
	
	
												<div class="form-group">
													<label for="email">Email : </label>
													<input data-expr="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$"  type="email" class="form-control input-square email" name="email"  placeholder="Email">
													<p class="text-danger">Email not valid ...</div>
	
												<div class="form-group">
													<label for="pass">Password : </label>
													<input data-expr="^[0-9a-zA-Z+*@!#%/-]{6,}$" type="password" class="form-control input-square pass" name="pass"   placeholder="password">
													<p class="text-danger">Password must contain at least 5 characters ...</p>
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
	
												<div class="form-group">
													<label for="departements">Square Select</label>
													<select required class="form-control input-square departements" name="dep_id"  >
													       
											       			
											       			
										                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																
										                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
										                     	</option>
										 						
															<%}%>
										 						
																							
													
													</select>
												</div>
												
											</form>
										
										
										
										
										
											<div hidden  class="formResult form-group has-success" style="margin:10px 0;">											
												<input type="text" value="Success" class="form-control" readonly disabled>
											</div>
										
																				
										</div>
										
										
										<div class="card-action" style="text-align: center;">
											<button class="btn btn-success" disabled>Add</button>
											<button class="btn btn-danger" data-dismiss="modal">Cancel</button>
										</div>
										
									</div>
							    </div>
							  </div>
							</div>



							<div class="col-12">
								<div class="card">
									<div class="card-header ">
										<h4 class="card-title">Nurse Table</h4>
										<p class="card-category">Change All User Informations.</p>
									</div>
									<div class="card-body table-responsive">
									
										<div class="col-12 text-center" style="padding: 0;">
											<button id="addNurseBtn" class="addNurseBtn btn btn-primary" style="width: 100%;background: #2e55a5 !important; border-color: #2e55a5 !important;"  data-toggle="modal" data-target="#add-nurse-modal">Add New Nurse</button>
										</div>
									
									
										<table class="table nurses-table table-bordered table-head-bg-primary mt-4 table-striped table-hover">
											<thead>
												<tr>													
													<th scope="col">Name</th>
													<th scope="col">Email</th>
													<th scope="col" hidden>Password</th>
													<th scope="col" hidden>Phone</th>
													<th scope="col" hidden>Birth Date</th>
													<th scope="col">Departement</th>
													<th scope="col">Action</th>
												</tr>
											</thead>
											<tbody>
											
									<% for(beans.nurse ite : nurseDB.getNurses()    ){%>		
			
											<tr>
													<td class="fullname"><%= ite.getFullname() %></td>
													<td class="email"><%= ite.getEmail() %></td>
													<td class="pass" hidden><%= ite.getPass() %></td>
													<td class="phone" hidden><%= ite.getPhone_numb() %></td>
													<td class="bdate" hidden><%= ite.getBirth_date() %></td>
													
													<td class="dep" data-depid="<%= ite.getDep_id() %>"><%= departementDB.getDepById(ite.getDep_id()).getDep_name() %></td>
													
													<td class="td-actions">
														<div class="form-button-action" data-myd="<%=ite.getPerson_id()%>">
															<button id="setToEditBtn" 	type="button" class="btn btn-link btn-simple-primary edit-btn"  data-toggle="modal" data-target="#edit-nurse-modal" data-original-title="Edit User">
																<i class="la la-edit"></i>
															</button>
															<button id="setToRemoveBtn"  type="button"  class="btn btn-link btn-simple-danger remove-btn"  data-toggle="modal" data-target="#remove-user-modal" data-original-title="Remove">
																<i class="la la-times"></i>
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
					<script src="/hopefully/assets/js/nursemanager.js"></script>
			