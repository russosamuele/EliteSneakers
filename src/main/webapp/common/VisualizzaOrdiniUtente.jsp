<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "model.OrdineBean, java.util.*"
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Ordini Utente</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<jsp:include page="header.jsp"/>

	<%
	    
    	List<OrdineBean> ordini = (List<OrdineBean>)request.getAttribute("listOrdini");
					
		if(ordini == null){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/VisualizzaOrdiniUtente");
			dispatcher.forward(request, response);	
			return;
		}
      	
	%>


<body>

	<section class="visualizzaOrdiniUser">

	<h2>Cronologia ordini</h2>
				
		<table>  
			<thead>
				<tr>
					<th>Numero ordine</th>
					<th>Data ordine</th>
					<th>Dettagli</th>
				</tr>
			</thead>
			<tbody>
			<%for(OrdineBean ordine : ordini){%>
				<tr>
					<td><%=ordine.getNumeroOrd()%></td>
					<td><%=ordine.getDataOrdine()%></td>
					<td> </td>
				</tr>
			<%} %>
			</tbody>
		</table>
	
	 </section>
		
		<jsp:include page="footer.jsp"/>

</body>
</html>