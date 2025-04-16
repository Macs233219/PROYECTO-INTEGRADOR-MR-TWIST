<%-- 
    Document   : menuMermas
    Created on : 15 abr 2025, 23:34:17
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mr. Twist - Sistema de Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/menuMermas/menuMermas.css">
</head>
<body>
    <header class="header">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/api/placeholder/170/189" alt="Mr. Twist Logo" class="logo">
        </div>
        <h1 class="title">Mr. Twist</h1>
        <p class="subtitle">Sistema de Control de Inventario</p>
    </header>
    
    <main class="main-content">
        <div class="menu-container">
            <h2 class="menu-title">Menú Mermas</h2>
            <div class="menu-grid">
                <button class="menu-button">
                    <span class="button-icon">➕</span>
                    Agregar Merma
                </button>
                <button class="menu-button">
                    <span class="button-icon">🔍</span>
                    Consultar Mermas
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
