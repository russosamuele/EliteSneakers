<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.ProductDAO, model.ProductBean, java.util.*"
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
			ProductDAO dao = new ProductDAO();
			Collection<ProductBean> sneakers = dao.doRetrieveAll("brand");
	
			String message = (String)request.getAttribute("message");
			if(message == null)
				message="";
	
	%>
		<p  style="color:green "> <%=message %> </p>
	
	
	
	<main>
		<h2>Modifica Prodotto</h2>
		
		<form action="<%=request.getContextPath()%>/ModificaProdottoServlet" method="post" enctype="multipart/form-data">
		
			<label for="code">Codice prodotto:</label>
			<select id="code" name="code" required>
				
				<%for(ProductBean p : sneakers){ %>
					<option value="<%=p.getCode()%>"> code:<%=p.getCode()%> - brand: <%=p.getBrand()%> - modello: <%=p.getModello()%> - taglia: <%=p.getTaglia()%></option>
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
			<label for="quantita">Quantita:</label>
			<input type="text" id="quantita" name="quantita" required>
			<br>
			<label for="prezzo">Prezzo:</label>
			<input type="number" id="prezzo" name="prezzo" min="0" step="0.01" required>
			<br>
			<label for="taglia">Taglia:</label>
			<input type="number" id="taglia" name="taglia" min="25" required>
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
 