<%-- 
    Document   : entrada_inventario
    Created on : 8 abr 2025, 10:34:23 p.m.
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Agregar Entrada de Inventario</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inventario/entrada_inventario.css" />
    </head>
    <body>
        <div class="header-pattern"></div>

        <div class="form-container">
            <h1>Formulario de entrada<br>inventario</h1>
            <img src="${pageContext.request.contextPath}/images/logo_mrTwist.png" alt="Mr. Twist Logo" class="logo" />

            <form action="${pageContext.request.contextPath}/entradasInventarioServlet" method="POST">
                <label for="producto">Producto a agregar a entrada:</label>

                <select name="idProducto">
                    <%
                        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                        if (productos != null) {
                            for (Producto producto : productos) {
                    %>
                    <option value="<%= producto.getId()%>"><%= producto.getNombre()%></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label for="cantidad">Cantidad a agregar:</label>
                <input type="number" id="cantidad" name="cantidad" value="0" min="0"/>

                <div class="botones">
                    <button type="submit" class="guardar">Guardar</button>
                    <button type="button" class="cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/menuInventario.jsp'">Cancelar</button>
                </div>
            </form>
        </div>
    </body>
</html>