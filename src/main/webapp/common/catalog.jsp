<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Elite Sneakers - Catalogo</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="content">
		<h1>Catalogo</h1>
		<table>
			<thead>
				<tr>
					<th>Immagine</th>
					<th>Nome</th>
					<th>Marca</th>
					<th>Prezzo</th>
				</tr>
			</thead>
			
			<tbody>
			
			<%-- 
			 
			  <% 
					List<Sneaker> sneakers = SneakerDao.getAllSneakers();
					for(Sneaker sneaker : sneakers) {
				%>
				<tr>
					<td><img src="<%=sneaker.getImageUrl()%>" alt="<%=sneaker.getName()%>" /></td>
					<td><%=sneaker.getName()%></td>
					<td><%=sneaker.getBrand()%></td>
					<td><%=sneaker.getPrice()%> €</td>
				</tr>
				<%
					}
				%> --%>
			</tbody>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
