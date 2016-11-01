<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
	<title>Ranking</title>
</head>
<body>
 <div class="container">
 	<div class="panel panel-default">
    	<div class="panel-heading">
			<h2>Projeto Gamification</h2>
		</div>		
		<div class="panel-body">
			<h3>Ranking</h3> 
			<c:if test="${info != null}">
				<div class="alert alert-info">
					 ${info}
				</div>	 
			</c:if>
			<table class="table table-hover">
			   <thead>
				<tr>
					<th>Posição</th>
					<th>Nome</th>
					<th>Login</th>
					<th>Pontos</th>
				</tr>
			   <thead>		
				<c:forEach varStatus="counter" items="${ranking}" var="u">
					<tr>
						<td><c:out value="${counter.count}º" /></td>
						<td><c:out value="${u.nome}" /></td>
						<td><c:out value="${u.login}" /></td>
						<td><c:out value="${u.pontos}" /></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<a href="<c:url value="topicoController?acao=listar"/>" class="btn btn-default" role="button">Listar Tópicos</a>
			<a href="<c:url value="/novo-topico.jsp"/>" class="btn btn-default" role="button">Novo Tópico</a>
			<a href="<c:url value="/"/>" class="btn btn-default" role="button">Sair</a>
		</div>		
	</div>		
 </div>	
</body>
</html>