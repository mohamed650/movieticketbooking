//To redirect to videos
function play(){
	window.open('https://youtu.be/oqxAJKy0ii4', '_blank');
}
function movenetflix(){
	window.open('https://www.netflix.com/in/title/81040344', '_blank');
}

function viewUser(){
	window.location.href = "/viewTicket";
}

//To search through cards
function searchWholeCards() {
  var input, filter, cards, cardContainer, title, i;
  input = document.getElementById("myFilter");
  filter = input.value.toUpperCase();
  cards = document.getElementsByClassName("box");
  title = document.getElementsByClassName("movienames");
  for (i = 0; i < cards.length; i++) {
    title = cards[i].querySelector(".details");
    if (title.innerHTML.toUpperCase().indexOf(filter) > -1) {
      cards[i].style.display = "";
    } else {
      cards[i].style.display = "none";
    }
  }
}

//To search through moviename
function searchMovie() {
  var input, filter, cards, cardContainer, title, i;
  input = document.getElementById("moviename");
  filter = input.value.toUpperCase();
  cards = document.getElementsByClassName("box");
  for (i = 0; i < cards.length; i++) {
    title = cards[i].querySelector(".movienames");
    if (title.innerText.toUpperCase().indexOf(filter) > -1) {
      cards[i].style.display = "";
    } else {
      cards[i].style.display = "none";
    }
  }
}

//To search through theatrename
function searchTheatre() {
  var input, filter, cards, cardContainer, title, i;
  input = document.getElementById("theatrename");
  filter = input.value.toUpperCase();
  cards = document.getElementsByClassName("box");
  for (i = 0; i < cards.length; i++) {
    title = cards[i].querySelector(".theatrenames");
    if (title.innerText.toUpperCase().indexOf(filter) > -1) {
      cards[i].style.display = "";
    } else {
      cards[i].style.display = "none";
    }
  }
}

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
			s += '<div class="box" data-bs-toggle="modal" data-bs-target="#moviecontent">';
			s += '<img src='+data+' alt="KGF Chapter 2" class="model">';
			s += '<div class="details">';
			s += '<p class="movienames">'+map[data][0]+'</p>';
			s += '<span class="theatrenames">'+map[data][1]+'</span>'
			s += '</div>';
			s += '</div>';
			s+= '</div>';
		}
	}
	
	$(".container1").append(s);
}

$(document).on('click', '.box', function(event){
	var params = {
		moviename: $(this).find('p.movienames').text()
	};
	$.ajax({
		type: 'POST',
		url: '/loadDescription',
		data: params,
		dataType: 'json',
		success: function(response){
			movieDescription(response);
		}
	})
});
//To open modal
/*$(".card").click(function(){
	var params = {
		movieid: $(this).find("span").text()
	};
	$.ajax({
		type: 'POST',
		url: '/loadDescription',
		data: params,
		dataType: 'json',
		success: function(response){
			movieDescription(response);
		}
	})
	
});*/


function movieDescription(map){
	for(data in map){
		var s = "<span>Movie Name: </span>"+map[data][0]+"<br>";
	}
	$("#movie_name").html(s);
	
	for(data in map){
		var s = "<span>Movie Director: </span>"+map[data][1]+"<br>";
	}
	$("#moviedirector").html(s);
	
	for(data in map){
		var s = "<span>Movie Producer: </span>"+map[data][2]+"<br>";
	}
	$("#movieproducer").html(s);
	
	for(data in map){
		var s = "<span>Movie Actor: </span>"+map[data][3]+"<br>";
	}
	$("#movieactor").html(s);
	
	for(data in map){
		var s = "<span>Movie Actress: </span>"+map[data][4]+"<br>";
	}
	$("#movieactress").html(s);
}


// To load Theatre names
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
//To Process Theatre Names
function processTheatre(map){
	var s = '<option value="-1">Please Select a Theatre</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';    
	}
	$("#theatre_id").html(s);
	
	//To process Theatre Seats
	$("#theatre_id").on('change', function(){
		var params = {
			theatre_id: $("#theatre_id").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadTheatreSeats',
		data: params,
		dataType: 'json',
		success: function(response){
			processTheatreSeats(response);
		}
		
	});
	});
	
	//To process Movie Dates
	$("#theatre_id").on('change', function(){
		var params = {
			theatre_id: $("#theatre_id").val(),
			movie_id: $("#movie_id").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadMovieDates',
		data: params,
		dataType: 'json',
		success: function(response){
			processMovieDates(response);
		}
		
	});
	});
	
	//To process Theatre Shows
	$("#theatre_id").on('change', function(){
		var params = {
			movie_id: $("#movie_id").val(),
			theatre_id: $("#theatre_id").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadTheatreShows',
		data: params,
		dataType: 'json',
		success: function(response){
			processTheatreShows(response);
		}
		
	});
	});
}

/*function processTheatreMovies(map){
	var s = '<option value="-1">Please Select a Movie</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';  
	}
	console.log(s);
	$("#movie_id").html(s);
}*/

function processMovieDates(map){
	for(data in map){
		$("#date_of_reservation").attr('min', data);
		$("#date_of_reservation").attr('max', map[data]);
	}
}

