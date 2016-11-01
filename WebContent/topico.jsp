<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Tópico</title>
</head>
<body>
 <div class="container">
 	<div class="panel panel-default">
    	<div class="panel-heading">
			<h2>Projeto Gamification</h2>
		</div>		
		<div class="panel-body">
			<h3>Tópico</h3> 
			<br />
			<dl>
				<dt>Título</dt>
				<dd>${topico.titulo}</dd>
			</dl>
			<dl>
				<dt>Criado por:</dt>
				<dd>${topico.usuario.nome}</dd>
			</dl>
			<dl>
				<dt>Conteúdo:</dt>
				<dd>${topico.conteudo}</dd>
			</dl>
			
			<br />
			<h4>Comentários:</h4>
			<c:if test="${info != null}">
				<div class="alert alert-warning">
					 ${info}
				</div>	 
			</c:if>
			<ul class="list-group">
				<c:forEach items="${topico.comentarios}" var="c">
				  <li class="list-group-item">
				    <span class="badge"><c:out value="${c.usuario.nome}" /></span>
				    <c:out value="${c.texto}" />
				  </li>
				 </c:forEach> 
			</ul>
			
			<br />
			<h4>Deixe um comentário:</h4>
			<c:if test="${erro != null}">
				<div class="alert alert-danger">
					 <strong>Erro!</strong> ${erro}
				</div>	 
			</c:if>
			<form method="post" action="comentarioController">
				<div class="form-group">
					<textarea rows="10" cols="50" name="texto" class="form-control"></textarea><br /><br />
					<input type="hidden" name="id" value="${topico.id}" />
					<input type="hidden" name="acao" value="inserir" />
					<button type="submit" class="btn btn-primary">
						Comentar
					</button>
				</div>		
			</form>
			<a href="<c:url value="topicoController?acao=listar"/>" class="btn btn-default" role="button">Listar Tópicos</a>
			<a href="<c:url value="/novo-topico.jsp"/>" class="btn btn-default" role="button">Novo Tópico</a>
			<a href="<c:url value="usuarioController?acao=ranking"/>" class="btn btn-default" role="button">Ranking</a>
			<a href="<c:url value="/"/>" class="btn btn-default" role="button">Sair</a>
		</div>		
	</div>		
 </div>	
</body>
</html>