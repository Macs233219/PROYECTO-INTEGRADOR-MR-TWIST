<%-- 
    Document   : menuMantenimiento
    Created on : 15 abr 2025, 23:38:49
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mr. Twist - Sistema de Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/menuMantenimiento/menuMantenimiento.css">
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
            <h2 class="menu-title">Menú Mantenimiento</h2>
            <div class="menu-grid">
                <form action="agregarMantenimiento.jsp" method="get">
                    <button class="menu-button" type="submit">
                        <span class="button-icon">➕</span>
                        Agregar Mantenimiento
                    </button>
                </form>
                <form action="consultarMantenimiento.jsp" method="get">
                    <button class="menu-button" type="submit">
                        <span class="button-icon">🔍</span>
                        Consultar Mantenimiento
                    </button>
                </form>
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
