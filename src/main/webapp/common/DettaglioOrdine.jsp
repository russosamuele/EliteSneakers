<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.ProductDAO, model.ProductBean,model.FinalOrder,model.DettaglioOrdineBean,model.UserBean,  java.util.*"
	pageEncoding="UTF-8"
	errorPage="errorPage.jsp"
	%>

<!DOCTYPE html>
<html lang="it">
<head>
<title>Elite Sneakers - Dettaglio Ordine</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


	<%@ include file="../common/header.jsp"%>

	<%
	
	FinalOrder ordine = (FinalOrder) request.getAttribute("ordine");
	
	int code = Integer.parseInt(request.getParameter("code"));
	request.setAttribute("code", code);
	
	
	if (ordine == null) {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetOrdineServlet");
		dispatcher.forward(request, response);
		return;
	}
	
	%>

	<body>
		<section class="visualizzaOrdiniUser">
		<p>Questi sono i dettagli dell'ordine <%=code%> effettuato in data <%=ordine.getOrdine().getDataOrdine()%> </p>
		<table class="table table-sm table-dark table-hover table-bordered">  
        <tr>
            <th scope="col">Nome Prodotto</th>
            <th scope="col">Quantita</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Visualizza</th>
        </tr>
        
        <%
        List <DettaglioOrdineBean> dettagli = ordine.getDettagli();
        List <String> nomiSneakers = ordine.getNomi();
        for(int i = 0; i< dettagli.size(); i++){ 
        %>
            <tr>
                <th scope="row"><%=nomiSneakers.get(i)%></th>
                <td><%=dettagli.get(i).getQuantita()%></td>
                <td><%=dettagli.get(i).getPrezzo()%></td>
                <td><a href="<%=request.getContextPath()%>/common/product.jsp?code=<%=dettagli.get(i).getCodiceProdotto()%>"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
 						 <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
 						 <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/></svg> </a>
 				</td> 
            </tr>
        <%} %> 
       
    </table> 
	  
	  </section>
	
	<%@ include file="../common/footer.jsp"%>


</body>

</html>
 