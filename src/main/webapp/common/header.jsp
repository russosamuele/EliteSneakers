<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>

	<header>
		<h1>Elite Sneakers</h1>
		<nav>
			<ul>
			
				<%
					boolean isLogged=false;
					boolean isAdmin=false;
					
		
					String value1= (String)session.getAttribute("isLogged");
					
					if(value1 != null && value1.equals("true"))
						isLogged=true;
					else
						isLogged=false;
					
					String value2= (String)session.getAttribute("isAdmin");
					if(value2 != null && value2.equals("true"))
						isAdmin=true ;
					else
						isAdmin=false;
				%>
				
				
				
				<%String path = request.getContextPath(); %>
				
				
				
							
				<%if(isLogged && !isAdmin){ %>      
					<li><a href= "<%=path%>/common/index.jsp">Home</a></li>
					<li><a href="<%=path%>/common/catalog.jsp">Catalog</a></li>
					<li><a href="<%=path%>/common/cart.jsp">Cart</a></li>
					<li><a href="<%=path%>/common/account.jsp">Account</a></li>
				<%}else if (isLogged && isAdmin) {%>
					<li><a href= "<%=path%>/common/index.jsp">Home</a></li>
					<li><a href="<%=path%>/common/catalog.jsp">Catalog</a></li>
					<li><a href="<%=path%>/common/cart.jsp">Cart</a></li>
					<li><a href="<%=path%>/common/account.jsp">Account</a></li>
					<li><a href="<%=path%>/admin/gestione.jsp">Gestione</a></li>
				<% }else if(!isLogged){ %>
					<li><a href="<%=path%>/common/index.jsp">Home</a></li>
					<li><a href="<%=path%>/common/catalog.jsp">Catalog</a></li>
					<li><a href="<%=path%>/common/cart.jsp">Cart</a></li>
					<li><a href="<%=path%>/common/login.jsp">Login</a></li>
				<%}%>
				
				
			</ul>
		</nav>
	</header> 
	

</body>
</html>