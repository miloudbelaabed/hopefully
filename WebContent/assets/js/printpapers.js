
function makeTbRow(data){
	
	let depname = $('#departement-selecter option[value='+ data['dep_id'] +']').text();
	
	let rowContent = `<tr>
				<td>${data['appoint_date']}</td>											
				<td>${data['pat'].fullname}</td>
				<td>${ depname }</td>
				<td>${data['book_time']}</td>
				<td>${data['pat'].birth_date}</td>
		</tr>`;
	return rowContent;
		
}


function printTableData(){
	
	
	$('#toPrintContainer').printThis();
	
	
}

function fillTableData(){
	let dataDates = $('.datesForm').serialize();
	
	$.ajax({
		url: "/hopefully/printreports",        
        type: "POST",   
        data:dataDates,
        success(data){
        	
        	let htmlContent ="" ;
        	
        	
        	
        	if(Object.keys(data).length == 0){
        		
        		htmlContent ='<tr rowspan=5><td class="text-center" colspan=4>No Appointments On This Date</td></tr>'
        		
        	}else {

	        	
	        	$.each(data, function(i, item) {
	        		
	        		htmlContent+= makeTbRow(item);
	        		
	        	});
	        	

        	}
        	
        	tableBody.html(htmlContent);
        	
        	$('#printData').prop('disabled',false);
        	
        	$('.printShow .startdate').text( formatDate( $('#start_date').datepicker('getDate')) );
        	$('.printShow .enddate').text(  formatDate($('#end_date').datepicker('getDate') ));
        	
        	
        },
        error : function(){
        	console.log('Error While connectiong ');
        }
        
		
	});
	
}



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
