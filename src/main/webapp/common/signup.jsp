<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
	<title>Elite Sneakers - Registrati</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<script src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>


<body>
	
	<%@ include file="header.jsp" %>
	
	
	<main>
		<h2>Registrati per fare acquisti su Elite Sneakers</h2>
		
		
		<% 
		String error = (String)request.getAttribute("error");
		if(error == null)
			error="";
	
		%>
		
		<p  style="color:red "> <%=error %> </p>
		
		
		<form action="/EliteSneakersEcommerce/SignupServlet" method="post" id="regForm">
			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" required onBlur="return validateNome()"><span id="errorName"></span><br>
			
			<label for="cognome">Cognome:</label>
			<input type="text" id="cognome" name="cognome" required onBlur="return validateCognome()"><span id="errorLastname"></span><br>
			
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required onBlur="return validateEmail()"> <span id="errorEmail"></span><br>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required><br>
			
			<label for="conferma_password">Conferma Password:</label>
			<input type="password" id="conferma_password" name="conferma_password" required><br>
			
			<label for="dataNascita">Data di nascità:</label>
			<input type="date" id="dataNascita" name="dataNascita" required><br>
		
			<label for="indirizzo">Indirizzo</label>
			<textarea id="indirizzo" name="indirizzo" required></textarea><br>	
				
			<label for="indirizzo_spedizione">Indirizzo di Spedizione:</label>
			<textarea id="indirizzo_spedizione" name="indirizzo_spedizione" required></textarea><br>
			
			<input type="submit" value="Registrati">
		</form>
		
		<p>Hai già un account? <a href="<%=request.getContextPath()%>/common/login.jsp">Accedi qui</a></p>
	</main>
	
	<footer>
		<%@ include file="footer.jsp" %>
	</footer>
</body>
</html>
