let chosedAppoint='';

$('.cancel-pay').click(function(){
	
	chosedAppoint = '';
	
});

function printPayTicket(){
	
	let appData = {
			
		fullname: $(chosedAppoint).find('.patientName').text(),
		
		dep: $('.departements option[value='+$('.departements').val()+']').text(),
		
		appdate: formatDate($('.date_picker').datepicker('getDate')) ,
		
		bookdate:$(chosedAppoint).find('.bookedTime').text()
	};
	
	$.ajax({
		
        url: "/hopefully/printappcard", 
        
        type: "POST",        
        
        data:  appData,
        
        success: function(data){
        	
        	
        	$('#toprintDiv').html(data);

        	$('#toprintDiv').printThis();
        	
        	//$('#toprintDiv').html('');

        },
        error : function(){
        	
        	console.log('Error f');
        }
	});
	
	
}



$('.pay-theAppoin').click(function(){
	
	if(chosedAppoint!=''){
		
		let appoint_id = $(chosedAppoint).find('.id').html();
		
		let idexpr = new RegExp( '^[0-9]{1,}$' ,'i');
		
		if(idexpr.test(appoint_id)){
			
			
			
			
			 $.ajax({
			    	
			        url: "/hopefully/payappointment",
			        
			        type: "POST",
			        
			        data: 'appoint_id='+appoint_id,
			       
			        success: function (data) {
			        	
			        	jsonObject = {title:'Appointment Not Paid',msg:'Error While Paying The Appointment Verify Your Internet Connection.',type:'danger'};

			        	if(data['paid']){
			        		
			        		jsonObject = {title:'Appointment Paid Successfully',msg:'The Appointment Has Paid Successfully.',type:'success'};

			        		$(chosedAppoint).alert();
			        		
			        		printPayTicket();
			        		
			        		setTimeout(function(){ 
			        			
			        			$(chosedAppoint).remove(); }, 2000);

			        		}
			        	
			        	$('#pay-app-modal').modal('toggle');
			        	
			        	showAlert(jsonObject);
			        	
			        },error:function(xhr){
			        	
			        	jsonObject = {title:'Appointment Not Paid',msg:'Error While Paying The Appointment Verify Your Internet Connection.',type:'danger'};
			        	
			        	showAlert(jsonObject);
			        	
			        }
			        
			    });
			
		}
		
	}

	
	
});
// START FROM HERE
//$('.pay-theAppoin').click(function() {
  //  $('.container').printThis();
//}) 

// END HERE

function showAlert (dataObj){

	$.notify({
		icon: 'la la-bell',
		title: dataObj['title'],
		message: dataObj['msg'],
	},{
		type: dataObj['type'] ,
		placement: {
			from: "top",
			align: "center"
		},
		time: 1000,
	});



}



function updateTable(){

	let dateExpr = new RegExp( "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$" ,'i');

	let depExpr = new RegExp( '^[0-9]{1,2}$' ,'i');

	
	if( dateExpr.test(selectedDate) && depExpr.test(selectedDep) ){
		
		 $.ajax({
		    	
		        url: "/hopefully/appointmentsJson",
		        
		        type: "POST",
		        
		        data: 'dep_id='+selectedDep+'&appoint_date='+selectedDate,
		       
		        success: function (data) {
		        	
		        	
		        	let htmlContent ="" ;
		        	
		        	
		        	
		        	if(Object.keys(data).length == 0){
		        		
		        		htmlContent ='<tr rowspan=4><td class="text-center" colspan=4>No Appointments On This Date</td></tr>'
		        		
		        	}else {
		        	
			        	
			        	
			        	$.each(data, function(i, item) {
			        		
			        		htmlContent+= tableRowMake(item);
			        		
			        	});
			        	

		        	}
		        	
		        	tableBody.html(htmlContent);
		        	
		        	$('.pay-app-btn').click(function(){		        		
		        		chosedAppoint = this.closest('tr');
		        	});	
		        	
		        },
		        error: function (xhr) {
		        	
		        	editFormResultMsg('error','Connection Error Please Verify Your Internet Connection.');
		        	
		        }
		    });
	}else {
		jsonObject = {title:'Data Not Correct',msg:'Please Enter A valid date or chose a correct departement.',type:'danger'};
		showAlert(jsonObject);
	}
}
function tableRowMake(itemData){
	let editAction ='';
	
	if(selectedDate==formatDate(new Date())){
		editAction = `					
							
							<button  type="button"  class="btn btn-link btn-simple-danger pay-app-btn"  data-toggle="modal" data-target="#pay-app-modal" data-original-title="Pay The Appointment" title="Pay The Appointment">
								<i class="la la-dollar"></i>
							</button>
							
							<button  type="button"  class="btn btn-link btn-simple-danger pay-app-btn"  data-toggle="modal" data-target="#pay-app-modal" data-original-title="Pay The Appointment" title="Pay The Appointment">
								<i class="fa fa-envelope-o"></i>
							</button>
					`;
	}
	
	let htmlItem = `	<tr>
					<td class="id" hidden>${itemData['appoint_id']}</td>
						
				
														
					<td class="patientName"> ${itemData['pat']['fullname']} </td> 
				
					<td class="patientPhone"> ${itemData['pat']['phone_numb']} </td> 
				
					<td class="bookedTime">${itemData['book_time']} </td> 
					
					<td class="td-actions">	
						<div class="form-button-action" data-id="${itemData['appoint_id']}">			
							${editAction}
						</div>
					</td>
				
				</tr>`;
	
	return htmlItem;	
	
}









