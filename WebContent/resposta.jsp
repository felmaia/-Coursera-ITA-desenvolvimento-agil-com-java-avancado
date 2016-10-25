<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resposta Tradu��o</title>
</head>
<body>
	<h2>A tradu��o da palavra "${param.palavra}" �: ${palavraTraduzida} </h2>
	<a href="<c:url value="/"/>">Voltar</a>
	<br />
	<h4>Palavras dispon�veis no dicion�rio:</h4>
	<table>
		<tr>
			<th>Portugu�s</th>
			<th>Ingl�s</th>
		</tr>	
		<c:forEach items="${listaDePalavras}" var="p">
		<tr>
			<td><c:out value="${p.valorEmPortugues}"></c:out></td>
			<td><c:out value="${p.valorEmIngles}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>