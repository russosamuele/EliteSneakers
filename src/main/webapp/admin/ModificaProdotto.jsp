<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductDAO, model.ProductBean,model.FinalProduct, java.util.*"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>Elite Sneakers - Modifica Prodotto</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	
	<%@ include file="../common/header.jsp"%>
	
	<%
		int id = 4;
		request.setAttribute("id", id);
		ArrayList<FinalProduct> listSneakers = (ArrayList<FinalProduct>) request.getAttribute("listSneakers");
		
		if (listSneakers == null){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetSneakersList");
			dispatcher.forward(request, response);	
			return;
		}
		String message = (String) request.getAttribute("ms");
		if (message == null)
			message = "";
	%>

		<p  style="color:green "> <%=message %> </p>
	
	
	
	<main>
		<h2>Modifica Prodotto</h2>
		
		<form action="<%=request.getContextPath()%>/ModificaProdottoServlet" method="post" enctype="multipart/form-data">
		
			<label for="code">Codice prodotto:</label>
			<select id="code" name="code" required>
				
				<%for(FinalProduct prod : listSneakers){ %>
				<option value="<%=prod.getProdotto().getCode()%>"> code:<%=prod.getProdotto().getCode()%> - brand: <%=prod.getProdotto().getBrand()%> - modello: <%=prod.getProdotto().getModello()%></option>
				<%} %>
			</select>
			<br>
			<label for="brand">Brand:</label>
			<input type="text" id="brand" name="brand" required>
			<br>
			<label for="modello">Modello:</label>
			<input type="text" id="modello" name="modello" required>
			<br>
			<label for="descrizione">Descrizione:</label>
			<textarea id="descrizione" name="descrizione" required></textarea>
			<br>
			<label for="prezzo">Prezzo:</label>
			<input type="number" id="prezzo" name="prezzo" min="0" step="0.01" required>
			<br>
			<label for="immagine">Immagine:</label>
			<input type="file" id="photo" name="photo" value="" required>
			<br>
			<input type="submit" value="Aggiorna">
		</form>
		
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
	
</body>
</html>
 