function makeAppoint(){

	if($('.is-valid').length == 6){
	    $.ajax({
	        type:'post',
	        url:'/hopefully/appointmake',
	        data: $('#appointFormBook').serialize(),
	        success: function (data) {
	         	
	        	
	        	$('#book-appoint-modal').modal('toggle');

	        	showAlert(data);
	        },
	        error: function (data) {
	        	
	        	let jsonObject = {title:'Connection Error',msg:'Please Verify Your Internet Connection.',type:'danger'};
	        	$('#book-appoint-modal').modal('toggle');

	        	showAlert(jsonObject);
	            
	        }
	    });
	    
	}

    return false;

}





validateInputOnBlur();




function validateInputOnBlur(){
	
	$('#appointFormBook input').each(function(i,li) {
		
		 $(this).keyup(function() {
			 
			 	let exp = new RegExp( $(this).attr('data-expr') ,'i');
				let val = $(this).val();

				
				if($('.is-valid').length == 5 && exp.test(val) ){
					$(this).addClass("is-valid");	
					$(this).removeClass("is-invalid");	

				}else {
					$(this).removeClass("is-valid");
					$(this).addClass("is-invalid");	

				}
				
		 });
		
		
		
		
		$(this).blur(function (){
			
			let exp = new RegExp( $(this).attr('data-expr') ,'i');
			let val = $(this).val();
			
			if( exp.test(val) ){
				
				$(this).removeClass('is-invalid');
				$(this).addClass('is-valid');
				
				let inpname =  $(this).attr('name');
				$('.error-container .'+inpname+'-error ').css('display','none');
				
				if($('.is-valid').length == 6){
					$('.error-container .departemntsel-error ').css('display','none');
					$('.bet-but.book-btn').attr('disabled',false);
					$('#departement-selecter').addClass('is-valid');
					
				}
				
			}else {
				$(this).removeClass('is-valid');
				$(this).addClass('is-invalid');
				$('.bet-but.book-btn').attr('disabled',true);
				let inpname =  $(this).attr('name');
				$('.error-container .'+inpname+'-error ').css('display','block');
				
			}
						
		});

	});
	
    
    
    $('.birth_picker').change(function() { 
    	$(this).removeClass('is-invalid');
    	$(this).addClass('is-valid');
    	let inpname =  $(this).attr('name');
    	$('.error-container .'+inpname+'-error ').css('display','none');
    });
    
    $('.appoin_picker').change(function() { 
    	$(this).removeClass('is-invalid');
    	$(this).addClass('is-valid');
    	let inpname =  $(this).attr('name');
    	$('.error-container .'+inpname+'-error ').css('display','none');
    	
    	if($('.is-valid').length == 6){
			$('.error-container .departemntsel-error ').css('display','none');
			$('.bet-but.book-btn').attr('disabled',false);
			$('#departement-selecter').addClass('is-valid');
			
		}
		
    });
	
	
    $('#departement-selecter').change(function() {
        if ($(this).val() <20 && $(this).val() >0) {
        	
        	$('.error-container .departemntsel-error').css('display','none');
        	$('#departement-selecter').removeClass('is-invalid');
        	$('#departement-selecter').addClass('is-valid');

        }else{
        	$('.error-container .departemntsel-error').css('display','block');
        	$('#departement-selecter').addClass('is-invalid');
        	$('#departement-selecter').removeClass('is-valid');
        }
        
    	if($('.is-valid').length == 6){
			$('.error-container .departemntsel-error ').css('display','none');
			$('.bet-but.book-btn').attr('disabled',false);
			$('#departement-selecter').addClass('is-valid');
			
		}
		
    });

}




