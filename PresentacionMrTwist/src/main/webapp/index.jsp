<%-- 
    Document   : menu
    Created on : 8 abr 2025, 10:27:06 p.m.
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menú Principal - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/producto/index.css">
    </head>
    <body>
        <div class="header-pattern"></div>

        <div class="main-container">
            <div class="logo-container">
                <img src="images/logo_mrTwist.png" alt="Logo Mr. Twist">
            </div>

            <div class="buttons-container">
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuPrincipalServlet'">Inventario</button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuPrincipalServlet'">Mermas</button>
                <button class="menu-button center" onclick="window.location.href = '${pageContext.request.contextPath}/menuPrincipalServlet'">Maquinas</button>
            </div>
        </div>
    </body>
</html>

