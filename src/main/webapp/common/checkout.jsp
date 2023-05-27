<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout Carrello</title>
</head>
<body>
    <h1>Checkout Carrello</h1>

    <form action="" method="post">
        <h3>Informazioni di spedizione</h3>
        
       
        <label for="address">Indirizzo di spedizione:</label>
        <input type="text" id="address" name="address"><br>

        <h3>Informazioni della carta</h3>
        
        <label for="cardNumber">Numero di carta:</label>
        <input type="text" id="cardNumber" name="cardNumber" required><br>

        <label for="expirationDate">Data di scadenza:</label>
        <input type="text" id="expirationDate" name="expirationDate" required><br>

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv" required><br>

        <input type="submit" value="Conferma Ordine">
        
    </form>
</body>
</html>