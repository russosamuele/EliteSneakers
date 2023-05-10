<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
	<title>Elite Sneakers - Registrati</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
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
		
		
		
		<form action="/EliteSneakersEcommerce/SignupServlet" method="post">
			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" required><br><br>
			
			<label for="cognome">Cognome:</label>
			<input type="text" id="cognome" name="cognome" required><br><br>
			
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required><br><br>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required><br><br>
			
			<label for="conferma_password">Conferma Password:</label>
			<input type="password" id="conferma_password" name="conferma_password" required><br><br>
			
			<label for="age">Eta:</label>
			<input type="text" id="age" name="age" required><br><br>
		
			<label for="indirizzo">Indirizzo</label>
			<textarea id="indirizzo" name="indirizzo" required></textarea><br><br>	
				
			<label for="indirizzo_spedizione">Indirizzo di Spedizione:</label>
			<textarea id="indirizzo_spedizione" name="indirizzo_spedizione" required></textarea><br><br>
			
			<input type="submit" value="Registrati">
		</form>
		
		<p>Hai già un account? <a href="<%=request.getContextPath()%>/common/login.jsp">Accedi qui</a>.</p>
	</main>
	
	<footer>
		<%@ include file="footer.jsp" %>
	</footer>
</body>
</html>
