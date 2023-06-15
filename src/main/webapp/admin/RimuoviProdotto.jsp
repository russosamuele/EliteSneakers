<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductBean,model.FinalProduct, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>Elite Sneakers - Rimuovi Prodotto</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	
	<%@ include file="../common/header.jsp"%>
	
	<main>
	
	<section class="managementSection"> 
	
	<div id="managementDiv">
		<h2>Rimuovi Prodotto</h2>
		
		<%
		int id = 2;
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
		
		
		<p  style="color:green "> <%=message %> </p>
		
		<form action="/EliteSneakersEcommerce/DeleteProdotto" method="post">
		
			<label for="code">Codice prodotto:</label>
			<select class="inputField" id="code" name="code" required>
				
				<%for(FinalProduct prod : listSneakers){ %>
					
					<option value="<%=prod.getProdotto().getCode()%>"> code:<%=prod.getProdotto().getCode()%> - brand: <%=prod.getProdotto().getBrand()%> - modello: <%=prod.getProdotto().getModello()%></option>
				<%} %>
			</select>
			<br>
			<input type="submit" class="btn btn-primary" value="Rimuovi">
		</form>
		
		</div>
		
		</section>
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
 