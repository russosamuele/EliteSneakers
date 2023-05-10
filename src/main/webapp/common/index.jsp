<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Elite Sneakers - Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<%@ include file="header.jsp" %>
	
	<main>
	
		<section class="hero">
			<h2>Find Your Perfect Pair of Sneakers</h2>
			<form method="get" action="catalog.jsp">
				<input type="text" name="search" placeholder="Search for sneakers...">
				<button type="submit">Search</button>
			</form>
		</section>
		
		<section class="featured-products">
			<h2>Featured Products</h2>
			
			<div class="product-list">
			
				<div class="product">
					<img src="images/product1.jpg" alt="Product 1">
					<h3>Product 1</h3>
					<p>$99.99</p>
					<button>Add to Cart</button>
				</div>
				<div class="product">
					<img src="images/product2.jpg" alt="Product 2">
					<h3>Product 2</h3>
					<p>$79.99</p>
					<button>Add to Cart</button>
				</div>
				<div class="product">
					<img src="images/product3.jpg" alt="Product 3">
					<h3>Product 3</h3>
					<p>$149.99</p>
					<button>Add to Cart</button>
				</div>
				
			</div>
		</section>
	</main>
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>
