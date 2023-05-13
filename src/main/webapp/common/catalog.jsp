<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.ProductBean, model.ProductDAO, java.util.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Elite Sneakers - Catalogo</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

	<jsp:include page="header.jsp" />


		<%
		ProductDAO dao = new ProductDAO();
		Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");

		for (ProductBean sneaker : sneakers) {
		%>

		<div class="card" style="width: 18rem;">
			<img class="card-img-top" src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=sneaker.getCode()%>" alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title"> <%=sneaker.getBrand()%> <%=sneaker.getModello()%></h5>
				<p class="card-text"><%=sneaker.getDescrizione() %></p>
				<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
			</div>
		</div>

		<%
		}
		%>


		
	<jsp:include page="footer.jsp" />
</body>
</html>
