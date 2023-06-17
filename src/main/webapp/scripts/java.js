



window.onload = ()=>{
	
	let menu = document.querySelector('#menu-icon');
	let navbar = document.querySelector('.navbar');

console.log(menu);

menu.addEventListener("click", ()=>{
    menu.classList.toggle('bx-x');
    navbar.classList.toggle('open');
})
	
	
}

