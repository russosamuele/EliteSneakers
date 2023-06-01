<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, model.UserBean"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Ordini - Area Admin</title>
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
	
    <h1>Ordini - Area Admin</h1>

    <form action="<%=request.getContextPath()%>/VisualizzaOrdiniServlet" method="post">
    
        <label for="startDate">Data di inizio:</label>
        <input type="date" id="startDate" name="startDate"><br>

        <label for="endDate">Data di fine:</label>
        <input type="date" id="endDate" name="endDate"><br>

        <label for="username">Nome utente:</label>
        
        <select id="utente" name="utente">
				<%for(UserBean utente : listUsers){ %>
					<option value="<%=utente.getEmail()%>"><%=utente.getNome()%> <%=utente.getCognome()%> (<%=utente.getEmail()%>)</option>
				<%} %>
		</select>

        <input type="submit" value="Filtra">
    </form>
    
    

    <table>
        <tr>
            <th>Numero Ordine</th>
            <th>Data</th>
            <th>Utente</th>
            <th>Totale</th>
        </tr>
        
            <tr>
                <td>${order.orderNumber}</td>
                <td>${order.orderDate}</td>
                <td>${order.username}</td>
                <td>${order.total}</td>
            </tr>
       
    </table>
    
    </section>
    
   <jsp:include page="../common/footer.jsp"/>
   
</body>
</html>
