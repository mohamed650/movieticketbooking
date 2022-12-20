$(document).ready(function(){
	loadTickets();
	
	$(".logout").unbind('click').bind('click',function(e){
		$.ajax({
			type: "GET",
			url: "/logout",
			success: function(){
				window.location.href= "/"
			}      
		});
	});
	
	//To check Sessison
	$("#cancelTicket").click(function(){
		$.ajax({
	    	type   : "GET",
	        url    : "/checkSession",
	        success: function(response){
				if(response.MESSAGE == "ALIVE"){
					deleteDetails();
				}else {
					alert("Session expired please login again");
					window.location.href = "/"
	        	}
	      	}
		});
	});
	
});

//To delete or cancel ticket
function deleteDetails(){
	if($('.select-row').is(':checked')){
		deleteValue();
	}else{
		alert("please select a row..");
	}
}
function deleteValue(){
	var select = table.getSelectedRows().id;
	var data = table.getSelectedData(select);
	var id = data[0].reservation_id;
	var moviename = data[0].movie_name;
	var theatrename = data[0].theatre_name;
	var showtime = data[0].show_time;
	var dateofreservation = data[0].date_of_reservation;
	var noofseatsbooked = data[0].no_of_seatsbooked;
	var params = {
		reservation_id: id,
		movie_name: moviename,
		theatre_name: theatrename,
		show_time: showtime,
		date_of_reservation: dateofreservation,
		no_of_seatsbooked: noofseatsbooked
	};
	if(confirm("Are You Sure You Want to Cancel Ticket!!!")){
		$.ajax({
		type: 'POST',
		url: "/deleteBooking",
		data: params,
		dataType: 'json',
		success: function(response){
			if(response.MESSAGE == "Success"){
				alert("Deleted");
				loadTickets();
			}else if(response.MESSAGE == "ShowBegins") {
				alert("You Cannot delete now The show is yet to begin");
				loadTickets();
			}
			else {
				alert("Unable to Delete...");
				loadTickets();
			}
		}
	});
	}else {
		window.location.href= "/viewTicket";
	}
}

//User Ticket Tabulator
var table;
function loadTickets(){
	table = new Tabulator("#ticketTable", {
		layout:"fitDataFill",
		height: "360px",
		rowFormatter:function(row){
			if(row.getData().ticket_exist_flg == false){
				row.getElement().style.backgroundColor = "#ffbe33";
				row.getElement().style.pointerEvents = "none"; 
			}
		},
	    pagination: "local",
    	paginationSize: 10,
    	paginationSizeSelector: [3, 6, 8, 10],
    	movableColumns: true,
    	paginationCounter:"rows",
		ajaxURL    :  "/loadTicketDetails",
		ajaxConfig : "get",
		width: 50,
		headerSort: false,
      	cssClass: 'text-center',
      	frozen: true,
	    columns    : [
		{
		title: '',	
      	field: 'IsSelected',
      	formatter: function (cell, formatterParams, onRendered) {
			return '<input type="checkbox" class="select-row" readonly />';
      	},
     	headerSort: false,
      	cssClass: 'text-center',
      	frozen: true,
      	cellClick: function (e, cell) {
        var $element = $(cell.getElement());
        var $chkbox = $element.find('.select-row');
        if (cell.getData().IsSelected) {
          $chkbox.prop('checked', false);
          cell.getRow().deselect();
          cell.getData().IsSelected = false;
        }else {
          $chkbox.prop('checked', true);
          cell.getRow().select();
          cell.getData().IsSelected = true;
        }
      	}
    	},
    	{title:"Reservation", field:"reservation_id"},
		{title:"Email_ID", field:"email_id"},
		{title:"Movie Name", field:"movie_name"},
		{title:"Theatre Name", field:"theatre_name"},
		{title:"Booking Date", field:"date_of_reservation",hozAlign:"center"},
		{title:"Show Time", field:"show_time", hozAlign:"center"},
		{title:"No of Seats", field:"no_of_seatsbooked", hozAlign:"center"},
		{title:"Total Price", field:"total_price", hozAlign:"center"},
		{title:"Ticket Status", field:"ticket_exist_flg", hozAlign:"center"},
		],
		});
}
