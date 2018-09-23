<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Visualizar Filme</title>

<link href="/pipoca/css/bootstrap.min.css" rel="stylesheet">
<link href="/pipoca/css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Editar Filme</h3>
		<!-- Formulario para inclusao de clientes -->
		<form action="/pipoca/update" method="post">
			<input type="hidden" name="id" value="${filme.id}">
			<div class="row">
				<div class="form-group col-md-4">
					<label for="genero">Gênero</label> <select class="form-control"
						name="genero.id" id="genero">
						<c:forEach var="genero" items="${generos}">

							<c:choose>
								<c:when test="${genero.id == filme.genero.id}">
									<option value="${genero.id}" selected>${genero.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${genero.id}">${genero.nome}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
				</div>
				<div class="form-group col-md-8">
					<label for="titulo">Título</label> <input type="text"
						class="form-control" name="titulo" id="titulo" required
						maxlength="100" placeholder="nome do filme" value="${filme.titulo}">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="descricao">Descrição</label>
					<textarea class="form-control rounded-0" id="descricao"
						name="descricao" maxlength="4000" rows="10">${filme.descricao}</textarea>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8">
					<label for="diretor">Direção</label> <input type="text"
						class="form-control" name="diretor" id="diretor" maxlength="60"
						placeholder="nome do(s) diretor(es)" value="${filme.diretor}">
				</div>

				<div class="form-group col-md-4">
					<label for="dataLancamento">Lançamento</label> <input type="date"
						class="form-control" name="dataLancamento" id="dataLancamento" value="<fmt:formatDate value="${filme.dataLancamento}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8">
					<label for="posterpath">Poster</label> <input type="text"
						class="form-control" name="posterPath" id="posterpath"
						maxlength="200" placeholder="caminho da imagem do poster" value="${filme.posterPath}">
				</div>
				<div class="form-group col-md-4">
					<label for="popularidade">Popularidade</label> <input type="number"
						class="form-control" name="popularidade" id="popularidade"
						min="0.0" max="100.0" value="${filme.popularidade}">
				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao"
						value="criar">Salvar</button>
					<a href="/pipoca/" class="btn btn-default">Cancelar</a>
				</div>
			</div>
		</form>
	</div>
	<script src="/pipoca/js/jquery.min.js"></script>
	<script src="/pipoca/js/bootstrap.min.js"></script>
</body>
</html>