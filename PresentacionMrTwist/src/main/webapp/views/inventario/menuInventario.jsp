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
        <title>Mr. Twist - Sistema de Inventario</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/menuMantenimiento.css">
    </head>
    <body>
        <header class="header">
            <div class="logo-container">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <img src="${pageContext.request.contextPath}/images/logo_Empresa.png" alt="Logo Mr. Twist" class="logo">
                </a>
            </div>
            <h1 class="title">Mr. Twist</h1>
            <p class="subtitle">Sistema de Control de Inventario</p>
        </header>

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Menú Inventario</h2>
                <div class="menu-grid">
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/agregarProductosServlet'">
                        <span class="button-icon">➕</span>
                        Agregar Producto
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultarProductosServlet'">
                        <span class="button-icon">🔍</span>
                        Consultar Inventario
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/agregarEntradasServlet'">
                        <span class="button-icon">📝</span>
                        Crear Nueva Entrada
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultarEntradasServlet'">
                        <span class="button-icon">📋</span>
                        Consultar Entradas
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