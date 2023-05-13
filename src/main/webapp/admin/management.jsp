<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.UserDAO, model.UserBean"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html>
<head>
	<title>Elite Sneakers - Area Riservata</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	
	<%@ include file="../common/header.jsp"%>
	
	<main>
	
		<% 
		UserBean user =  (UserBean)session.getAttribute("user");
		
		if(user == null){
			String percorso = request.getContextPath();
			response.sendRedirect(percorso + "/common/login.jsp");
				return ;
		}
		else{
		
		%>
		
		<h2>Benvenuto nell'area di gestione, <%=user.getNome()%>!</h2>
		
		<h3>Gestione prodotti</h3>
		<ul>
			<li><a href="aggiungi_prodotto.jsp">Aggiungi un nuovo prodotto</a></li>
			<li><a href="modifica_prodotto.jsp">Modifica un prodotto esistente</a></li>
			<li><a href="rimuovi_prodotto.jsp">Rimuovi un prodotto esistente</a></li>
		</ul>
		
		<h3>Gestione ordini</h3>
		<ul>
			<li><a href="visualizza_ordini.jsp">Visualizza tutti gli ordini</a></li>
			<li><a href="gestisci_ordine.jsp">Gestisci un ordine esistente</a></li>
		</ul>
		
		<h3>Gestione utenti</h3>
		<ul>
			<li><a href="visualizza_utenti.jsp">Visualizza tutti gli utenti</a></li>
			<li><a href="modifica_utente.jsp">Modifica un utente esistente</a></li>
			<li><a href="rimuovi_utente.jsp">Rimuovi un utente esistente</a></li>
		</ul>
	</main>
	
	<% }%>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
    