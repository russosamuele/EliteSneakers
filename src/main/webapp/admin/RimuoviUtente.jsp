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
		int id = 2;
		request.setAttribute("id", id);
		List<UserBean> listUsers = (List<UserBean>) request.getAttribute("listUsers");
		
		if (listUsers == null){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetUsersList");
			dispatcher.forward(request, response);	
			return;
		}
		
		String message = (String)request.getAttribute("result");
		if(message == null)
			message="";
			
	%>
	
	
	<main>
	
	<section class="managementSection"> 
		<h2>Rimuovi Utente</h2>
		
		<p  style="color:green "> <%=message %> </p>
		
		<form method="post" action="<%=request.getContextPath()%>/RimuoviUtenteServlet">
			<label for="utente">Seleziona l'utente da rimuovere:</label>
			<select id="utente" name="utente">
				<%for(UserBean utente : listUsers){ %>
					<option value="<%=utente.getEmail()%>"><%=utente.getNome()%> <%=utente.getCognome()%> (<%=utente.getEmail()%>)</option>
				<%} %>
			</select>
			<br>
			<input type="submit" value="Rimuovi Utente">
		</form>
		
		</section>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
