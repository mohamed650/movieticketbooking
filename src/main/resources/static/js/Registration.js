//To validate Registration
function validateUser(){
	var params = {
		email_id: $("#email_id").val(),
		user_name: $("#user_name").val(),
		contactno: $("#contactno").val(),
		password: $("#password").val(),
		address: $("#address").val()
	};
	var mailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var phoneFormat = /^[6-9]\d{9}$/;
	if(params.user_name == "" && params.email_id == "" && params.password == "" && params.contactno == "" && params.address == ""){
		alert("Fields Cannot be empty!!!");
		return false;
	}else if(params.user_name == ""){
		alert("Username Cannot be empty!!!");
		return false;
	}else if(params.email_id == ""){
		alert("Email Cannot be empty!!!");
		return false;
	}else if(!(params.email_id.match(mailFormat))){
		alert("Invalid Email address!!!");
		return false;
	}else if(params.contactno == ""){
		alert("Contact No Cannot be empty!!!");
		return false;
	}else if(!(params.contactno.match(phoneFormat))){
		alert("Phone number should be 10 digits only!!!");
		return false;
	}else if(params.password == ""){
		alert("Password Cannot be empty!!!");
		return false;
	}else if((params.password).length <= 6){
		alert("Password should be atleast Six characters!!!");
		return false;
	}else if((params.password).length >= 12){
		alert("Password should not exceeds more than twelve characters!!!");
		return false;
	}else if(params.address == ""){
		alert("Address Cannot be empty!!!");
		return false;
	}
	$.ajax({
		type: 'POST',
		url: '/insertUser',
		data: params,
		dataType: 'json',
		success: function(response){
			if(response.MESSAGE == "Success"){
				alert("Registered Successfully..");
				window.location.href = "/";
			}else{
				alert("User Exist...");
				window.location.href = "/register";
			}
		}
	});
}