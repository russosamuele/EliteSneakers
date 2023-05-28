<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.Cart, model.CartItem, model.ProductBean"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Elite Sneakers - Carrello</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		crossorigin="anonymous">
</head>
<body>

	<%@ include file="header.jsp" %>
	
	
	<% 
	
	Cart carrello = (Cart) session.getAttribute("carrello");
	String error = (String)request.getAttribute("error");
	if(error == null)
		error = "";
	if(carrello == null){
		response.sendRedirect("/EliteSneakersEcommerce/CartControl?redirect=carrello");
		return;
	}
	
	%>
	
	<p style=color:red> <%=error%></p>
	
	<div class="container">
		<h1>Il tuo carrello</h1>
		<table>
			<thead>
				<tr>
					<th>Prodotto</th>
					<th> Taglia </th>
					<th>Prezzo</th>
					<th>Quantità</th>
					<th>Totale</th>
					<th>    </th>
				</tr>
			</thead>
			<tbody>
				<% for(CartItem pb : carrello.getProducts()) { %>
				<tr>
					<td><%=pb.getProductBean().getBrand() + " ," + pb.getProductBean().getModello()%></td>
					<td> <%=pb.getTaglia()%> </td>
					<td><%=pb.getProductBean().getPrice()%> &euro;</td>
					<td><%=pb.getQuantita() %></td>
					<td><%=pb.getProductBean().getPrice() * pb.getQuantita()%>&euro;</td>
					<td> <a href="/EliteSneakersEcommerce/CartControl?action=delete&code=<%=pb.getProductBean().getCode()%>&sizeSelect=<%=pb.getTaglia()%>&redirect=carrello">
								<svg width="16" height="18" viewBox="0 0 16 18" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path d="M3 18C2.45 18 1.979 17.804 1.587 17.412C1.195 17.02 0.999333 16.5493 1 16V3H0V1H5V0H11V1H16V3H15V16C15 16.55 14.804 17.021 14.412 17.413C14.02 17.805 13.5493 18.0007 13 18H3ZM13 3H3V16H13V3ZM5 14H7V5H5V14ZM9 14H11V5H9V14Z" fill="black"/>
								</svg>
							</a>
					</td>
				</tr>
				
				<%} %>
				
			</tbody>
		</table>

		<div class="cart-total">
			<h3>Totale</h3>
			<p>Sotto-totale: <%=carrello.getTotale()%>&euro;</p>
			<p>Spedizione: 0.0 &euro;</p>
			<p>Totale: <%=carrello.getTotale()%></p>
		</div>

		<div class="checkout-button">
			<a href="checkout.jsp" class="btn btn-primary">Procedi al pagamento</a>
		</div>
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>
