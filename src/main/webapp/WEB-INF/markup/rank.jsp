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
    <title>Meu Perfil</title>

    <link rel="stylesheet" href="<c:url value="/css/extendsInicio.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsCard.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsArticles.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsButtons.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-family.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsTable.css"/>">
    <!-- CDN CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

</head>
<body>

<div class="topnav">
    <a href="<c:url value="/redirect/inicio/"/>">Home</a>
    <a href="<c:url value="/redirect/artigos/"/>">Articles</a>
    <a href="<c:url value="/redirect/cursos/"/>">Courses</a>
    <a href="#news">News</a>
    <a class="active" href="<c:url value="/redirect/rank/"/>">Rank de Posts</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    <a href="<c:url value="/redirect/perfil/"/>">Meu Perfil</a>
    <a href="<c:url value="/login/logout/"/>" style="background: red">Sair</a>
</div>

<main>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nome</th>
            <th scope="col">Quantidade de publicações</th>
            <th scope="col">Ranks</th>
        </tr>
        </thead>

        <c:forEach var="rank" items="${ranks}">
            <tbody>
                <tr>
                    <td>${rank.nome}</td>
                    <td>${rank.publicao}</td>
                    <td>${rank.level}</td>
                </tr>
            </tbody>
        </c:forEach>
    </table>

</main>


</body>




<script src="<c:url value="/js/extendsArticle.js"/>"></script>
</html>