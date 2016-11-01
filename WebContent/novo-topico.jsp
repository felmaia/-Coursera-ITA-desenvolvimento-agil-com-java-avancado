<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Novo Tópico</title>
</head>
<body>
 <div class="container">
 	<div class="panel panel-default">
    	<div class="panel-heading">
			<h2>Projeto Gamification</h2>
		</div>		
		<div class="panel-body">
			<h3>Novo Tópico</h3>  
			<c:if test="${erro != null}">
				<div class="alert alert-danger">
					 <strong>Erro!</strong> ${erro}
				</div>	 
			</c:if>
			<br />
			<form method="post" action="topicoController">
			  <fieldset>
			  	<legend>Dados do tópico</legend>
			  	<div class="form-group">
				    <label for="titulo">Título</label><span style="color:red">*</span>
				    <input type="text" name="titulo" id="titulo" value="${param.titulo}" class="form-control" />
				</div>
				<div class="form-group">
				  <label for="texto">Conteúdo</label><span style="color:red">*</span>
				  <textarea rows="10" cols="50" name="conteudo" id="conteudo" value="${param.conteudo}" class="form-control"></textarea>
				</div>
				<input type="hidden" name="acao" value="inserir" /> 
				<button type="submit" class="btn btn-primary">
					Cadastrar
				</button>
			   </fieldset>	
			</form>
			<br />
			<a href="<c:url value="topicoController?acao=listar"/>" class="btn btn-default" role="button">Listar Tópicos</a>
			<a href="<c:url value="usuarioController?acao=ranking"/>" class="btn btn-default" role="button">Ranking</a>
			<a href="<c:url value="/"/>" class="btn btn-default" role="button">Sair</a>
		</div>		
	</div>		
 </div>	
</body>
</html>