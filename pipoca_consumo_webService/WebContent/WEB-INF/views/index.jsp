<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hora da Pipoca</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body id="LoginForm">
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<div class="panel">
					<h2>Hora da Pipoca</h2>
					<p>Para acessar por favor faça o login</p>
				</div>
				<form action="logar" method="post" id="Login">

					<div class="form-group">
						<input type="email" name="email" class="form-control" id="inputEmail"
							placeholder="Email">
					</div>
					<div class="form-group">
						<input type="password" name="senha" class="form-control" id="inputPassword"
							placeholder="Password">
					</div>
					<button type="submit" name="acao" value="logar" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</div>
	</div>


</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>