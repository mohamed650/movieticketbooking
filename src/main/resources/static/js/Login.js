$(document).ready(function(){
	$('#selectRole').on('change.otp', function(){
		$('#otpfield').toggle($(this).val() == '0');
		$('#otpbtn').toggle($(this).val() == '0');
	}).trigger('change.otp');
});
const inputs = document.querySelectorAll(".input");


function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}


inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});

/*document.getElementById("selectRole").onchange = function(){
	if(this.value == '1'){
		alert("hello")
		document.getElementById("otp").style.display = "none";
	}	
}*/

function getOtp(){
	var params = {
		email_id: $("#email_id").val(),
		password: $("#password").val()
	}
	if(params.email_id == "" && params.password == ""){
		alert("Email and Password should not be Empty....");
		return false;
	}else if(params.email_id == "" && params.password != ""){
		alert("Email should not be Empty....");
		return false;
	}else if(params.email_id != "" && params.password == ""){
		alert("Password should not be Empty....");
		return false;
	}else {
		$.ajax({
			type: 'POST',
			url: '/getOtp',
			data: params,
			dataType: 'json',
			success : function(response){
				if(response.MESSAGE == "Success"){
					alert("Otp is sent to Your Mail");
				}else{
					window.location.href = "/";
				}
			}
		});
	}
}

//For User Login Validation
function loginValidate(){
	var roleValue = $("#selectRole").val();
	if(roleValue == '0'){
		var params = {
			email_id: $("#email_id").val(),
			password: $("#password").val(),
			otp: $("#otp").val()
		};
		if(params.email_id == "" && params.password == "" && params.otp == ""){
			alert("Email, Password an Otp should not be Empty....");
			return false;
		}else if(params.email_id == "" && params.password != "" && params.otp != ""){
			alert("Email should not be Empty....");
			return false;
		}else if(params.email_id != "" && params.password == "" && params.otp == ""){
			alert("Password should not be Empty....");
			return false;
		}else if(params.email_id != "" && params.password != "" && params.otp == ""){
			alert("Otp should not be Empty....");
			return false;
		}else{
			$.ajax({
				type: 'POST',
				url: '/loginUser',
				data: params,
				dataType: 'json',
				success: function(response){
					if(response.MESSAGE == "Success"){
						alert("Logged in  Successfully..");
						window.location.href = "/user"
					}else if(response.MESSAGE == "Invalid OTP"){
						alert("Invalid OTP, Please Enter Correct OTP");
						return false;
					}else{
						alert("USER doesn't Exist'..");
						window.location.href = "/"
					}
				}
			});
		}	
	}else {
		var params = {
			email_id: $("#email_id").val(),
			password: $("#password").val()
		};
		if(params.email_id == "" && params.password == ""){
			alert("Email and Password should not be Empty....");
			return false;
		}else if(params.email_id == "" && params.password != ""){
			alert("Email should not be Empty....");
			return false;
		}else if(params.email_id != "" && params.password == ""){
			alert("Password should not be Empty....");
			return false;
		}else if(params.email_id == "admin" && params.password == "admin"){
			window.location.href = "/loginAdmin";
		}else{
			alert("Invalid Admin Credentials..");
			return false;
		}
	}
}

//To Redirect to Register Page
function login(){
	window.location.href = "/register";
}