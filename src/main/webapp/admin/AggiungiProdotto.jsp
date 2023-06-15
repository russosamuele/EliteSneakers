<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<title>Elite Sneakers - Aggiungi Prodotto</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	
	<%@ include file="../common/header.jsp"%>
	
	
	<main>
		
		
		<section class="managementSection"> 
		
		<div id="managementDiv">
		
		<h2>Aggiungi Prodotto</h2>
		
		<form action="/EliteSneakersEcommerce/AddProduct" method="post" enctype="multipart/form-data">
			<label for="code">Code:</label>
			<input class="inputField" type="number" id="code" name="code" required>
			<br>
			<label for="brand">Brand:</label>
			<input class="inputField" type="text" id="brand" name="brand" required>
			<br>
			<label for="modello">Modello:</label>
			<input class="inputField" type="text" id="modello" name="modello" required>
			<br>
			<label for="descrizione">Descrizione:</label>
			<textarea class="inputField" id="descrizione" name="descrizione" required></textarea>
			<br>
			
			<label for="prezzo">Prezzo:</label>
			<input class="inputField" type="number" id="prezzo" name="prezzo" min="0" step="0.01" required>
			<br>
			
			<label for="immagine">Immagine:</label>
			<input class="inputField" type="file" id="photo" name="photo" value="" required>
			<br>
			<input type="submit" class="btn btn-primary" value="Aggiungi">
		</form>
		
		</div>
	
	</section>
		
	</main>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
