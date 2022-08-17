<%--
  Created by IntelliJ IDEA.
  User: gabri
  Date: 8/11/2022
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Edita Usuario</title>

  <link rel="stylesheet" href="<c:url value="/css/extendsInicio.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/extendsArticles.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/extendsButtons.css"/>">

</head>
<body>

<div class="topnav">
  <a href="<c:url value="/redirect/inicio/"/>">Home</a>
  <a href="<c:url value="/redirect/artigos/"/>">Articles</a>
  <a class="active" href="<c:url value="/redirect/cursos/"/>">Courses</a>
  <a href="#news">News</a>
  <a href="#contact">Contact</a>
  <a href="#about">About</a>
  <a class="active" href="<c:url value="/redirect/perfil/"/>">Meu Perfil</a>
  <a href="<c:url value="/login/logout/"/>" style="background: red">Sair</a>
</div>

<main>

  <form class="grid" action="/plataform/usuario/redirectEdit" method="get">
    <label for="title"><b>Titulo do Curso</b></label>

    <input type="hidden" name="id" value="${id}">
    <label for="name">Nome</label>
    <input type="text" placeholder="Novo nome" id="name" name="name" value="${nome}" required>
    <label for="email">Email</label>
    <input type="text" placeholder="Novo email" id="email" name="email" value="${email}" required>
    <label for="senha">Senha</label>
    <input type="text" placeholder="Nova senha" id="senha" name="senha" value="${senha}" required>

    <button class="">Cria curso</button>
  </form>

</main>


</body>

<script src="<c:url value="/js/extendsArticle.js"/>"></script>
</html>