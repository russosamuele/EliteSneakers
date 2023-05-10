<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Gestione - Elite Sneakers</title>
</head>
<body>
	
	<%@ include file="../common/header.jsp"%>

	<main>
		<h1>Gestione prodotti</h1>
		
		<h2>Aggiungi prodotto</h2>
		<form action="/admin/addProduct" method="POST">
			<label for="name">Nome</label> <input type="text" id="name"
				name="name"><br>
			<br> <label for="brand">Brand</label> <input type="text"
				id="brand" name="brand"><br>
			<br> <label for="price">Prezzo</label> <input type="number"
				id="price" name="price"><br>
			<br> <label for="image">Immagine</label> <input type="text"
				id="image" name="image"><br>
			<br>
			<button type="submit">Aggiungi</button>
		</form>
		
		<h2>Modifica prodotto</h2>
		<form action="/admin/updateProduct" method="POST">
			<label for="productId">ID prodotto</label> <input type="number"
				id="productId" name="productId"><br>
			<br> <label for="name">Nome</label> <input type="text" id="name"
				name="name"><br>
			<br> <label for="brand">Brand</label> <input type="text"
				id="brand" name="brand"><br>
			<br> <label for="price">Prezzo</label> <input type="number"
				id="price" name="price"><br>
			<br> <label for="image">Immagine</label> <input type="text"
				id="image" name="image"><br>
			<br>
			<button type="submit">Modifica</button>
		</form>
		
		
		<h2>Elimina prodotto</h2>
		<form action="/admin/deleteProduct" method="POST">
			<label for="productId">ID prodotto</label> <input type="number"
				id="productId" name="productId"><br>
			<br>
			<button type="submit">Elimina</button>
		</form>
		
		
		<h2>Prodotti presenti</h2>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Brand</th>
					<th>Prezzo</th>
					<th>Immagine</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Air Jordan 1</td>
					<td>Nike</td>
					<td>150.00€</td>
					<td><img src="/images/airjordan1.jpg" alt="Air Jordan 1"></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Chuck Taylor All Star</td>
					<td>Converse</td>
					<td>71.00€</td>
					<td><img src="/images/chucktaylor.jpg"
						alt="Chuck Taylor All Star"></td>
				</tr>
				<tr>
					<td>3</td>
					<td>Old Skool</td>
					<td>Vans</td>
					<td>80.00€</td>
					<td><img src="/images/oldskool.jpg" alt="Old Skool"></td>
				</tr>
			</tbody>
		</table>
	</main>
	
	<%@ include file="../common/footer.jsp"%>

</body>
</html>
