<%--
  Created by IntelliJ IDEA.
  User: gabri
  Date: 8/9/2022
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Entrar</title>
    <link rel="stylesheet" href="<c:url value="/css/extendsForm.css"/>">
</head>
<body>

<div class="grid">
    <form class="grid" action="/plataform/login/authenticate" method="post"> <!-- get is standard -->

        <c:if test="${error != null}"><h1>${error}</h1></c:if>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <a href="<c:url value="/redirect/dontHave"/>"><p>Não tem uma Conta? Registre-se Aqui</p></a>

        <input type="submit" value="Submit">
    </form>
</div>

<div class="grid" id="message">
    <h3>Password must contain the following:</h3>
    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
    <p id="number" class="invalid">A <b>number</b></p>
    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
</div>

</body>
</html>
