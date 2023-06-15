<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductBean,model.FinalProduct, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>Elite Sneakers - Aggiungi Disponibilita</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	<%@ include file="../common/header.jsp"%>
	
	
	<main>
	
		<section class="managementSection">
		
		
		<%
		int id = 3;
		request.setAttribute("id", id);
		ArrayList<FinalProduct> listSneakers = (ArrayList<FinalProduct>) request.getAttribute("listSneakers");
		
		if (listSneakers == null){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetSneakersList");
			dispatcher.forward(request, response);	
			return;
		}
		String message = (String) request.getAttribute("message");
		if (message == null)
			message = "";
		%>
		
		
	<div id="managementDiv">
	
		<h2 id="InfoPg">Aggiungi Disponibilita</h2>
		
		<div>
		
		<form action="/EliteSneakersEcommerce/AddDisponibilita" method="post">
			<select class="inputField" id="code" name="code" required>
			<%for(FinalProduct prod : listSneakers){ %>	
				<option value="<%=prod.getProdotto().getCode()%>"> code:<%=prod.getProdotto().getCode()%> - brand: <%=prod.getProdotto().getBrand()%> - modello: <%=prod.getProdotto().getModello()%></option>
			<%} %>			
			</select>
			<br>
			<label for="taglia">Taglia:</label>
			<input class="inputField" type="number" id="taglia" name="taglia" min="25" max="52" step="1" required>
			<br>
			<label for="quantita">Quantita:</label>
			<input class="inputField" type="number" id="quantita" name="quantita" min="1" max="999" step="1" required>
			<br>
			<input type="submit" class="btn btn-primary" value="Aggiungi">
		</form>
		</div>
	
	</div>	
	
	</section>
		
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
