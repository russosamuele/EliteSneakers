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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
.card {
	margin-bottom: 15px;
	width: 270px;
	height: 460px;
	margin-top: 10px;
}
</style>

</head>
<body>

	<jsp:include page="header.jsp" />


	<%
	ProductDAO dao = new ProductDAO();
	Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");
	ArrayList<ProductBean> listSneakers = new ArrayList<>(sneakers);

	int totSneakers = listSneakers.size();
	int s = 0;
	
	%>

	<div class="container">
		<%
		for (int row = 0; row < totSneakers / 4; row++) {
		%>
		<div class="row">
			<%
			for (int i = 0; i < 4; i++) {
			%>
			<div class="col-md-3">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=listSneakers.get(s).getCode()%>"
						class="card-img-top" alt="<%=listSneakers.get(s).getModello()%>">
					<div class="card-body">
						<h5 class="card-title">
							<%=listSneakers.get(s).getBrand()%>
							<%=listSneakers.get(s).getModello()%></h5>
						<p class="card-text"><%=listSneakers.get(s).getDescrizione()%></p>
					</div>
					<a href="#" class="btn btn-primary">Visualizza prodotto</a>
				</div>
			</div>
			<%
			s++;
			}
			%>
		</div>
		<%
		}
		while(s < totSneakers){
		%>
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=listSneakers.get(s).getCode()%>"
						class="card-img-top" alt="<%=listSneakers.get(s).getModello()%>">
					<div class="card-body">
						<h5 class="card-title">
							<%=listSneakers.get(s).getBrand()%>
							<%=listSneakers.get(s).getModello()%></h5>
						<p class="card-text"><%=listSneakers.get(s).getDescrizione()%></p>
					</div>
					<a href="#" class="btn btn-primary">Visualizza prodotto</a>
				</div>
			</div>
			<%
			s++;
			%>
		</div>
		<%}%>
		
		
	</div>
	


	<jsp:include page="footer.jsp" />
</body>
</html>
