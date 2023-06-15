<%@ page language="java" 
	contentType="text/html; charset=UTF-8" 
	import="java.util.*, model.UserBean, model.OrdineBean"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordini - Area Admin</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>


	<%
		int id = 3;
		request.setAttribute("id", id);
		List<UserBean> listUsers = (List<UserBean>) request.getAttribute("listUsers");
		
		if (listUsers == null){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/GetUsersList");
			dispatcher.forward(request, response);	
			return;
		}
	
	%>

	<jsp:include page="../common/header.jsp"/>
	
	<section class="managementSection"> 
	
	<div>
	
    <h1>Ordini - Area Admin</h1>

    <form action="<%=request.getContextPath()%>/VisualizzaOrdiniServlet" method="post">
    
        <label for="startDate">Data di inizio:</label>
        <input class="inputField" type="date" id="startDate" name="startDate"><br>

        <label for="endDate">Data di fine:</label>
        <input class="inputField" type="date" id="endDate" name="endDate"><br>

        <label for="username">Nome utente:</label>
        
        <select class="inputField" id="utente" name="utente" required>
        		<option value="tutti"> Tutti gli utenti </option>
				<%for(UserBean utente : listUsers){ %>
					<option value="<%=utente.getEmail()%>"><%=utente.getNome()%> <%=utente.getCognome()%> (<%=utente.getEmail()%>)</option>
				<%} %>
		</select>

        <input type="submit" class="btn btn-primary" value="Filtra">
    </form>
    
    </div>
    <br>
    <br>
    <%
    
    	List<OrdineBean> ordini = (List<OrdineBean>)request.getAttribute("listOrdine");
    
    	if(ordini != null){
	%>
    
    
    <div id="tableOrdini">
	<table class="table table-sm table-dark table-hover table-bordered" >  
        <tr>
            <th scope="col">Numero Ordine</th>
            <th scope="col">Utente</th>
            <th scope="col">Data</th>
            <th scope="col">Visualizza</th>
       
        </tr>
        
        <%for(OrdineBean ordine: ordini){ %>
            <tr>
                <th scope="row"><%=ordine.getNumeroOrd()%></th>
                <td><%=ordine.getEmail()%></td>
                <td><%=ordine.getDataOrdine()%></td>
                <td><a href="<%=request.getContextPath()%>/common/DettaglioOrdine.jsp?code=<%=ordine.getNumeroOrd()%>"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
							    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
  								<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/></svg> </a>
  					</td> 
            </tr>
        <%} }%> 
       
    </table>  
    </div>
    
    </section>
    
   <jsp:include page="../common/footer.jsp"/>
   
</body>
</html>
