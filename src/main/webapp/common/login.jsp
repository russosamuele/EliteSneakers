<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Elite Sneakers - Login</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	
	<%@ include file="header.jsp" %>
	<main>
	
	<% 
		String error = (String)request.getAttribute("error");
		if(error == null)
			error="";
	
	%>
	
		<section class="loginSection">	
	
		<p  style="color:red "> <%=error %> </p>
		
		
		<h2>Login</h2>
		<form method="post" action="/EliteSneakersEcommerce/LoginServlet">
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
			<button type="submit">Login</button>
		</form>
		<p>Don't have an account? <a href="<%=request.getContextPath()%>/common/signup.jsp">Sign up here</a>.</p>
		
		</section>
	</main>
	
	
	<%@ include file="footer.jsp" %>
</body>
</html>
