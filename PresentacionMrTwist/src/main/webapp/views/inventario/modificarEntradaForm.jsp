<%-- 
    Document   : formProducto
    Created on : 8 abr 2025, 11:17:01 p.m.
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
        <title>Agregar Entrada - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css">
    </head>

    <body>
        <header class="header">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo_Empresa.png" alt="Logo Mr. Twist" class="logo">
            </div>
            <h1 class="title">Mr. Twist</h1>
            <p class="subtitle">Sistema de Control de Inventario</p>
        </header>    

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Modificar Entrada</h2>

                <form action="${pageContext.request.contextPath}/modificarEntradaInventarioServlet" method="POST">
                    
                    <input type="hidden" name="idEntradaInventario" value="${idEntradaInventario}" />

                    <div class="form-row">
                        <div class="form-group">
                            <label for="producto">Producto:</label>

                            <select class="form-input" name="idProducto">
                                <%
                                    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                                    Long idSeleccionado = (Long) request.getAttribute("idProductoSeleccionado");
                                    if (productos != null) {
                                        for (Producto producto : productos) {
                                %>
                                <option value="<%= producto.getId()%>" <%= producto.getId().equals(idSeleccionado) ? "selected" : ""%>>
                                    <%= producto.getNombre()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="cantidad">Cantidad a agregar:</label>
                            <input class="form-input" type="number" id="cantidad" name="cantidad" value="${cantidad}" min="0"/>
                        </div>
                    </div>

                    <div class="form-buttons">
                        <button type="submit" class="menu-button btn-guardar">Guardar</button>
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/menuInventario.jsp'">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>

    </body>
</html>

