<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<script>var adminuser ="${adminuser}"</script>
<link rel="stylesheet" href="/css/Cssboot.css">
<link rel="stylesheet" href="/css/User.css">
<link rel="stylesheet" href="/css/Tabulator.css">
<script src="/js/fontAwesome.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/Jquery1.16.0.js"></script>
<script src="/js/Tabulator.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <nav>
      <h3 class="brand">Netflix</h3>
      <div class="search">
        <input type="text" placeholder="Search..." id="myFilter" onkeyup="searchWholeCards()">
      </div>
      <div class="logoutbtn">
      	<p>Welcome, ${email}</p>
      	<a class="logout" data-toggle="tooltip" title="Logout" ><i class="fas fa-sharp fa-solid fa-power-off fa-xl"></i></a>
      </div>
    </nav>
    <section>
      <div class="content">
        <h1>Squid <span>Game</span></h1>
        <h3>Season 1</h3>
        <div class="rating">
          <i class="fas fa-solid fa-star"></i>
          <i class="fas fa-solid fa-star"></i>
          <i class="fas fa-solid fa-star"></i>
          <i class="fas fa-solid fa-star"></i>
          <i class="fas fa-solid fa-star-half"></i>
        </div>
        <div class="genre">
          Action <span>/</span> Drama <span>/</span> Thriller
        </div>
        <div class="btns">
          <button onclick="play()"><i class="fas fa-solid fa-play"></i></button>
          <button onclick="movenetflix()"><i class="fas fa-solid fa-download"></i></button>
        </div>
        <p>Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits — with deadly high stakes.
			Starring:Lee Jung-jae,Park Hae-soo,Wi Ha-jun
			Creators:Hwang Dong-hyuk</p>
      </div>
      <div class="searchcontent">
        <input type="text" placeholder="Search Movie" id="moviename" onkeyup="searchMovie()">
        <input type="text" placeholder="Search Theatre" id="theatrename" onkeyup="searchTheatre()">
      </div>
      <div class="bookbutton">
      	<button type="button" class="btn" id="booknow" data-bs-dismiss="modal">Book Now</button>
      	<button type="button" class="btn" id="viewticket" onclick="viewUser()">View Tickets</button>
      </div>
    </section> 
    <!-- <div class="cards">
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent">
        <img src="/images/shershaah.jpg" alt="Shershaah">
        <div class="imgcontent">
          <h4 class="card-title">Shershaah<span>100</span></h4>
          <p class="card-theatre">PVR</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/K.G.F_Chapter_2.jpg" alt="KGF Chapter 2">
        <div class="imgcontent">
          <h4 class="card-title">K.G.F Chapter 2<span>102</span></h4>
          <p class="card-theatre">Inox, Gayathri</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/RRR.jpg" alt="RRR">
        <div class="imgcontent">
          <h4 class="card-title">RRR<span>104</span></h4>
          <p class="card-theatre">Maruthi</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/kantara.jpeg" alt="Kantara">
        <div class="imgcontent">
          <h4 class="card-title">Kantara<span>101</span></h4>
          <p class="card-theatre">Inox, PVR</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/Jawaan.jpg" alt="Jawaan">
        <div class="imgcontent">
          <h4 class="card-title">Jawaan<span>103</span></h4>
          <p class="card-theatre">InoxInsignia</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/Tiger3.jpg" alt="Tiger 3">
        <div class="imgcontent">
          <h4 class="card-title">Tiger 3<span>105</span></h4>
          <p class="card-theatre">Galaxy</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/Pushpa.jpg" alt="Pushpa">
        <div class="imgcontent">
          <h4 class="card-title">Pushpa<span>106</span></h4>
          <p class="card-theatre">Jai Bharath</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/Vikram.jpg" alt="Vikram">
        <div class="imgcontent">
          <h4 class="card-title">Vikram<span>107</span></h4>
          <p class="card-theatre">Maruthi</p>
        </div>
      </div>
      <div class="card" data-bs-toggle="modal" data-bs-target="#moviecontent" >
        <img src="/images/777Charlie.jpg" alt="777 Charlie">
        <div class="imgcontent">
          <h4 class="card-title">777 Charlie<span>108</span></h4>
          <p class="card-theatre">Jai Bharath</p>
        </div>
      </div>
    </div> -->
    <fieldset class="scheduler-border" style="margin-top: 25px;">
    	<legend class="scheduler-border" style="font-size:32px">Movies</legend>
    	<div class="row container1"></div>
  </fieldset>
  </header>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Book Ticket</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
		<form id="form" action="">
			<!-- <img class="" src="#" style="height:350px; width:100%;"/> -->
			<div class="row modal-row">
				<div class="col-md">
					<label>Email: </label><br>
					<input type="text" id="email_id" placeholder="Email Id" value="${email}" disabled>
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>Movie Name: </label><br>
					<select id="movie_id">
					</select>
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>Theatre Name: </label><br>
					<select id="theatre_id" >
					</select>
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>Date of Reservation: </label><br>
					<input type="date" id="date_of_reservation">
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>Show Time: </label><br>
					<select id="show_time">
					</select>
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>No of Seats: </label><br>
					<select id="no_of_seatsbooked">
					</select>
				</div>
			</div>
			<div class="row modal-row">
				<div class="col-md">
					<label>Total Price: </label><br>
					<input type="text" id="total_price" placeholder="Total Price">
				</div>
			</div>
		</form>
	</div>
    <div class="modal-footer">
       <button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
       <input type="button" class="btn btn-info" value="Book Ticket" onclick="ticketBook()">
     </div>
   </div>
 </div>
 </div>

 <div class="modal fade" id="moviecontent" tabindex="-1" aria-labelledby="moviecontent" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog">
    		<div class="modal-content">
     			<div class="modal-header">
        			<h5 class="modal-title" id="staticBackdropLabel">Movie Details</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<p id="movie_name"></p>
        			<p id="moviedirector"></p>
        			<p id="movieproducer"></p>
        			<p id="movieactor"></p>
        			<p id="movieactress"></p>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
      			</div>
    		</div>
  		</div>
	</div>	
	
<script src="/js/MovieTicketBooking.js"></script>
</body>
</html>