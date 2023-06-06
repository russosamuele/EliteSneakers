<%@ 
	page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   errorPage="errorPage.jsp" 
%>


<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Carrello</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<section class="SectionCheckout">
	
    <h1>Checkout Carrello</h1>

    <form action="<%=request.getContextPath()%>/CheckoutServlet" method="post" id="checkoutForm" onsubmit="event.preventDefault();checkCheckout(this)">
        <h3>Informazioni di spedizione</h3>
        
       
        <label for="address">Indirizzo di spedizione:</label>
        <input type="text" id="address" name="address"><br>

        <h3>Informazioni della carta</h3>
        
        <label for="cardNumber">Numero di carta:</label>
        <input type="text" id="cardNumber" name="cardNumber" placeholder="####-####-####-####"required onBlur="return validateNumCarta()"> <span id="cardNumberError"></span><br>

        <label for="expirationDate">Data di scadenza:</label>
        <input type="date" id="expirationDate" name="expirationDate" required onBlur="return validateScadenzaCarta()"> <span id="expiryError"></span><br>

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv" placeholder="###" required onBlur="return validateCVV()"> <span id="CVVError"></span><br>

        <input type="submit" value="Conferma Ordine">
        
    </form>
    
    </section>
    
    <jsp:include page="footer.jsp"/>
    
</body>
</html>