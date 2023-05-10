<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Elite Sneakers - Carrello</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<%@ include file="header.jsp" %>

	<div class="container">
		<h1>Il tuo carrello</h1>
		<table>
			<thead>
				<tr>
					<th>Prodotto</th>
					<th>Prezzo</th>
					<th>Quantità</th>
					<th>Totale</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Nike Air Max 90</td>
					<td>120€</td>
					<td>1</td>
					<td>120€</td>
				</tr>
				<tr>
					<td>Adidas Stan Smith</td>
					<td>80€</td>
					<td>2</td>
					<td>160€</td>
				</tr>
				<tr>
					<td>New Balance 574</td>
					<td>100€</td>
					<td>1</td>
					<td>100€</td>
				</tr>
			</tbody>
		</table>

		<div class="cart-total">
			<h3>Totale</h3>
			<p>Sotto-totale: 380€</p>
			<p>Spedizione: 10€</p>
			<p>Totale: 390€</p>
		</div>

		<div class="checkout-button">
			<button type="button">Procedi al pagamento</button>
		</div>
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>
