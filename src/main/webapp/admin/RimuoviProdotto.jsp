<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductDAO, model.ProductBean, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Elite Sneakers - Rimuovi Prodotto</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	
	<%@ include file="../common/header.jsp"%>
	
	<main>
		<h2>Rimuovi Prodotto</h2>
		
		<%
			ProductDAO dao = new ProductDAO();
			Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");
		
			String message = (String)request.getAttribute("message");
			if(message == null)
				message="";
		
		%>
		
		<p  style="color:green "> <%=message %> </p>
		
		<form action="/EliteSneakersEcommerce/DeleteProdotto" method="post">
		
			<label for="code">Codice prodotto:</label>
			<select id="code" name="code" required>
				
				<%for(ProductBean p : sneakers){ %>
					<option value="<%=p.getCode()%>"> code:<%=p.getCode()%> - brand: <%=p.getBrand()%> - modello: <%=p.getModello()%> - taglia: <%=p.getTaglia()%></option>
				<%} %>
			</select>
			<br>
			<input type="submit" value="Rimuovi">
		</form>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
 