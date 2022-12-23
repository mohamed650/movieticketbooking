<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/Cssboot.css">
<link rel="stylesheet" href="/css/admin.css">
<script src="/js/fontAwesome.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/Jquery1.16.0.js"></script>
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
      		<p>Welcome, admin</p>
      		<a data-toggle="tooltip" title="Logout" onclick="logout()"><i class="fas fa-sharp fa-solid fa-power-off fa-xl"></i></a>
      	  </div>
	    </div>
	  </div>
	</nav>
	
	<div class="bookbutton">
	   <button type="button" class="btn" id="addTheatre" data-bs-dismiss="modal">Add Theatre</button>
	   <button type="button" class="btn" id="addMovie" data-bs-dismiss="modal">Add Movie</button>
	   <button type="button" class="btn" id="addshows" data-bs-dismiss="modal">Add Show Timings</button>
	   <button type="button" class="btn" id="adddesc" data-bs-dismiss="modal">Add Description</button>
	   <button type="button" class="btn" id="genReport">Generate Report</button>
	   <button type="button" class="btn" id="genExcel">Generate Excel</button>
	</div>
	
    <fieldset class="scheduler-border" style="margin-top: 25px;">
    	<legend class="scheduler-border" style="font-size:32px; color: #004369;">Movies</legend>
    	<div class="row container1"></div>
    </fieldset>
    <!-- <fieldset class="scheduler-border" style="margin-top: 15px;">
		<legend class="scheduler-border" style="font-size:32px">Theatres</legend>
		<div class="row container2"></div>
	</fieldset> -->
	
	<div class="modal fade" id="TheatreModal" tabindex="-1" aria-labelledby="TheatreModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h1 class="modal-title fs-5" id="TheatreModalLabel">Add Theatre</h1>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<form id="form" action="">
        				<div class="row modal-row">
							<div class="col-md">
								<label>Theatre ID: </label><br>
								<input type="text" id="theatre_id" placeholder="Theatre Id">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Theatre Name: </label><br>
								<input type="text" id="theatre_name" placeholder="Theatre Name">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Theatre Address: </label><br>
								<input type="text" id="theatre_address" placeholder="Theatre Address">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Maximum Seats: </label><br>
								<input type="text" id="maximum_seats" placeholder="Maximum Seats">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Screen Type: </label><br>
								<input type="text" id="theatre_screens" placeholder="Screen Type">
							</div>
						</div>
        			</form>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
        			<button type="button" class="btn btn-info" onclick="insertTheatre()">Save changes</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<div class="modal fade" id="MovieModal" tabindex="-1" aria-labelledby="MovieModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog modal-dialog-scrollable">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h1 class="modal-title fs-5" id="MovieModalLabel">Add Movie</h1>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<form id="movieform" action="">
        				<div class="row modal-row">
							<div class="col-md">
								<label>Theatre ID: </label><br>
								<select id="movie_theatre_id">
								</select>
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie ID: </label><br>
								<input type="text" id="movie_id" placeholder="Movie Id">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Name: </label><br>
								<input type="text" id="movie_name" placeholder="Movie Name">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Language: </label><br>
								<input type="text" id="movie_language" placeholder="Movie Language">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Standard: </label><br>
								<select id="movie_standard">
									<option value= -1>Please Select Standard</option>
									<option value="1">13+</option>
									<option value="2">15+</option>
									<option value="3">18+</option>
								</select>
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Genre: </label><br>
								<input type="text" id="movie_genre" placeholder="Movie Genre">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Start Date: </label><br>
								<input type="date" id="movie_start_date">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie End Date: </label><br>
								<input type="date" id="movie_end_date">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Ticket Price: </label><br>
								<input type="text" id="ticket_price" placeholder="Ticket Price">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Run Time: </label><br>
								<input type="time" id="run_time" step="1">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Image: </label><br>
								<input type="file" id="imgfile">
							</div>
						</div>
        			</form>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
        			<button type="button" class="btn btn-info" onclick="insertMovie()">Save changes</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<div class="modal fade" id="ShowTimingsModal" tabindex="-1" aria-labelledby="ShowTimingsModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog">
   	 		<div class="modal-content">
      			<div class="modal-header">
        			<h1 class="modal-title fs-5" id="ShowTimingsModalLabel">Add Show Timings</h1>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
     		 	<div class="modal-body">
        			<form id="showform" action="">
        				<div class="row modal-row">
							<div class="col-md">
								<label>Theatre Name: </label><br>
								<select id="show_theatre_id">
								</select>
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Name: </label><br>
								<select id="show_movie_id">
								</select>
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Show Timings: </label><br>
								<input type="text" id="show_timings" placeholder="Show Timings">
							</div>
						</div>
        			</form>
      			</div>
      			<div class="modal-footer">
			        <button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-info" onclick="insertTimings()">Save changes</button>
     	 		</div>
    		</div>
 		 </div>
	</div>
	
	<div class="modal fade" id="DescriptionModal" tabindex="-1" aria-labelledby="DescriptionModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
  		<div class="modal-dialog">
   	 		<div class="modal-content">
      			<div class="modal-header">
        			<h1 class="modal-title fs-5" id="DescriptionModalLabel">Add Description</h1>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
     		 	<div class="modal-body">
        			<form id="descform" action="">
        				<div class="row modal-row">
							<div class="col-md">
								<label>Movie Name: </label><br>
								<select id="desc_movieid">
								</select>
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Director: </label><br>
								<input type="text" id="desc_moviedirector" placeholder="Movie Director">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Producer: </label><br>
								<input type="text" id="desc_movieproducer" placeholder="Movie Producer">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Actors: </label><br>
								<input type="text" id="desc_movieactor" placeholder="Movie Actor">
							</div>
						</div>
						<div class="row modal-row">
							<div class="col-md">
								<label>Movie Actress: </label><br>
								<input type="text" id="desc_movieactress" placeholder="Movie Actress">
							</div>
						</div>
        			</form>
      			</div>
      			<div class="modal-footer">
			        <button type="button" class="btn btn-outline-info waves-effect" data-bs-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-info" onclick="insertDescription()">Save changes</button>
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
        			<p id="descmovie_name"></p>
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
<script src="/js/admin.js"></script>
</body>
</html>