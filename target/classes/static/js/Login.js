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

//For User Login Validation
function loginValidate(){
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
		$.ajax({
		type: 'POST',
		url: '/loginUser',
		data: params,
		dataType: 'json',
		success: function(response){
			if(response.MESSAGE == "Success"){
				alert("Logged in  Successfully..");
				window.location.href = "/user"
			}else{
				alert("USER doesn't Exist'..");
				window.location.href = "/"
			}
		}
	});
	}
	
}

//To Redirect to Register Page
function login(){
	window.location.href = "/register";
}