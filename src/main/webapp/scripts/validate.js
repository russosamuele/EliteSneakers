
const nameOrLastnamePattern = /^[A-Za-z]+$/;
//const emailPattern = /^\S+@\S+\.\S+$/;
const emailPattern = /^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
const nameErrorMessage = "Un nome valido deve contenere solo lettere";
const lastnameErrorMessage = "Un cognome valido deve contenere solo lettere";
const emailErrorMessage = "Una email valida deve essere nella forma username@domain.ext";


function validateFormElem(formElem, pattern, span, message) {
	if(formElem.value.match(pattern)){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.innerHTML = message;
	span.style.color = "red";
	return false;
}

function validateNome() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanName = document.getElementById("errorName");
	if(!validateFormElem(form.nome, nameOrLastnamePattern, spanName, nameErrorMessage))
		valid = false;
	
	
	return valid;
}

function validateCognome() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanName = document.getElementById("errorLastname");
	if(!validateFormElem(form.cognome, nameOrLastnamePattern, spanName, lastnameErrorMessage))
		valid = false;
	
	
	return valid;
}


function validateEmail() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage))
		valid = false;
	
	return valid;
}





