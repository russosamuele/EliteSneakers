<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductBean, model.ProductDAO, java.util.*" 
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
		
		<% 
		ProductDAO dao = new ProductDAO();
		Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");
					
		for(ProductBean sneaker : sneakers) {
		%>
			
		<div class="product">
					<img src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=sneaker.getCode()%>" alt="<%=sneaker.getBrand()%>" >
					<h3>Product 1</h3>
					<p>$99.99</p>
					<button>Add to Cart</button>
				</div>
				
		<%
					}
				%>
		
		
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
			
			
			 
			<%--    <% 
			  		ProductDAO dao = new ProductDAO();
					Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");
					
					for(ProductBean sneaker : sneakers) {
				
				<tr>
					<td><img src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=sneaker.getCode()%>" alt="<%=sneaker.getBrand()%>" /></td>
					<td> <%=sneaker.getBrand()%> </td>
					<td><%=sneaker.getModello()%></td>
					<td><%=sneaker.getPrice()%> €</td>
				</tr>
				<%
					}
				%> 
				
					--%>
				
			</tbody>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
