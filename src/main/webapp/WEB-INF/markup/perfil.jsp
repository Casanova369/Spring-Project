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

</head>
<body>

<div class="topnav">
    <a href="<c:url value="/redirect/inicio/"/>">Home</a>
    <a href="<c:url value="/redirect/artigos/"/>">Articles</a>
    <a href="<c:url value="/redirect/cursos/"/>">Courses</a>
    <a href="#news">News</a>
    <a href="<c:url value="/redirect/rank/"/>">Rank de Posts</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    <a class="active" href="<c:url value="/redirect/perfil/"/>">Meu Perfil</a>
    <a href="<c:url value="/login/logout/"/>" style="background: red">Sair</a>
</div>



<main>
    <div class="user-info">
        <!--pega do model attribute e joga a lista de cursos em curso -->



        <pre><h1>Bem vindo ${usuario.nome}!</h1></pre>
        <h1>${usuario.level}</h1>
    </div>

    <form action="/plataform/curso/redirectCreate" >
        <button class="create">Criar curso</button>
    </form>


    <div class="cards">
        <!--pega do model attribute e joga a lista de cursos em curso -->
        <c:forEach var="curso" items="${cursos}">
            <div class="card">
                <img src="<c:url value="/images/img_avatar.png"/>" alt="Avatar" style="width: 100%">
                <div class="container">
                    <h3><strong>${curso.titulo}</strong></h3>
                    <p>${curso.descricao}</p>
                    <p>${curso.usuario}</p>
                </div>

                <div class="buttons">
                    <form action="/plataform/modulo/redirectCreate">
                        <button>Criar Modulos</button>
                    </form>

                    <form action="/plataform/curso/redirectEdit">
                        <input type="hidden" name="title" value="${curso.titulo}">
                        <input type="hidden" name="description" value="${curso.descricao}">
                        <input type="hidden" name="id" type="hidden" value="${curso.id}">
                        <button>Edit</button>
                    </form>

                    <form action="/plataform/curso/delete">
                        <input name="id_us" type="hidden" value="${curso.usuario}">
                        <input name="id_cu" type="hidden" value="${curso.id}">
                        <button>Delete</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>

    <form action="/plataform/artigo/redirectCreate" >
        <button class="create">Criar artigo</button>
    </form>

    <div class="articles">
        <c:forEach var="artigo" items="${artigos}">
            <article>
                <h4><strong>${artigo.title}</strong></h4>
                <p>${artigo.text}</p>
                <p>${artigo.usuario}</p>

                <div class="buttons">
                    <form action="/plataform/artigo/redirectEdit">
                        <input name="id" type="hidden" value="${artigo.id}">
                        <input name="title" type="hidden" value="${artigo.title}">
                        <input name="text" type="hidden" value="${artigo.text}">
                        <button>Edit</button>
                    </form>
                    <form action="/plataform/artigo/delete">
                        <input name="id_us" type="hidden" value="${artigo.usuario}">
                        <input name="id_ar" type="hidden" value="${artigo.id}">
                        <button>Delete</button>
                    </form>
                </div>

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