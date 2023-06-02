
let menu = document.querySelector('#menu-icon');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('bx-x');
    navbar.classList.toggle('open');
}


function checkDisponibilita(){
	
	let elem = document.getElementById("dispTaglia").textContent;
	let span = document.getElementById("DispError");
	
	let elemInt = parseInt(elem);
	
	elemInt=elemInt-1;
	
	if(elemInt<=0){
		span.style.color = "red";
		span.innerHTML = "Disponibilità limitata (max quantità 1)";
		return false;
	}
	else{
		span.innerHTML="";
		span.style.color="black";
		return true;
	}
		
}