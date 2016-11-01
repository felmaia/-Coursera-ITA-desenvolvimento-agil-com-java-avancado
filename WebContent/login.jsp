<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Login</title>
</head>
<body>
 <div class="container">
 	<div class="panel panel-default">
    	<div class="panel-heading">
			<h2>Projeto Gamification</h2>
		</div>		
		<div class="panel-body">
			<h3>Login</h3> 
			<c:if test="${erro != null}">
				<div class="alert alert-danger">
					 <strong>Erro!</strong> ${erro}
				</div>	 
			</c:if>
			<c:if test="${sucessoCadastro != null}">
				<div class="alert alert-success">
					<strong>Successo!</strong> ${sucessoCadastro}
				</div>
			</c:if>
			<form method="post" action="usuarioController">
				<input type="text" name="login" placeholder="Digite o Login" />
				<input type="password" name="senha" placeholder="Digite a Senha" />
				<input type="hidden" name="acao" value="autenticar" />
				<input type="submit" value="Enviar" />
			</form>
			<br />
			<a href="<c:url value="/novo-usuario.jsp"/>">Cadastre-se</a>
		</div>		
	</div>		
 </div>	
</body>
</html>