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
        <title>Registrar Merma - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css">
    </head>

    <body>
        <header class="header">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo_mrTwist.png" alt="Logo Mr. Twist" class="logo">
            </div>
            <h1 class="title">Mr. Twist</h1>
            <p class="subtitle">Sistema de Control de Inventario</p>
        </header>    

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Registrar Merma</h2>

                <form action="${pageContext.request.contextPath}/consultarAgregarMermasServlet" method="POST">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="producto">Producto:</label>

                            <select class="form-input" name="idProducto">
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
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="cantidad">Cantidad a agregar:</label>
                            <input class="form-input" type="number" id="cantidad" name="cantidad" value="0" min="0"/>
                        </div>
                    </div>
                            
                    <div class="form-row">
                        <div class="form-group">
                            <label for="descripcion">Motivo:</label>
                            <textarea class="form-input" id="motivo" name="motivo" placeholder="Ingrese el motivo de la merma"></textarea>
                        </div>
                    </div>        

                    <div class="form-buttons">
                        <button type="submit" class="menu-button btn-guardar">Guardar</button>
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/views/mermas/menuMermas.jsp'">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>

    </body>
</html>

