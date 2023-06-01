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
	
	<section class="managementSection"> 
	
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
			<li><a href="AggiungiProdotto.jsp">Aggiungi un nuovo prodotto</a></li>
			<li><a href="AggiungiDisponibilita.jsp">Aggiungi disponibilita prodotto</a></li>
			<li><a href="ModificaProdotto.jsp">Modifica un prodotto esistente</a></li>
			<li><a href="RimuoviProdotto.jsp">Rimuovi un prodotto esistente</a></li>
		</ul>
		
		<h3>Gestione ordini</h3>
		<ul>
			<li><a href="visualizzaOrdiniAdmin.jsp">Visualizza tutti gli ordini</a></li>
		</ul>
		
		<h3>Gestione utenti</h3>
		<ul>
			<li><a href="VisualizzaUtenti.jsp">Visualizza tutti gli utenti</a></li>
			<li><a href="RimuoviUtente.jsp">Rimuovi un utente esistente</a></li>
		</ul>
		
	</section>
	</main>
	
	
	<% }%>
	
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>
    