function processTheatreShows(map){
	var s = '<option value="-1">Please Select a Show</option>';
	for(data in map){
		for(var i=0; i<map[data].length; i++){
			s += '<option value="' + data + '">' + map[data][i] + '</option>';
		}
		  
	}
	$("#show_time").html(s);
	
	//Process theatre seats
	$("#show_time").on('change', function(){
		var params = {
			movie_id: $("#movie_id").val(),
			theatre_id: $("#theatre_id").val(),
			date_of_reservation: $("#date_of_reservation").val(),
			show_time: $("#show_time :selected").text()
		};
		$.ajax({
		type: 'POST',
		url: '/loadTheatreSeats',
		data: params,
		dataType: 'json',
		success: function(response){
			processTheatreSeats(response);
		}
		
		});
	});
}

//To load Theatre Seats
function processTheatreSeats(map){
	var s = '<option value="-1">Please Select Seats</option>';
	for(data in map){
		for(var i = 1; i<= map[data]; i++){
			s += '<option value="' + i + '">' + i + '</option>';
		}
		  
	}
	$("#no_of_seatsbooked").html(s);
	
	//To print ticket price
	$("#no_of_seatsbooked").on('change', function(){
		var params = {
			theatre_id: $("#theatre_id").val(),
			movie_id: $("#movie_id").val(),
			no_of_seatsbooked: $("#no_of_seatsbooked").find(":selected").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadTicketPrice',
		data: params,
		dataType: 'json',
		success: function(response){
			document.getElementById("total_price").value = response;
		}
		
	});
	});
}

//To load movie details
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
	$("#movie_id").html(s);
	
	//To process theatrenames
	$("#movie_id").on('change', function(){
		var params = {
			movie_id: $("#movie_id").val()
		};
		$.ajax({
		type: 'POST',
		url: '/loadTheatreNames',
		data: params,
		dataType: 'json',
		success: function(response){
			processTheatreNames(response);
		}
		
	});
	});
		
}

function processTheatreNames(map){
	var s = '<option value="-1">Please Select a Theatre</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';  
	}
	$("#theatre_id").html(s);
}

//To check Session
$("#booknow").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				$("#exampleModal").modal("show");
			}else {
				alert("Session expired please login again");
				window.location.href = "/"
        	}
      	}
	});
});

$("#viewticket").click(function(){
	$.ajax({
    	type   : "GET",
        url    : "/checkSession",
        success: function(response){
			if(response.MESSAGE == "ALIVE"){
				window.location.href = "/viewTicket";
			}else {
				alert("Session expired please login again");
				window.location.href = "/";
        	}
      	}
	});
});


//To Book Ticket
function ticketBook(){
	var params = {
		email_id: $("#email_id").val(),
		movie_id: $("#movie_id").val(),
		movie_name : $("#movie_id :selected").text(),
		theatre_id: $("#theatre_id").val(),
		theatre_name: $("#theatre_id :selected").text(),	
		date_of_reservation: $("#date_of_reservation").val(),
		show_time: $("#show_time :selected").text(),
		no_of_seatsbooked: $("#no_of_seatsbooked").val(),
		total_price: $("#total_price").val()
	};
	if(params.email_id == ""){
		alert("Email cannot be empty!!");
		return false;
	}else if(params.movie_id == 0){
		alert("Please Select Movie!!");
		return false;
	}else if(params.theatre_id == 0){
		alert("Please Select Theatre!!");
		return false;
	}else if(params.date_of_reservation == ""){
		alert("Please Select Date!!");
		return false;
	}else if(params.show_time == ""){
		alert("Please Select Time!!");
		return false;
	}else if(params.no_of_seatsbooked == 0){
		alert("Please Select Seats!!");
		return false;
	}else {
		$.ajax({
			type: 'POST',
			url: '/bookTicket',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "Success"){
					if(Notification.permission === "granted"){
						showNotification();
						sendEmail();
						window.location.href = "/user";
					}else{
						alert("Ticket Booked Successfully..");
						window.location.href = "/user";
					}
				}else{
					alert("You cannot book ticket now!!");
				}
			}
		});
	}
}

function sendEmail(){
	$.ajax({
		type: 'POST',
		url: '/sendMail',
		success: function(response){
			console.log(response)
			if(response.MESSAGE == "Success"){
				mailNotification();
			}else {
				alert("Error while sending the Mail..");
			}
		}
	})
}

//To push desktop notification
function showNotification(){
	const notification = new Notification("Movie Ticket Booking", {
		body: "Ticket Booked Successfully.."
	})
}

function mailNotification(){
	alert("hello")
	const notification = new Notification("Movie Ticket Booking", {
		body: "Mail Sent Successfully.."
	})
}

//To ask user permission to push notifications
if(Notification.permission === "granted"){
	console.log(Notification.permission);
}else if(Notification.permission != "denied"){
	Notification.requestPermission().then(permission => {
		if(permission == "granted"){
			console.log(Notification.permission);
		}
	})
}



$(document).ready(function(){
	if(adminuser === "USER"){
		loadMovieCards();
		loadTheatre(); 
		loadMovie();
	}
	
	//To reset the form
	$("#exampleModal").on("hidden.bs.modal", function() {
    	$(".modal-body form")[0].reset();
  	});
  	
  	$(".logout").unbind('click').bind('click',function(e){
		$.ajax({
			type: "GET",
			url: "/logout",
			success: function(){
				window.location.href= "/"
			}      
		});
	});
	
});

