$(document).ready(function(){
	loadMovieCards();
	//loadTheatreCards();
	loadTheatre();
	loadMovie();
	
	$("#TheatreModal").on("hidden.bs.modal", function() {
    	$(".modal-body form")[0].reset();
  	});
  	$("#MovieModal").on("hidden.bs.modal", function() {
    	$(".modal-body #movieform")[0].reset();
  	});
  	$("#ShowTimingsModal").on("hidden.bs.modal", function() {
    	$(".modal-body #showform")[0].reset();
  	});
  	$("#DescriptionModal").on("hidden.bs.modal", function() {
    	$(".modal-body #descform")[0].reset();
  	});
});

//To Load Movie Cards
function loadMovieCards(){
	$.ajax({
		type: 'POST',
		url: '/loadMovieInformation',
		data: {},
		dataType: 'json',
		success: function(response){
			processMovieInformation(response);
		}
	});
}

function processMovieInformation(map){
	var s = '';
	for(data in map){
		if(map[data].length != 0){
			s += '<div class="col-md">';
			s += '<div class="box">';
			s += '<img src='+data+' alt="image" class="model">';
			s += '<div class="details">';
			s += '<p>'+map[data]+'</p>';
			s += '</div>';
			s += '</div>';
			s += '</div>';
		}
	}
	$(".container1").append(s);
}

/*function loadTheatreCards(){
	$.ajax({
		type: 'POST',
		url: '/loadTheatreInformation',
		data: {},
		dataType: 'json',
		success: function(response){
			processTheatreInformation(response);
		}
	});
}

function processTheatreInformation(map){
	var s = '';
	for(data in map){
		if(map[data].length != 0){
			s += '<div class="col-md">';
			s += '<div class="box" >';
			s += '<img src="/images/K.G.F_Chapter_2.jpg" alt="KGF Chapter 2" class="model">';
			s += '<div class="details">';
			s += '<p>'+map[data]+'</p>';
			s += '</div>';
			s += '</div>';
			s += '</div>';
		}
	}
	$(".container2").append(s);
}*/

//To load theatre information
function loadTheatre(){
	$.ajax({
		type: 'POST',
		url: '/loadTheatre',
		data: {},
		success: function(response){
			var map = JSON.parse(response);
			processTheatre(map);
		}
		
	})
}

//To process theatre information
function processTheatre(map){
	var s = '<option value="-1">Please Select a Theatre</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';    
	}
	console.log(s);
	$("#movie_theatre_id").html(s);
	$("#show_theatre_id").html(s);
	
	$("#show_theatre_id").on('change', function(){
		var params = {
			theatre_id: $("#show_theatre_id").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadMovieNames',
		data: params,
		dataType: 'json',
		success: function(response){
			processMovieNames(response);
		}
		
	});
	});
}

function processMovieNames(map){
	var s = '<option value="-1">Please Select a Movie</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';  
	}
	console.log(s);
	$("#show_movie_id").html(s);
}

function loadMovie(){
	$.ajax({
		type: 'POST',
		url: '/loadMovie',
		data: {},
		success: function(response){
			var map = JSON.parse(response);
			processMovie(map);
		}
		
	})
}

function processMovie(map){
	var s = '<option value="-1">Please Select a Movie</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';    
	}
	console.log(s);
	$("#desc_movieid").html(s);
}


function insertTheatre(){
	var params = {
		theatre_id: $("#theatre_id").val(),
		theatre_name: $("#theatre_name").val(),
		theatre_address: $("#theatre_address").val(),
		maximum_seats: $("#maximum_seats").val(),
		theatre_screens: $("#theatre_screens").val()
	};
	if(params.theatre_id == ""){
		alert("Theatre Id Cannot be Null!!");
		return false;
	}else if(params.theatre_name == ""){
		alert("Theatre Name Cannot be Null!!");
		return false;
	}else if(params.theatre_address == ""){
		alert("Theatre Address Cannot be Null!!");
		return false;
	}else if(params.maximum_seats == ""){
		alert("Maximum Seats Cannot be Null!!");
		return false;
	}else if(params.theatre_screens == ""){
		alert("Theatre Screens Cannot be Null!!");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/insertTheatre',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "Success"){
					alert("Theatre Added Successfully...");
					window.location.href = '/loginAdmin';
					loadTheatreCards();
				}else if(response.MESSAGE == "EXIST"){
					alert("Theatre Already Present!!!");
				}else {
					alert("Unable to add theatre!!!");
					window.location.href = '/loginAdmin';
				}
			}
		});
	}
}


