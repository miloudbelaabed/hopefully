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


$(document).ready(function(){
	let currenpageurl = window.location.pathname;
	let currentps  = currenpageurl+"/";
	
	$('ul.sider-nav li a').each(function(i,item){
		
		if($(this).attr('href') == currenpageurl){
			$(this).parent().addClass('active');
		}else {
			if($(this).attr('href') == currentps){
				
				$(this).parent().addClass('active');
			}
			
		}

		
		

	});
	  
});