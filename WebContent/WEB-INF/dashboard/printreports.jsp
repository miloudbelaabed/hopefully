<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dbmanip.*"%>
<%@page import="beans.*"%>
<%@page import="java.sql.DriverManager,java.sql.SQLException" %>



<style media="all" >
.printShow {
	display:none;
}
</style>

<style media="print" >
.printShow {
	display:block!important;
}

</style>

				<link href= '/hopefully/assets/css/jqueryUi.css' rel='stylesheet'> 

				<div class="container-fluid">
					
					
					
						<h4 class="page-title">Print Reports :</h4>
						<div class="row">









							<div class="col-12">
								<div class="card">
									<div class="card-header ">
										<h4 class="card-title">Print Reports</h4>
										<p class="card-category">Print Reports Informations On Specific Date.</p>
									</div>
									<div class="card-body table-responsive">
										<form class="datesForm" >	
										<div style="display:none;">

										  <select name="dep-id"  id="departement-selecter">
								                  
													
											                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																		
												                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
												                     	</option>
												 						
																	<%}%>
																									
								 						
								 						
					                    </select>
										
										</div>
										<div class="row form-chose form-group" style="padding:0 20px;">

													
													<div class="form-group  col-12 col-md-4">
														<label for="start_date">Start Date : </label>
														<input type="text" id="start_date" class="form-control date_picker " name="start_date"> 
													</div>
											
													<div class="form-group  col-12 col-md-4">
														<label for="end_date">End Date : </label>
														<input type="text" id="end_date" class="form-control date_picker " name="end_date"> 
													</div>
													
													<div class="form-group col-12 col-md-4">
													<label for="departements">Square Select</label>
													<select required class="form-control input-square departements" name="dep_id"  >
													       
											       			<option value="0" selected>Chose Departement</option>
											       			
										                  	<% for(departement dep: departementDB.getDepartements() ) {  %>
																
										                      	<option value="<%= dep.getDep_id() %>"> <%= dep.getDep_name() %>
										                     	</option>
										 						
															<%}%>
										 						
																							
													
													</select>
												</div>
													
												
												<div class="form-group col-12" >
													
													<a id="getData" class="btn btn-primary form-control" style="width: 100%;background: #2e55a5 !important;color:#fff!important;" >Get Data</a>
												</div>
												
									
										
										</div>
										
										</form>
									
										<div class="form-group col-12" style=" padding: 0 10px;" >
											
											<button id="printData" class="btn btn-primary form-control" style="width: 100%;background: #2e55a5 !important;color:#fff!important;"  disabled>Print Data</button>
										
										</div>
									
									
					
										
									<div id="toPrintContainer">
										
										<div class="printShow row" style="padding:0 15px;">
										
												<h1 class="header-title col-12 text-center" >
													HopeFully Hospital
												</h1>
												
												<div class="printbody col-12">
												
													<p class="left">All Hospital Appointments From  </p>
													
													<p class="right"><span class="startdate"></span> to <span class="enddate"></span></p>													
													
													
												</div>
												
												
												
										</div>
										
										
										<table class="table nurses-table table-bordered table-head-bg-primary mt-4 table-striped table-hover">
											<thead>
												<tr>		
													<th scope="col">Appoint Date</th>											
													<th scope="col">Patient Name</th>
													<th scope="col" >Dep Name</th>
													<th scope="col" >Book Date</th>
													<th scope="col" >Birth Date</th>
													

												</tr>
											</thead>
											
											<tbody>
												
												
											
						

											</tbody>

											
										</table>
										
									</div>
									
									</div>
								</div>
							</div>

						</div>
					</div>


				
									
					<script type="text/javascript">
						
						
						const tableBody 	= $('tbody');
	

					
						$(document).ready(function() { 
					          
		
				                $(".date_picker").datepicker({dateFormat: 'yy-mm-dd' });


				                $('#start_date').change(function() { 
				                    startDate = $(this).datepicker('getDate'); 
				                    $("#end_date").datepicker("option", "minDate", startDate); 
				                }) ;
				  
				                $('#end_date').change(function() { 
				                    endDate = $(this).datepicker('getDate'); 
				                    $("#start_date").datepicker("option", "maxDate", endDate); 
				                }) ;
				                
				                
				                $('#getData').click(function(){
				                	
				                	fillTableData();
				                });
				                
								 $('#printData').click(function(){
				                	
									 printTableData();
				                });
				                
				                
				                
				        }) 
				        
					</script>
					
					<script src="/hopefully/assets/js/printpapers.js"></script>
								
					<script src="/hopefully/assets/js/printThis.js"></script>
			
				
	
			