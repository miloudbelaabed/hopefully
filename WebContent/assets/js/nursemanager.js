var torHandleId  = -1;
let toHandleTr;
let nameToRem;
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
	
	
	if(operation=="ed"){
		
		operationform = 'editNurseForm';
		
		
	}else {
		
		operationform = 'addNurseForm';
		torHandleId= $('#addNurseForm .natid').val();
	}
	

	

    $.ajax({
    	
        url: "/hopefully/nurseUpdate",
        
        type: "POST",
        
        data: 'ope='+operation+'&id='+torHandleId+'&'+$('#'+operationform).serialize(),
       
        success: function (data) {

        	if (data['success'] == true  && data['connection']==true){
    			
        		var formdata = $("#"+operationform).serializeArray();
    			var dataJson = {};
    			$(formdata ).each(function(index, obj){
    				dataJson[obj.name] = obj.value;
    			});

    			
        		if(operation=='ed'){
        			
        			tableRowUpdate(dataJson);
        			
        			editFormResultMsg('success','Nurse Updated Successfully.');
        			
        		
        		}else {
        			
        			editFormResultMsg('success','New Nurse Inserted Successfully.');
        			
        			
        			
        		}
        		
        		$('#edit-nurse-modal .btn-danger').prop('disabled',true);
        		
        		setTimeout(function(){ 
        			torHandleId = -1;
        			$('#'+operationform).closest('.modal').modal('toggle');
        			if(operation!='ed'){
        	            location.reload(true);
        			}
        			
        		}, 1500);

        		

        		
     		} else if(data['success']==false && data['connection']==true){
        		
     			if(data['adminprob']==true){
     				
            		editFormResultMsg('error',"Admin Can't be a nurse please enter another email .");

     				
     			}else {
     				
            		for (key in data){
            			if(data[key]==false){
            				$('#'+operationform+' .'+key).parent().addClass('has-error');
            				$('#'+operationform+' .'+key).next().css('display','block');
            			}
            		}
            		editFormResultMsg('error','Some Data Are Not Correct Please Verify it.');

     			}

        		

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
	
	validateInputOnBlur('add-nurse-modal');
	validateInputOnBlur('edit-nurse-modal');
	
	
	
	$('#edit-nurse-modal .btn-danger').click(function(){
		clearAllInputsError('edit-nurse-modal');
	});
	
	$('#add-nurse-modal .btn-danger').click(function(){
		clearAllInputsError('add-nurse-modal');
	});
	
	
	$('#edit-nurse-modal .btn-success').click(function(){
		updateNurse('ed');
	});
	
	
	$('#add-nurse-modal .btn-success').click(function () {
		updateNurse('add');
	});

	
	$('.remove-btn').click(function(){
		
		setTohandle(this);
		
		nameToRem = toHandleTr.children(":first").text();
		
		nametochange.html(nameToRem);
		
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
	
	
	$('.addNurseBtn').click(function(){
		
		clearAllInputsError('add-nurse-modal');
		operationform="addNurseForm";
		
	});
	
	
	
	
	$('.confirm-remove').click(function(){
		removeNurse();
	});
	

});






















function tableRowUpdate(itemData){

	
	$(toHandleTr).find('.dep').attr('data-depid',itemData['dep_id']);

	$(toHandleTr).find('.fullname').html(itemData['fullname']);
	
	$(toHandleTr).find('.email').html(itemData['email']);
	
	$(toHandleTr).find('.pass').html(itemData['pass']);
	
	$(toHandleTr).find('.phone').html(itemData['phone']);
	
	$(toHandleTr).find('.bdate').html(itemData['bdate']);
	
	$(toHandleTr).find('.dep').html(  $("#"+operationform+" .departements option[value='"+itemData['dep_id'] +"']").text()  );

	
	

	
}



