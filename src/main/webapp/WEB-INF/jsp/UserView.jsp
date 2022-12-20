<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/Cssboot.css">
<link rel="stylesheet" href="/css/viewticket.css">
<link rel="stylesheet" href="/css/Tabulator.css">
<script src="/js/fontAwesome.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/Jquery1.16.0.js"></script>
<script src="/js/Tabulator.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg">
	  <div class="container-fluid">
	    <a class="navbar-brand brand">Netflix</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <!-- <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li> -->
	      </ul>
	      <div class="d-flex logoutbtn">
      		<p>Welcome, ${email}</p>
      		<a data-toggle="tooltip" title="Logout" class="logout"><i class="fas fa-sharp fa-solid fa-power-off fa-xl"></i></a>
      	  </div>
	    </div>
	  </div>
	</nav>
	
	<div class="maincontainer">
		<button type="button" class="btn" id="cancelTicket">Cancel Ticket</button>
		<div id="ticketTable"></div>
	</div>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog modal-dialog-scrollable">
    		<div class="modal-content">
	      		<div class="modal-header">
	        		<h5 class="modal-title" id="exampleModalLabel">Update Ticket</h5>
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
<script src="/js/viewticket.js"></script>
</body>
</html>