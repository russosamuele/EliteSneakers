<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Elite Sneakers - Aggiungi Prodotto</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	<%@ include file="../common/header.jsp"%>
	
	<main>
		<h2>Aggiungi Prodotto</h2>
		
		<form action="/EliteSneakersEcommerce/AddProduct" method="post" enctype="multipart/form-data">
			<label for="code">Code:</label>
			<input type="number" id="code" name="code" required>
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
			<input type="submit" value="Aggiungi">
		</form>
		
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
