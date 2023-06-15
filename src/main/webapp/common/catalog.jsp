<%@page import="model.FinalProduct"
	language="java" 
	contentType="text/html; charset=UTF-8"
	import="model.ProductBean, model.ProductDAO, java.util.*"
	pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Elite Sneakers - Catalogo</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">


</head>
<body>

	<jsp:include page="header.jsp" />
	
	<%
	int id = 1;
	request.setAttribute("id", id);
	ArrayList<FinalProduct> listSneakers = (ArrayList<FinalProduct>) request.getAttribute("listSneakers");
	
	if (listSneakers == null){
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetSneakersList");
		dispatcher.forward(request, response);	
		return;
	}
	
	
	int totSneakers = listSneakers.size();
	int s = 0;
	
	%>
	
	<section class="catalog">
	
	<div id=filtri>
	
		<form action="/EliteSneakersEcommerce/CatalogoFilter" name="FormFiltri" method="post">
	
			<label> Brand: </label><select name= "brand" id="brand">
				<option value="-"></option>
				<option value="Nike">Nike</option>
				<option value="Jordan">Jordan</option>
				<option value="Adidas">Adidas</option>
				<option value="New Balance">New Balance</option>
				<option value="Yeezy">Yeezy</option>
				<option value="Versace">Versace</option>
				<option value="Gucci">Gucci</option>
			</select>
			
			<label> Prezzo minimo: </label><input type="number" name="prezzoMin" id="prezzoMin" value="100" step="10">
			<label> Prezzo massimo: </label><input type="number" name="prezzoMax" id="prezzoMax" value="1200" step="10">
			
			<input type="submit" value="filtra" class="btn btn-primary">
			
		</form>
	
	
	</div>
	
	<div class="album py-5">
	
        <div class="container">

          <div class="row">
          
          <% for(int i = 0; i<listSneakers.size(); i++){ 
        	  ProductBean prodotto = listSneakers.get(i).getProdotto();
          %>
             <div class="col-md-4">
              <div class="card mb-4 box-shadow">
                <img class="card-img-top" src="<%=request.getContextPath()%>/GetPhotoServlet?code=<%=prodotto.getCode()%>" alt="Card image cap">
                <div class="card-body">
                  <p class="card-text"><%=prodotto.getBrand()%> <%=prodotto.getModello()%></p>
                  <div class="d-flex justify-content-between align-items-center">
                  	<a href="<%=request.getContextPath()%>/common/product.jsp?code=<%=prodotto.getCode()%>" class="btn btn-info" role="button"><%=prodotto.getPrice()%>&euro;</a>
                  </div>
                </div>
              </div>
            </div>
            
            <%}%>
            
           </div>
        </div>
     </div>
         
	</section>
	
	<jsp:include page="footer.jsp" />
</body>
</html>
