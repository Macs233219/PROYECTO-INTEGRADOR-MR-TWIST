<%-- 
    Document   : menuInventario
    Created on : 8 abr 2025, 11:35:32 p.m.
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú Inventario - Mr. Twist</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/menuInventario/menu_inventario.css">
</head>
<body>
    <div class="header-pattern"></div>

    <div class="main-container">
        <div class="logo-container">
            <img src="../images/logo_mrTwist.png" alt="Logo Mr. Twist">
        </div>

        <div class="buttons-container">
            <div class="row">
                <button class="menu-button">Agrega producto</button>
                <button class="menu-button">Consulta Inventario</button>
            </div>
            <div class="row">
                <button class="menu-button">Crear nueva entrada</button>
                <button class="menu-button">Consulta entradas</button>
            </div>
        </div>
    </div>
</body>
</html>