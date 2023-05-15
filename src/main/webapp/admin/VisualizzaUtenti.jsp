<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.UserDAO, model.UserBean, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Elite Sneakers - Tutti gli Utenti</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	
	<%@ include file="../common/header.jsp"%>
	
	
	<%
		UserDAO dao = new UserDAO();
		Collection<UserBean> utenti = dao.doRetrieveAll("nome");
			
	%>
	
	<main>
	
		<h2>Tutti gli Utenti</h2>
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Email</th>
					<th>Admin</th>
				</tr>
			</thead>
			<tbody>
				<%for(UserBean utente : utenti){ %>
					<tr>
							<td><%=utente.getNome() %></td>
							<td><%=utente.getCognome() %></td>
							<td><%=utente.getEmail() %></td>
							<td><%=utente.isAdmin() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
    