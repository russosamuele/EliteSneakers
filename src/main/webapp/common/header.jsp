<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="errorPage.jsp"
%>
<!DOCTYPE html>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>EliteSneakers</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styleIndex.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    
 	<link rel="preconnect" href="https://fonts.googleapis.com">
 	
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Jost:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
</head>

<body>

<%
				boolean isLogged = false;
				boolean isAdmin = false;

				String value1 = (String) session.getAttribute("isLogged");

				if (value1 != null && value1.equals("true"))
					isLogged = true;
				else
					isLogged = false;

				if (session.getAttribute("isAdmin") != null && session.getAttribute("isAdmin").equals("true"))
					isAdmin = true;
				else
					isAdmin = false;
				%>



				<%
				String path = request.getContextPath();
				%>

	 <header>
	 
        <div class="bx bx-menu" id="menu-icon"></div>
        <a href="<%=path%>/common/index.jsp" class="logo"><img src="<%=request.getContextPath()%>/images/logo.png" alt=""></a>

        <ul class="navbar">
            <li><a href="<%=path%>/common/index.jsp">Home</a></li>
            <li><a href="<%=path%>/common/catalog.jsp">Shop</a></li>
        </ul>
		
			<%
			if (isLogged && !isAdmin) {
			%>
        <div class="nav-icon">
            <a href="<%=path%>/common/account.jsp"><i class='bx bx-user-circle' ></i></a>
            <a href="<%=path%>/common/cart.jsp"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="whitesmoke" class="bi bi-cart3" viewBox="0 0 16 16">
  						<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
						</svg>
			</a>
        </div>
        	<%
			} else if (isLogged && isAdmin) {
			%>
		<div class="nav-icon">
			<a href="<%=path%>/admin/management.jsp"> <svg xmlns="http://www.w3.org/2000/svg" width="30" height="25" fill="whiteSmoke" class="bi bi-person-gear" viewBox="0 0 16 16">
  					<path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Zm3.63-4.54c.18-.613 1.048-.613 1.229 0l.043.148a.64.64 0 0 0 .921.382l.136-.074c.561-.306 1.175.308.87.869l-.075.136a.64.64 0 0 0 .382.92l.149.045c.612.18.612 1.048 0 1.229l-.15.043a.64.64 0 0 0-.38.921l.074.136c.305.561-.309 1.175-.87.87l-.136-.075a.64.64 0 0 0-.92.382l-.045.149c-.18.612-1.048.612-1.229 0l-.043-.15a.64.64 0 0 0-.921-.38l-.136.074c-.561.305-1.175-.309-.87-.87l.075-.136a.64.64 0 0 0-.382-.92l-.148-.045c-.613-.18-.613-1.048 0-1.229l.148-.043a.64.64 0 0 0 .382-.921l-.074-.136c-.306-.561.308-1.175.869-.87l.136.075a.64.64 0 0 0 .92-.382l.045-.148ZM14 12.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0Z"/>
					</svg> 
			</a>
            <a href="<%=path%>/common/account.jsp"><i class='bx bx-user-circle' ></i></a>
            
            <a href="<%=path%>/common/cart.jsp"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="whiteSmoke" class="bi bi-cart3" viewBox="0 0 16 16">
  						<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
					</svg> 
			</a>
			
        </div>
        
        <%
		} else if (!isLogged) {
		%>
		
		 <div class="nav-icon">
            <a href="<%=path%>/common/login.jsp"><i class='bx bx-user-circle' ></i></a>
            <a href="<%=path%>/common/cart.jsp"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="whiteSmoke" class="bi bi-cart3" viewBox="0 0 16 16">
  						<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
						</svg>
			</a>
        </div>
        <%} %>
			
			
        	
    </header>
    
 <script src="../scripts/java.js" defer></script>

</body>
</html>