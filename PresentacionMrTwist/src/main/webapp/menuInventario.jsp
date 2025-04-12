<%-- 
    Document   : menuInventario
    Created on : Apr 9, 2025, 2:51:26 AM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menú de Inventario - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/menu_inventario.css">
    </head>
    <body>
        <div class="header-pattern"></div>

        <div class="main-container">
            <div class="logo-container">
                <img src="images/logo_mrTwist.png" alt="Logo Mr. Twist">
            </div>

            <div class="buttons-container">
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuInventarioServlet?action=consultarProductos'">Consultar Productos</button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuInventarioServlet?action=agregarProducto'">Agregar Producto</button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuInventarioServlet?action=consultarEntradasInventario'">Consultar Entradas de Inventario</button>
                <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/menuInventarioServlet?action=agregarEntradaInventario'">Agregar Entrada de Inventario</button>
            </div>
        </div>
    </body>
</html>