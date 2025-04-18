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
    <title>Mr. Twist - Sistema de Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/menu.css">
</head>
<body>
    <header class="header">
        <div class="logo-container">
            <img src="images/logo_mrTwist.png" alt="Logo Mr. Twist" class="logo">
        </div>
        <h1 class="title">Mr. Twist</h1>
        <p class="subtitle">Sistema de Control de Inventario</p>
    </header>
    
    <main class="main-content">
        <div class="menu-container">
            <h2 class="menu-title">Menú Principal</h2>
            <div class="menu-grid">
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/menuInventario.jsp'">
                    <span class="button-icon">📦</span>
                    Administración de Inventario
                </button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/mermas/menuMermas.jsp'">
                    <span class="button-icon">📉</span>
                    Mermas
                </button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/mantenimientos/menuMantenimientos.jsp'">
                    <span class="button-icon">🔧</span>
                    Mantenimiento
                </button>

            </div>
        </div>
    </main>
    
    <footer>
        <div class="footer-content">
            <p>&copy; Mr. Twist - Sistema de Inventario</p>
        </div>
    </footer>
</body>
</html>