<%-- 
    Document   : menuMantenimiento
    Created on : 22 abr 2025, 6:33:01 p. m.
    Author     : jesus
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="Mr. Twist Logo" class="logo">
            </div>
            <h1 class="title">Mr. Twist</h1>
            <p class="subtitle">Sistema de Control de Inventario</p>
        </header>

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Menú Mantenimiento</h2>
                <div class="menu-grid">
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/agregarMantenimientoServlet'">
                        <span class="button-icon">➕</span>
                        Registrar un Mantenimiento
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultarMaquinasServlet'">
                        <span class="button-icon">➕</span>
                        Consultar Maquinas
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultarMantenimientosServlet'">
                        <span class="button-icon">➕</span>
                        Consultar Mantenimientos
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