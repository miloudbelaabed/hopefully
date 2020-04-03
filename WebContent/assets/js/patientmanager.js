var torHandleId  = -1;
let toHandleTr;
let operationform='';

var nametochange = $('#nameToChange') ;




function setTohandle(parent){
	
	torHandleId = $(parent).parent().attr('data-myd');
	toHandleTr = $(parent).closest("tr");
	
}

function updateInput(inpid,newdata){
	
	let val = toHandleTr.find('.'+newdata).text();

	$('#editNurseForm').find('.'+inpid).val(val);

}

function clearAllInputsError(formid){
	
	$('#'+formid+' input').each(function(i,li) {
		
	
				$(this).parent().removeClass('has-success');
				$(this).parent().removeClass('has-error');
				$(this).next().css('display','none');
				$(this).val('');

	});
	
	$('#'+formid+'  .formResult').prop('hidden', true);
	$('#'+formid+'  .formResult').removeClass('has-error');
	$('#'+formid+' .card-action .btn-success').attr('disabled',true);
	
	operationform ='';
	
}

function validateInputOnBlur(modalId){
	
	
	
	$('#'+modalId+' input').each(function(i,li) {
		$(this).blur(function (){
			
			let exp = new RegExp( $(this).attr('data-expr') ,'i');
			let val = $(this).val();
			
			if( exp.test(val) ){
				
				$(this).parent().removeClass('has-error');
				$(this).parent().addClass('has-success');
				$(this).next().css('display','none');
				
				if($('.has-error').length == 0){
					$('.card-action .btn-success').attr('disabled',false);
				}
				
			}else {
				$(this).parent().removeClass('has-success');
				$(this).parent().addClass('has-error');
				$(this).next().css('display','block');
				$('.card-action .btn-success').attr('disabled',true);
			}
		});

	});
	
}


function removeNurse(){
	
    var values = {                   
            'id': torHandleId
    };
    

    $.ajax({
    	
        url: "/hopefully/removeNurse",
        
        type: "POST",
        
        data: values,
       
        success: function (data) {
        	
    		let dataObj = JSON.parse(data);

    		showAlert(dataObj);
              
    		if(dataObj['type']=="success"){
    			toHandleTr.remove();
    		}   
              
              
        },
        error: function () {
            let dataObj = {type:"danger",title:'Connection Error',msg:'Verify Your Internet Connection.'};
            showAlert(dataObj)
        }
    });

}


function editFormResultMsg(resStat,msg){
	
	let formRes = $('#'+operationform).parent().find('.formResult');
	
	formRes.prop('hidden', false);
	
	formRes.removeClass('has-error');
	formRes.removeClass('has-success');

	formRes.addClass(`has-${resStat}`);
	formRes.find('input').val(msg);
		
	
}

function updateNurse(operation){    
	

	operationform = 'editNurseForm';
	

    $.ajax({
    	
        url: "/hopefully/patientUpdate",
        
        type: "POST",
        
        data: 'id='+torHandleId+'&'+$('#editNurseForm').serialize(),
       
        success: function (data) {

        	if (data['success'] == true  && data['connection']==true){
    			
        		var formdata = $("#"+operationform).serializeArray();
    			var dataJson = {};
    			$(formdata ).each(function(index, obj){
    				dataJson[obj.name] = obj.value;
    			});


        			
    			tableRowUpdate(dataJson);
    			
    			editFormResultMsg('success','Patient Updated Successfully.');
    			

        		
        		$('#edit-nurse-modal .btn-danger').prop('disabled',true);
        		
        		setTimeout(function(){ 
        			torHandleId = -1;
        			$('#'+operationform).closest('.modal').modal('toggle');        			
        		}, 1500);

        		

        		
     		} else if(data['success']==false && data['connection']==true){
        		

            		for (key in data){
            			if(data[key]==false){
            				$('#'+operationform+' .'+key).parent().addClass('has-error');
            				$('#'+operationform+' .'+key).next().css('display','block');
            			}
            		}
            		
            		editFormResultMsg('error','Some Data Are Not Correct Please Verify it.');

        		

         	}else {
         		
         		editFormResultMsg('error','Connection Error Please Verify Your Internet Connection.');
        		
         	}
  
              
        },
        error: function (xhr) {
        	
        	editFormResultMsg('error','Connection Error Please Verify Your Internet Connection.');
        	
        }
    });

}





$(document).ready(function(){
	

	validateInputOnBlur('edit-nurse-modal');
	
	
	
	$('#edit-nurse-modal .btn-danger').click(function(){
		clearAllInputsError('edit-nurse-modal');
	});

	
	$('#edit-nurse-modal .btn-success').click(function(){
		updateNurse('ed');
	});




	$('.edit-btn').click(function(){
		
		setTohandle(this);		
		clearAllInputsError('edit-nurse-modal');
		operationform="editNurseForm";
		$('#edit-nurse-modal .btn-danger').prop('disabled',false);
		
		updateInput('fullname','fullname');
		updateInput('email','email');
		updateInput('pass','pass');
		updateInput('phone_numb','phone');
		updateInput('birth_date','bdate');
		
		
		
		let  val =  toHandleTr.find('.dep').attr('data-depid');
		
		if(val>1 && val<15){
			$('#editNurseForm').find('.departements').val(val);
		}
	});
	



});






















function tableRowUpdate(itemData){

	$(toHandleTr).find('.fullname').html(itemData['fullname']);
	
	$(toHandleTr).find('.phone').html(itemData['phone']);
	
	$(toHandleTr).find('.bdate').html(itemData['bdate']);
	

}



