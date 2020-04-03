<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>
<%
	String curUserType = (String)request.getSession().getAttribute("userType");
	person per = (person) request.getSession().getAttribute("logged");

%>






    			<link href= '/hopefully/assets/css/jqueryUi.css' rel='stylesheet'> 
    		
    		<style>
				
				#toprintDiv {
					display:none!important;

				}
				
			</style>
				
				    		
			<style media="print">
				
				#toprintDiv {
					display:block!important;
				}
				
			</style>
				    		

    		
    		
    		
				<div class="container-fluid">
					
					
					
						<h4 class="page-title">Appointments Manager :</h4>
						<div class="row">


							<div class="modal fade" id="pay-app-modal" tabindex="-1" role="dialog" aria-labelledby="Pay The Appointment : " aria-hidden="true">
								<div class="modal-dialog" role="document">
								  	<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Pay The Appointment</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											Are You Sure To Pay The Appointment <span id="nameToChange"></span>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary cancel-pay" data-dismiss="modal">Cancel</button>
											<button type="button" class="btn btn-primary pay-theAppoin">Pay</button>
										</div>
									</div>
								</div>
							</div>







							<div id="book-appoint-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="Add New Nurse :" aria-hidden="true">
							  <div class="modal-dialog modal-lg">
							    <div class="modal-content">
							      <div style="margin-bottom:0;" class="card">
							      
										<div class="card-header">
											<div class="card-title">Book An Appointment:</div>
										</div>
										
										<div class="card-body">

	          							  <form id="appointFormBook" class="container" method="post">
              
											<div class="row">

								                  <div class="form-group col-6 col-sm-4">
								                    <label for="phone-input">Phone Number</label>
								                    <input data-expr="^0[1-9][0-9]{8}$" name="phone_numb" type="text" class="form-control btn-sm" id="phone-input" placeholder="0666666666">
								                  </div>
								
								                  <div class="form-group col-6 col-sm-4">
								                    <label for="select-box">Example select</label>
								                    <select name="dep_id" required="" style="height: 33.75px!important;" class="form-control btn-sm" id="departement-selecter">
								                      <option disabled="" value="0" selected="">Chose Departement</option>
								                      
								                  	
													
								 						
													
											                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																		
												                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
												                     	</option>
												 						
																	<%}%>
																									
								 						
								 						
								                    </select>
								                  </div>
								
								                  <div class="form-group col-6 col-sm-4">
								                    <label for="name-input">Full Name</label>
								                    <input data-expr="^([\w]{3,})+\s+([\w\s]{3,})+$" name="full_name" type="text" class="form-control btn-sm" id="name-input" placeholder="Name">
								                  </div>
								
								                  <div class="form-group col-6 col-sm-4">
								                    <label for="natid-input">National Id</label>
								                    <input name="person_id" data-expr="^[0-9]{9}$" type="text" class="form-control btn-sm" id="natid-input" placeholder="Name">
								                  </div>




																			                  
												  <div class="form-group col-6 col-sm-4">
								                    <label for="birthdate-input">Birth Date </label>
								                    <input  id="book-birthDate"  data-expr="^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$" name="birth_date" type="text" class="form-control btn-sm birth_picker " id="birthdate-input" placeholder="YYYY-MM-DD">
								                  </div>
								
									



												  <div class="form-group col-6 col-sm-4">
								                    <label for="appoindate-input">Appointment Date </label>
								                    <input id="book-appointDate" data-expr="^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$" name="appoin_date" type="text" class="date_picker form-control btn-sm appoin_picker " id="appoindate-input" placeholder="YYYY-MM-DD">
								                  </div>
								                  
								                  <div class="form-group col-12">

													<a onclick="makeAppoint()" class="btn btn-primary bet-but book-btn" style="width:100%; color:white;"><span><i style="margin-right: 10px;" class="fa fa-bookmark"></i></span>Book Now</a>
													
								                  </div>
								 
							           	     </div>
									                
	            						  </form>
	
										  <div class="error-container">
										    <p class="text-danger full_name-error"  >Fullname must contain at least first and last name.</p>
										    <p class="text-danger phone_numb-error" >Phone number is not valid...</p>
										    <p class="text-danger person_id-error">National Id must contain 9 numbers ...</p>
										    <p class="text-danger birth_date-error">You must chose a valid birth date ...</p>
										    <p class="text-danger appoin_date-error">You must chose a valid appoint date ...</p>
										    
							   			    <p class="text-danger departemntsel-error">You must chose a departement ...</p>
							
										  </div>
								
										</div>
										
										
										<div class="card-action" style="text-align: center;background-color: #fff;padding: 20px 0;">
																					
											<button class="btn btn-danger" data-dismiss="modal">Cancel</button>
										</div>
										
									</div>
							    </div>
							  </div>
							</div>






							<div class="col-12">
								<div class="card">
									<div class="card-header ">
										<h4 class="card-title">Appointments Table :</h4>
										<p class="card-category">Manage All Appointments Data.</p>
									</div>
									
									
									
									
									<div class="card-body table-responsive">
										
										<div class="col-12 text-center" style="padding: 0;">
											<button id="addNurseBtn" class="addNurseBtn btn btn-primary" style="width: 100%;background: #2e55a5 !important; border-color: #2e55a5 !important;"  data-toggle="modal" data-target="#book-appoint-modal">Book An Appointment</button>
									

									
										</div>
										 	
										
										<form method="post" action="appoint" id="appointForm">
              
							                <div class="row " style="padding: 0 15px;" >
							                
   												<div class="form-group  col-12 col-md-6">
													<label for="dep_id">Departement : </label>
													<select required class="form-control input-square departements" name="dep_id"  >
														<% if (curUserType.equals("Administrator")) {%>
									
										                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																
										                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
										                     	</option>
										 						
															<%}%>
														<% } %>
														
														<% if (curUserType.equals("Nurse")) {%>
									
										                  	<% 
																int dep_id = ((nurse)per).getDep_id();
										                  		String dep_name = departementDB.getDepById(dep_id).getDep_name();
															%>
																
										                      	<option value="<%= dep_id %>"> <%= dep_name %>
										                     	</option>
										 						
															
														<% } %>
														
														
														
														
													
													</select>
												</div>
												
												<div class="form-group  col-12 col-md-6">
													<label for="date_picker">Date : </label>
													<input type="text" id="date_picker" class="form-control date_picker"  name="date_picker" > 
												</div>
							
							                
							                </div>	
							                					                
						              	</form>
									
									
										<table class="table nurses-table table-bordered table-head-bg-primary mt-4 table-striped table-hover">
											<thead>
												<tr>													
													<th scope="col" hidden>ID</th>
													<th scope="col">Patient Name</th>													
													<th scope="col">Patient Phone</th>
													<th scope="col" hidden>Birth Date</th>
													<th scope="col">Booked Time</th>													
													<th scope="col">Action</th>
												</tr>
											</thead>
											
											<!-- tbody Filled With Js Auto -->
											<tbody id="tableBody">
												
								
											</tbody>

											
										</table>
									</div>
								</div>
							</div>



							<div id="toprintDiv" >
							
							</div>
							
						</div>
					</div>
					
					<script type="text/javascript">
						
						
						const tableBody 	= $('#tableBody');
	
						var selectedDate 	= '';
						var selectedDep 	= '1';

					
						function formatDate(date) {
						    var d = new Date(date),
						        month = '' + (d.getMonth() + 1),
						        day = '' + d.getDate(),
						        year = d.getFullYear();
	
						    if (month.length < 2) 
						        month = '0' + month;
						    if (day.length < 2) 
						        day = '0' + day;
	
						    return [year, month, day].join('-');
						}

					
						$(document).ready(function() { 
					          
				            $(function() { 
				            	
				                $(".date_picker").datepicker({dateFormat: 'yy-mm-dd' ,maxDate:'+7d',minDate: '-0d'});
				                


				                $(".date_picker").datepicker().datepicker("setDate", new Date());
				                
				                
				                $(".birth_picker").datepicker({dateFormat: 'yy-mm-dd' ,maxDate:'+0d',changeYear: true});
				                

				                
				                

					          
				                selectedDate = formatDate(new Date());
				                updateTable();
				            }); 
				            
				            $('.date_picker').change(function() { 
				            	
				        		//selectedDate = formatDate($('#date_picker').datepicker('getDate')); 
				        		
				        		//console.log('changed : '+formatDate($('#date_picker').datepicker('getDate')) );
				        		
				        		selectedDate = formatDate($('#date_picker').datepicker('getDate'));
				        		
				        		updateTable();
				            });
				            
				            $( ".departements" ) .change(function () {    
				            	selectedDep = $('.departements').val();
				            	updateTable()
			            	});  
				            
				            const timeoutloading = setInterval((ev) => {
								updateTable();
								//hadi hya 
							}, 20000);

				        }) 
				        
					</script>
			
			
				
			
			
			

			
			
			
					<script src="/hopefully/assets/js/appointmanager.js"></script> 
				<script src="/hopefully/assets/js/printThis.js"></script> 
				
			