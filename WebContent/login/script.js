let emailInput;
let passInput ;



$(document).ready(function(){
	
	emailInput 	= 	$('#emailInp');
	passInput 	=	$('#passInp');

	validateInputOnBlur();



	
});




function login(){

	let email = $(emailInput).val();
	let pass = $(emailInput).val();
	
    $.ajax({
        type: 'post',
        url:'/hopefully/loginDash',
        data: $('#login-form').serialize(),
        beforeSend:function(){
        	$('.result-login .text-danger').addClass('text-hidden');
    		$('.result-login .text-warning').addClass('text-hidden');
    		$('.result-login .text-success').addClass('text-hidden');
        },
        success: function (data) {
         	
        	if(data['found']){

        		$('.result-login .text-success').removeClass('text-hidden');
        		setTimeout(() => {
        			location.reload(true);
				}, 1000);
        		
        	}else {
        		if(data['connection']){     		
	        		$('.result-login .text-danger').removeClass('text-hidden');		
        		}else {
                	$('.result-login .text-warning').removeClass('text-hidden');
        		}
        	}
        	
        },
        error: function (data) {
        	$('.result-login .text-warning').removeClass('text-hidden');
        }
    });
    
	
	return false;
	
}



function validateInputOnBlur(){
	
	$('#login-form input').each(function(i,li) {
		
		 $(this).keyup(function() {
			 	let exp = new RegExp( $(this).attr('data-expr') ,'i');
				let val = $(this).val();
				
				if($('.is-valid').length == 1 && exp.test(val) ){
					$('#login-btn').attr('disabled',false);					
				}
		 });
		
		$(this).blur(function (){
			
			let exp = new RegExp( $(this).attr('data-expr') ,'i');
			let val = $(this).val();
			
			if( exp.test(val) ){
				
				$(this).removeClass('is-invalid');
				$(this).addClass('is-valid');
				
				$(this).next().addClass('text-hidden');


				if($('.is-valid').length == 2){
					
					$('#login-btn').attr('disabled',false);					
					
				}
				
			}else {
				
				$(this).removeClass('is-valid');
				$(this).addClass('is-invalid');
				
				$('#login-btn').attr('disabled',true);
				
				$(this).next().removeClass('text-hidden');
			
				
			}

		});
		


	});
	

}


