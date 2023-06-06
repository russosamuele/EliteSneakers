<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isErrorPage="true"
%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>JSP Error page</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="errorDiv">
<% if(exception != null) { %>
<h3> Si è verificato un errore </h3>
<a href="<%=request.getContextPath()%>/common/index.jsp" class="btn btn-primary">Ritorna alla home </a>
<br>
<%}	%>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>