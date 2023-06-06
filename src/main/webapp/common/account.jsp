<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.UserDAO, model.UserBean, model.OrdineBean, java.util.*"
    errorPage="EliteSneakersEcommerce/common/error500.jsp"
%>


 <!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Account - Elite Sneakers</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="header.jsp"/>
	
	

	<main>
	
	<section class="accountSection"> 
		<h1>Il mio account</h1>
		
		<div id="accountList">
			<a href="<%=request.getContextPath()%>/common/VisualizzaOrdiniUtente.jsp" id="visualizzaOrdiniButton"class ="btn btn-primary"> Visualizza Ordini </a>
		
			<a href="<%=request.getContextPath()%>/LogoutServlet" class="btn btn-primary">Logout </a>
		
		</div>
		
		<br> 
		<br>
		
		<h2>Modifica informazioni personali</h2>
			
		<%
			
			UserBean user = (UserBean)session.getAttribute("user");
			if(user != null){
				
		
		%>
		
		<!--  va creata uan servlet per la modifica account -->
		
		<form action="/account/update" method="POST">  
			<label for="name">Nome</label>
			<input type="text" id="name" name="name" value="<%=user.getNome()%>"><br>
			
			<label for="cognome">Cognome:</label>
			<input type="text" id="cognome" name="cognome" value="<%=user.getCognome()%>"><br>
			
			<label for="email">Email</label>
			<input type="email" id="email" name="email" value="<%=user.getEmail()%>"><br>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" value=""><br>
			
			<label for="address">Cognome:</label>
			<input type="text" id="address" name="address" value="<%=user.getIndirizzo()%>"><br>
			
			<label for="address_sp">Indirizzo di spedizione</label>
			<textarea id="address_sp" name="address"><%=user.getIndirizzo_spedizione()%></textarea><br>
			
			
			<button type="submit">Aggiorna</button>
		</form>
		
		
		
		<% }else{ 
			String path = request.getContextPath();
			response.sendRedirect(path + "/common/login.jsp");
				return ;
		}
			%>
			
	</section>
		
		
	</main>
	
	<jsp:include page="footer.jsp" />
	
</body>
</html>