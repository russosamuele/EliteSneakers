<%@page import="model.FinalProduct"%>
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
	int id = 1;
	request.setAttribute("id", id);
	ArrayList<FinalProduct> listSneakers = (ArrayList<FinalProduct>) request.getAttribute("listSneakers");
	
	if (listSneakers == null){
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetSneakersList");
		dispatcher.forward(request, response);	
		return;
	}
	
	
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
				ProductBean prodotto = listSneakers.get(s).getProdotto();
			%>
			<div class="col-md-3">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=prodotto.getCode()%>"
						class="card-img-top" alt="<%=prodotto.getModello()%>">
					<div class="card-body">
						<h5 class="card-title">
							<%=prodotto.getBrand()%>
							<%=prodotto.getModello()%></h5>
						<p class="card-text"><%=prodotto.getDescrizione()%></p>
					</div>
					<a href="<%=request.getContextPath()%>/common/product.jsp?code=<%=prodotto.getCode()%>" class="btn btn-primary">Visualizza prodotto</a>
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
			ProductBean prodotto = listSneakers.get(s).getProdotto();

		%>
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=prodotto.getCode()%>"
						class="card-img-top" alt="<%=prodotto.getModello()%>">
					<div class="card-body">
						<h5 class="card-title">
							<%=prodotto.getBrand()%>
							<%=prodotto.getModello()%></h5>
						<p class="card-text"><%=prodotto.getDescrizione()%></p>
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
