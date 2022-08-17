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
    <title>Inicio</title>


    <link rel="stylesheet" href="<c:url value="/css/extendsInicio.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsCard.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/extendsArticles.css"/>">

</head>
<body>

<div class="topnav">
    <a class="active" href="<c:url value="/redirect/inicio/"/>">Home</a>
    <a href="<c:url value="/redirect/artigos/"/>">Articles</a>
    <a href="<c:url value="/redirect/cursos/"/>">Courses</a>
    <a href="#news">News</a>
    <a href="<c:url value="/redirect/rank/"/>">Rank de Posts</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    <a href="<c:url value="/redirect/perfil/"/>">Meu Perfil</a>
    <a href="<c:url value="/login/logout/"/>" style="background: red">Sair</a>
</div>
    <main>
        <div class="cards">

            <!--pega do model attribute e joga a lista de cursos em curso -->
            <c:forEach var="curso" items="${cursos}">
                <div class="card">
                    <img src="<c:url value="/images/img_avatar.png"/>" alt="Avatar" style="width: 100%">
                    <div class="container">
                        <h4><strong>${curso.titulo}</strong></h4>
                        <p>${curso.descricao}</p>
                    </div>
                </div>
            </c:forEach>


        </div>


            <div class="articles">
                <c:forEach var="artigo" items="${artigos}">
                    <article>
                        <h3><strong>${artigo.title}</strong></h3>
                        <p>${artigo.text}</p>

                        <!--
                        <button id="myBtn">Read more</button>
                        <button id="myBtn">Edit</button>
                        <button id="myBtn">Delete</button>
                        -->

                    </article>
                </c:forEach>
            </div>



    </main>

</body>


<script src="<c:url value="/js/extendsArticle.js"/>"></script>
</html>