
var tinynav=false;
var hidden=false;
var oldscroll=0;


$(window).scroll(function () {

    if(!hidden){

        if ($(window).scrollTop() >= 50) {
            if(!tinynav){
                $('.navbar-hd').addClass('topped');
                tinynav=true;
            }
        } else {
            if(tinynav){
                $('.navbar-hd').removeClass('topped');
                tinynav=false;
            }
        }

    }

    
    if($(window).scrollTop()>800){


        if(!hidden){
            $('.navbar-hd').addClass('hidden-navbar-hd');
            hidden=true;
        }

    }


    if($(window).scrollTop() < oldscroll){

        if(hidden){
            $('.navbar-hd').removeClass('hidden-navbar-hd');
            hidden=false;
        }


    }

    oldscroll = $(window).scrollTop();



});

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




function makeAppoint(){


        $.ajax({
            type: frm.attr('method'),
            url:'/hopefully/appointmake',
            data: frm.serialize(),
            success: function (data) {
             	
            	console.log(data['title']);
            	showAlert(data);
            },
            error: function (data) {
            	
            	let jsonObject = {title:'Connection Error',msg:'Please Verify Your Internet Connection.',type:'danger'};
            	showAlert(jsonObject);
                
            }
        });
        
        return false;
    
}


validateInputOnBlur();

$('.bet-but.book-btn').attr('disabled',false);

function validateInputOnBlur(){
	
	$('#appointForm input').each(function(i,li) {
		
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





  