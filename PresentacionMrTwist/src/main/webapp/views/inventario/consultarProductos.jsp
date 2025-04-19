<%-- 
    Document   : consultaInventario
    Created on : 8 abr 2025, 11:31:43 p.m.
    Author     : user
--%>

<%@page import="entidades.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consulta de Inventario - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/consultas.css">
    </head>
    <body>
        <div class="header-pattern"></div>

        <div class="container">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo_mrTwist.png" alt="Mr. Twist Logo" class="logo" />
            </div>

            <div class="content">
                <div class="title-card">
                    <h1>Consulta de inventario</h1>
                </div>

                <form action="${pageContext.request.contextPath}/busquedaProductosServlet" method="POST">
                    <div class="search-bar">
                        <input id="busqueda" name="busqueda" type="text" class="search-input" placeholder="Buscar por nombre...">
                        <button class="search-button">Buscar</button>
                    </div>
                </form>

                <table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                            if (productos != null) {
                                for (Producto producto : productos) {
                        %>
                        <tr>
                            <td><%= producto.getNombre()%></td>
                            <td><%= producto.getCantidadTotal()%></td>
                            <td><%= String.format("$%.2f", producto.getPrecioUnitario())%></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/modificarProductoServlet" method="GET" style="display:inline;">
                                    <input type="hidden" name="idProducto" value="<%= producto.getId()%>" />
                                    <button class="action-button info-button">i</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/eliminarProductoServlet" method="POST" style="display:inline;">
                                    <input type="hidden" name="idProducto" value="<%= producto.getId()%>" />
                                    <button type="submit" class="action-button delete-button">×</button>
                                </form>

                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>

                <div class="pagination">
                    <button class="page-button active">1</button>
                    <button class="page-button">2</button>
                    <button class="page-button">3</button>
                    <button class="page-button">4</button>
                    <span class="page-button">...</span>
                </div>

                <button type="button" class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/menuInventario.jsp'">Volver</button>
            </div>
        </div>
    </body>
</html>