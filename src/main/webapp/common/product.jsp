<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.ProductDAO, model.ProductBean,model.FinalProduct, java.util.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<title>Elite Sneakers - Prodotto</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	.product-image {
		width: 300px;
		height: 300px;
	}
</style>
</head>
<body>


	<%@ include file="../common/header.jsp"%>

	<%
	int id = 1;
	request.setAttribute("id", id);
	FinalProduct sneaker = (FinalProduct) request.getAttribute("sneaker");
	
	int code = Integer.parseInt(request.getParameter("code"));
	request.setAttribute("code", code);
	
	
	if (sneaker == null) {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetSneaker");
		dispatcher.forward(request, response);
		return;
	}
	
	%>

	<body>
		<div class="container">
	    <div class="row">
	      <div class="col-md-6">
			<img src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=sneaker.getProdotto().getCode()%>"
				class="card-img-top" alt="<%=sneaker.getProdotto().getModello()%>">
	      </div>
	      <div class="col-md-6">
	        <h1><%=sneaker.getProdotto().getBrand()%> <%=sneaker.getProdotto().getModello()%></h1>
	        <p> <%=sneaker.getProdotto().getDescrizione()%></p>
	        
	      <form action="/EliteSneakersEcommerce/CartControl?action=add&code=<%=sneaker.getProdotto().getCode()%>&redirect=catalogo" method="post">
          <div class="form-group">
            <label for="sizeSelect">Taglia:</label>
            <select class="form-control" id="sizeSelect" name="sizeSelect">
            	<% 
            	int j;
            	for (int i = 36; i < 46; i++) {
            	    boolean tagliaDisponibile = false; // Aggiungi una variabile booleana per verificare se la taglia è disponibile
            	    for (j = 0; j < sneaker.getDisponibilitaTaglie().size(); j++) {
            	        if (sneaker.getDisponibilitaTaglie().get(j).getTaglia() == i &&
            	                sneaker.getDisponibilitaTaglie().get(j).getQuantita() > 0) {
            	            tagliaDisponibile = true; // Imposta la variabile a true se la taglia è disponibile
            	            break;
            	        }
            	    }
            	    if (tagliaDisponibile) { // Controlla se la taglia è disponibile
            	        %>
            	        <option value="<%=sneaker.getDisponibilitaTaglie().get(j).getTaglia()%>"> <%=sneaker.getDisponibilitaTaglie().get(j).getTaglia()%></option>
            	        <%
            	    } else { // Se la taglia non è disponibile, disabilita l'opzione
            	        %>
            	        <option	 disabled> <%=i %>(SOLD OUT)</option>
            	        <%
            	    }
            	}
 			%>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">Aggiungi al carrello</button>
        </form>
	      </div>
	    </div>
	  </div>
	  <script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</body>
	
	
	<%@ include file="../common/footer.jsp"%>


</body>

</html>
 