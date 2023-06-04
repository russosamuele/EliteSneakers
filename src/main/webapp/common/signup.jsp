<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
	<title>Elite Sneakers - Registrati</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<script src="<%=request.getContextPath()%>/scripts/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajax.js"></script>
</head>


<body>
	
	<%@ include file="header.jsp" %>
	
	
	<main>
	
		<section class="signupSection">
		<h2>Registrati per fare acquisti su Elite Sneakers</h2>
		
		
		<% 
		String error = (String)request.getAttribute("error");
		if(error == null)
			error="";
	
		%>
		
		<p  style="color:red "> <%=error %> </p>
		
		<div id="signupDiv">
		
		<form action="/EliteSneakersEcommerce/SignupServlet" method="post" id="regForm" onsubmit="event.preventDefault();checkSignup(this)">
			<label for="nome">Nome:</label>
			<input class="inputField" type="text" id="nome" name="nome" required onBlur="return validateNome()"><span id="errorName"></span><br>
			
			<label for="cognome">Cognome:</label>
			<input class="inputField" type="text" id="cognome" name="cognome" required onBlur="return validateCognome()"><span id="errorLastname"></span><br>
			
			<label for="email">Email:</label>
			<input class="inputField" type="email" id="email" name="email" required onBlur="return validateEmail()" onChange="return tryEmail()"> <span id="errorEmail"></span> <span id="emailCheckDisponibility"> </span><br>
			
			<label for="password">Password:</label>
			<input class="inputField" type="password" id="password" name="password" required onBlur ="return validatePassword()"><span id="errorpswd"></span><br>
			
			<label for="conferma_password">Conferma Password:</label>
			<input class="inputField" type="password" id="conferma_password" name="conferma_password" required onBlur="return pswMatching()"> <span id="matchError"></span><br>
			
			<label for="dataNascita">Data di nascità:</label>
			<input class="inputField" type="date" id="dataNascita" name="dataNascita" required onBlur="return ageValidate()"> <span id="ageError"></span><br>
		
			<label for="indirizzo">Indirizzo</label>
			<textarea class="inputField" id="indirizzo" name="indirizzo" required></textarea><br>	
				
			<label for="indirizzo_spedizione">Indirizzo di Spedizione:</label>
			<textarea class="inputField" id="indirizzo_spedizione" name="indirizzo_spedizione" required></textarea><br>
			
			<input type="submit" value="Registrati" id="invia" onclick="tryEmail()">
		</form>
		
		<p>Hai già un account? <a href="<%=request.getContextPath()%>/common/login.jsp">Accedi qui</a></p>
		
		</div>
		
		</section>
	</main>
	
	<footer>
		<%@ include file="footer.jsp" %>
	</footer>
</body>
</html>
