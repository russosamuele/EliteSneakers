<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.UserDAO, model.UserBean, java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<title>Elite Sneakers - Rimuovi Utente</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	<%@ include file="../common/header.jsp"%>
	
	
	<%
		UserDAO dao = new UserDAO();
		Collection<UserBean> utenti = dao.doRetrieveAll("nome");
		
		String message = (String)request.getAttribute("result");
		if(message == null)
			message="";
			
	%>
	
	
	<main>
		<h2>Rimuovi Utente</h2>
		
		<p  style="color:green "> <%=message %> </p>
		
		<form method="post" action="<%=request.getContextPath()%>/RimuoviUtenteServlet">
			<label for="utente">Seleziona l'utente da rimuovere:</label>
			<select id="utente" name="utente">
				<%for(UserBean utente : utenti){ %>
					<option value="<%=utente.getEmail()%>"><%=utente.getNome()%> <%=utente.getCognome()%> (<%=utente.getEmail()%>)</option>
				<%} %>
			</select>
			<br>
			<input type="submit" value="Rimuovi Utente">
		</form>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
