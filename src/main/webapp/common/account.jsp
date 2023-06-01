<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.UserDAO, model.UserBean"
 %>

 <!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Account - Elite Sneakers</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<jsp:include page="header.jsp"/>
	
	

	<main>
	
	<section class="accountSection"> 
		<h1>Il mio account</h1>
		<h2>Modifica informazioni personali</h2>
		<%=session.getAttribute("isAdmin")%>	
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
		
		<br>
		
		<h2>Cronologia ordini</h2>
		
		<table>   <!--  per generare la tabella va fatta una query sul DB, e va fatto un ciclo sul result SEt -->
			<thead>
				<tr>
					<th>ID ordine</th>
					<th>Data ordine</th>
					<th>Totale</th>
					<th>Stato</th>
					<th>Dettagli</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>#12345</td>
					<td>01/05/2023</td>
					<td>€99,99</td>
					<td>Consegnato</td>
				</tr>
				<tr>
					<td>#12344</td>
					<td>29/04/2023</td>
					<td>€49,99</td>
					<td>Spedito</td>
				</tr>
			</tbody>
		</table>
		
		
		
		<a href="<%=request.getContextPath()%>/LogoutServlet"> Logout </a>
		
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