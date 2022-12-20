<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link rel="stylesheet" href="/css/Login.css">
<script src="/js/fontAwesome.js"></script>
<script src="/js/Registration.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/Jquery1.16.0.js"></script>
</head>
<body>
	<img class="wave" src="/images/wave.png">
	<div class="container">
		<div class="img">
			<img src="/images/bg.svg">
		</div>
		<div class="login-content">
			<form action="">
				<h2 class="title">Register</h2>
				<div class="input-div email">
           		   <div class="i"> 
           		    	<i class="fas fa-envelope"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Email</h5>
           		    	<input type="text" class="input" id="email_id">
            	   </div>
            	</div>
           		<div class="input-div one">
           		   <div class="i">
           		   		<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
           		   		<h5>Username</h5>
           		   		<input type="text" class="input" id="user_name">
           		   </div>
           		</div>
           		<div class="input-div contact">
           		   <div class="i"> 
           		    	<i class="fas fa-phone"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Contact No</h5>
           		    	<input type="tel" class="input" id="contactno">
            	   </div>
            	</div>
           		<div class="input-div pass">
           		   <div class="i"> 
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Password</h5>
           		    	<input type="password" class="input" id="password">
            	   </div>
            	</div>
            	
            	<div class="input-div address">
           		   <div class="i"> 
           		    	<i class="fas fa-address-book"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Address</h5>
           		    	<input type="text" class="input" id="address">
            	   </div>
            	</div>
            	<input type="button" class="btn" value="Register" onclick="validateUser()">
            </form>
        </div>
    </div>
    <script src="/js/Login.js"></script>
</body>
</html>