<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
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

	<%@ include file="header.jsp" %>

    <!-- home-section -->
    <section class="home">
        <div class="text">
            <h4>New Fashion</h4>
            <h1>Spring Summer <br> Collection</h1>
            <p>It Is A Long Established Fact That A Reader Will Be Distracted By The Readable Content</p>

            <a id href="<%=request.getContextPath()%>/common/catalog.jsp" class="btn">Shop Now</a>
        </div>
    </section>


    <!-- link to js -->
    <script src="<%=request.getContextPath()%>/scripts/java.js"></script>
    
    <%@ include file="footer.jsp" %>
    
</body>
</html>