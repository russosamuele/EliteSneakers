<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.UserDAO, model.UserBean, model.OrdineBean, java.util.*"
%>


 <!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Account - Elite Sneakers</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>

<body>

	<jsp:include page="header.jsp"/>
	
	

	<main>
	
	<section class="accountSection"> 
		
		
		
	<div id="accountDiv">
	
		<h1 id="accountLabel">Il mio account</h1>
	
		<div id="accountList">
			<a href="<%=request.getContextPath()%>/common/VisualizzaOrdiniUtente.jsp" id="visualizzaOrdiniButton"class ="btn btn-primary"> Visualizza Ordini </a>
		
			<a href="<%=request.getContextPath()%>/LogoutServlet" class="btn btn-primary" id="logoutButton">Logout </a>
		
		</div>
		
		<br> 
		<br>
		
		<h2>Modifica informazioni personali</h2>
			
		<%
		
			String error = (String)request.getAttribute("errorPSW");
		
			if(error==null)
				error="";
		
			
			UserBean user = (UserBean)session.getAttribute("user");
			if(user != null){
				
		
		%>
		
		<!-- onsubmit="event.preventDefault();checkFormUpdate(this)" -->
		
		<form action="<%=request.getContextPath()%>/ModificaAccount" method="post" name="updateAccount" id="updateAccount" >  
					
			
			<label for="address">Indirizzo:</label>
			<input class="inputField" type="text" id="address" name="address" value="<%=user.getIndirizzo()%>"><br>
			
			<label for="address_sp">Indirizzo di spedizione</label>
			<textarea class="inputField" id="address_sp" name="address_sp"><%=user.getIndirizzo_spedizione()%></textarea><br>
			
			<label for="password">Vecchia Password:</label>
			<input class="inputField" type="password" id="passwordVecchia" name="passwordVecchia" required><span id="errorpswd" style="color:red"> <%=error%></span><br>
			
			<label for="nuovaPassword">Nuova Password:</label>
			<input class="inputField" type="password" id="nuovaPassword" name="nuovaPassword" onChange="return checkPswd()"> <span id="matchError"></span><br>
			
			
			<input class="btn btn-primary" type="submit" value="Aggiorna">
			
		</form>
		
		
		
		<% }else{ 
			String path = request.getContextPath();
			response.sendRedirect(path + "/common/login.jsp");
				return ;
		}
			%>
		
	</div>	
	</section>
	
	
		
		
	</main>
	
	<jsp:include page="footer.jsp" />
	
</body>
</html>