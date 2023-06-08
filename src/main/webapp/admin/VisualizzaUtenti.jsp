<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.UserDAO, model.UserBean, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>Elite Sneakers - Tutti gli Utenti</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>

<body>
	
	<%@ include file="../common/header.jsp"%>
	
	
	<%
	int id = 1;
	request.setAttribute("id", id);
	List<UserBean> listUsers = (List<UserBean>) request.getAttribute("listUsers");
	
	if (listUsers == null){
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetUsersList");
		dispatcher.forward(request, response);	
		return;
	}
			
	%>
	
	<main>
	
	<section class="managementSection"> 
	
		<h2>Tutti gli Utenti</h2>
		<table class="table table-sm table-dark table-hover table-bordered">  
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Cognome</th>
					<th scope="col">Email</th>
					<th scope="col">Admin</th>
				</tr>
			</thead>
			<tbody>
				<%for(UserBean utente : listUsers){ %>
					<tr>
							<th scope="row"><%=utente.getNome() %></th>
							<td><%=utente.getCognome() %></td>
							<td><%=utente.getEmail() %></td>
							<td><%=utente.isAdmin() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
		
		</section>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
    