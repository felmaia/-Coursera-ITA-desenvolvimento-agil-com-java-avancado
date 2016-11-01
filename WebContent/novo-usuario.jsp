<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Novo Cadastro</title>
</head>
<body>
 <div class="container">
 	<div class="panel panel-default">
    	<div class="panel-heading">
			<h2>Projeto Gamification</h2>
		</div>		
		<div class="panel-body">
			<h3>Cadastre-se</h3>  
			<c:if test="${erro != null}">
				<div class="alert alert-danger">
					 <strong>Erro!</strong> ${erro}
				</div>	 
			</c:if>
			<br />
			<form method="post" action="usuarioController">
			  <fieldset>
			  	<legend>Dados de acesso</legend>
			  	<div class="form-group">
				    <label for="nome">Nome</label><span style="color:red">*</span>
				    <input type="text" name="nome" id="nome" value="${param.nome}" class="form-control" />
				</div>
				<div class="form-group">
				  <label for="email">E-mail</label><span style="color:red">*</span>
				  <input type="text" name="email"  id="email" value="${param.email}" class="form-control" />
				</div>
				<div class="form-group">
				  <label for="login">Login</label><span style="color:red">*</span>
				  <input type="text" name="login" id="login" value="${param.login}" class="form-control" />
				</div>
				<div class="form-group">
				  <label for="senha">Senha</label><span style="color:red">*</span>
				  <input type="password" name="senha" id="senha" value="${param.senha}" class="form-control" />
				</div>
				<input type="hidden" name="acao" value="inserir" /> 
				<button type="submit" class="btn btn-primary">
					Cadastrar
				</button>
			   </fieldset>	
			</form>
			<br />
			<a href="<c:url value="/"/>">Já é cadastrado?</a>
		</div>		
	</div>		
 </div>	
</body>
</html>