function insertMovie(){
	var fullpath = $("#imgfile").val();
	var params = {
		theatre_id: $("#movie_theatre_id").val(),
		movie_id: $("#movie_id").val(),
		movie_name: $("#movie_name").val(),
		movie_language: $("#movie_language").val(),
		movie_standard: $("#movie_standard :selected").text(),
		movie_genre: $("#movie_genre").val(),
		movie_start_date: $("#movie_start_date").val(),
		movie_end_date: $("#movie_end_date").val(),
		ticket_price: $("#ticket_price").val(),
		run_time: $("#run_time").val(),
		imgfile: fullpath.replace(/^.*[\\\/]/, '')
	};
	if(params.theatre_id == ""){
		alert("Theatre Id Cannot be Null!!");
		return false;
	}else if(params.movie_id == ""){
		alert("Movie Id Cannot be Null!!");
		return false;
	}else if(params.movie_name == ""){
		alert("Movie Name Cannot be Null!!");
		return false;
	}else if(params.movie_language == ""){
		alert("Movie Language Cannot be Null!!");
		return false;
	}else if(params.movie_standard == ""){
		alert("Movie Standard Cannot be Null!!");
		return false;
	}else if(params.movie_genre == ""){
		alert("Movie Genre Cannot be Null!!");
		return false;
	}else if(params.movie_start_date == ""){
		alert("Start Date Cannot be Null!!");
		return false;
	}else if(params.movie_end_date == ""){
		alert("End Date Cannot be Null!!");
		return false;
	}else if(params.ticket_price == ""){
		alert("Ticket Price Cannot be Null!!");
		return false;
	}else if(params.run_time == ""){
		alert("Run Time Cannot be Null!!");
		return false;
	}else {
		$.ajax({
			type: 'POST',
			url: '/insertMovie',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "Success"){
					alert("Movie Added Successfully...");
					window.location.href = '/loginAdmin';
					loadMovieCards();
				}else if(response.MESSAGE == "EXIST"){
					alert("Movie Already Present!!!");
				}else {
					alert("Unable to add movie!!!");
					window.location.href = '/loginAdmin';
				}
			}
		})
	}
}

function insertTimings(){
	var params = {
		theatre_id: $("#show_theatre_id").val(),
		movie_id: $("#show_movie_id").val(),
		show_timings: $("#show_timings").val()
	};
	if(params.theatre_id == ""){
		alert("Theatre Id Cannot be Null!!");
		return false;
	}else if(params.movie_id == ""){
		alert("Movie Id Cannot be Null!!");
		return false;
	}else if(params.show_timings == ""){
		alert("Show Timings Cannot be Null!!");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/insertTimings',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "Success"){
					alert("Timings Added Successfully...");
					window.location.href = '/loginAdmin';
				}else if(response.MESSAGE == "EXIST"){
					alert("Timings already Present for particular Movie!!!");
				}else {
					alert("Unable to add timings!!!");
					window.location.href = '/loginAdmin';
				}
			}
		});
	}
}

function insertDescription(){
	var params = {
		movieid: $("#desc_movieid").val(),
		moviename: $("#desc_movieid :selected").text(),
		moviedirector: $("#desc_moviedirector").val(),
		movieproducer: $("#desc_movieproducer").val(),
		movieactor: $("#desc_movieactor").val(),
		movieactress: $("#desc_movieactress").val()
	};
	if(params.moviename == ""){
		alert("Movie Name Cannot be Null!!");
		return false;
	}else if(params.moviedirector == ""){
		alert("Movie Director Cannot be Null!!");
		return false;
	}else if(params.movieproducer == ""){
		alert("Movie Producer Cannot be Null!!");
		return false;
	}else if(params.movieactor == ""){
		alert("Movie Actor Cannot be Null!!");
		return false;
	}else if(params.movieactress == ""){
		alert("Movie Actress Cannot be Null!!");
		return false;
	}else {
		$.ajax({
			type: 'POST',
			url: '/insertDescription',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "Success"){
					alert("Description Added Successfully...");
					window.location.href = '/loginAdmin';
				}else if(response.MESSAGE == "EXIST"){
					alert("Description already Present for particular Movie!!!");
				}else {
					alert("Unable to add description!!!");
					window.location.href = '/loginAdmin';
				}
			}
		})
	}
}

$("#addTheatre").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				$("#TheatreModal").modal("show");
			}else {
				alert("Session expired please login again");
				window.location.href = "/"
        	}
      	}
	});
});

$("#addMovie").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				$("#MovieModal").modal("show");
			}else {
				alert("Session expired please login again");
				window.location.href = "/"
        	}
      	}
	});
});

$("#addshows").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				$("#ShowTimingsModal").modal("show");
			}else {
				alert("Session expired please login again");
				window.location.href = "/"
        	}
      	}
	});
});

$("#adddesc").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				$("#DescriptionModal").modal("show");
			}else {
				alert("Session expired please login again");
				window.location.href = "/"
        	}
      	}
	});
});

function logout(){
	$.ajax({
		type: "GET",
		url: "/logout",
		success: function(){
			window.location.href= "/"
		}      
	});
}


