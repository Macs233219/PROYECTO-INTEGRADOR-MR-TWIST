<%-- 
    Document   : agregarMantenimiento
    Created on : 20 abr 2025, 7:54:20 p. m.
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mr. Twist - Gestión de Mantenimientos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/cssCU2/mantenimiento.css">
</head>
<body>

    <div class="header">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo Mr. Twist" class="logo" />
        </div>
        <div class="title">Mr. Twist</div>
        <div class="subtitle">Gestión de Mantenimientos</div>
    </div>

    <main class="main-content">
        <div class="menu-container">
            <div class="menu-title">Historial por Sucursal</div>
            <div id="contenedorSucursales"></div>
        </div>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/mantenimiento.js"></script>
</body>
</html>

