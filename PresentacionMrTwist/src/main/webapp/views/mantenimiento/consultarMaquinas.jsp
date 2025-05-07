<%-- 
    Document   : consultarMaquinas
    Created on : 6 may 2025, 22:31:46
    Author     : Chris
--%>

<%@page import="entidades.Maquina"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consulta de Inventario - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/consultas.css?v=<%= System.currentTimeMillis()%>">
    </head>
    <body>

        <header class="header">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo_mrTwist.png" alt="Mr. Twist Logo" class="logo" />
            </div>
        </header>
        <main class="main-content">
            <div class="container">

                <div class="content">
                    <div class="title-card">
                        <h1>Consulta de maquinas</h1>
                    </div>

                    <form action="${pageContext.request.contextPath}/busquedaMaquinasServlet" method="GET">
                        <div class="search-bar">
                            <input id="busqueda" name="busqueda" type="text" class="search-input"
                                   placeholder="Buscar por nombre..."
                                   value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : ""%>">
                            <button class="search-button">Buscar</button>
                        </div>
                    </form>


                    <table>
                        <thead>
                            <tr>
                                <th>Maquina</th>
                                <th>Estado</th>
                                <th>Sucursal</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Maquina> maquinas = (List<Maquina>) request.getAttribute("maquinas");
                                if (maquinas != null) {
                                    for (Maquina maquina : maquinas) {
                            %>
                            <tr>
                                <td><%= maquina.getId()%></td>
                                <td><%= maquina.getEstado()%></td>
                                <td><%= maquina.getSucursal().getCiudad()%></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/eliminarMaquinaServlet" method="POST" style="display:inline;">
                                        <input type="hidden" name="idMaquina" value="<%= maquina.getId()%>" />
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
                        <%
                            int paginaActual = (request.getAttribute("paginaActual") != null)
                                    ? (Integer) request.getAttribute("paginaActual") : 1;
                            int totalPaginas = (request.getAttribute("totalPaginas") != null)
                                    ? (Integer) request.getAttribute("totalPaginas") : 1;

                            String busquedaParam = request.getAttribute("busqueda") != null ? (String) request.getAttribute("busqueda") : "";
                            String baseURL;

                            if (!busquedaParam.isEmpty()) {
                                baseURL = request.getContextPath() + "/busquedaProductosServlet?busqueda=" + java.net.URLEncoder.encode(busquedaParam, "UTF-8") + "&pagina=";
                            } else {
                                baseURL = request.getContextPath() + "/consultarMaquinasServlet?pagina=";
                            }

                            int maxVisiblePages = 5;
                        %>




                        <% if (paginaActual > 1) {%>
                        <a href="<%= baseURL + (paginaActual - 1)%>" class="page-button anterior">Anterior</a>
                        <% } %>

                        <%
                            if (paginaActual > maxVisiblePages / 2 + 1) {
                                for (int i = 1; i <= 2; i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }
                            if (paginaActual > 3) {
                        %>
                        <span class="page-button">...</span>
                        <%
                                }
                            }

                            for (int i = Math.max(1, paginaActual - 1); i <= Math.min(totalPaginas, paginaActual + 1); i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }

                            if (paginaActual < totalPaginas - maxVisiblePages / 2) {
                                if (paginaActual < totalPaginas - 2) {
                        %>
                        <span class="page-button">...</span>
                        <%
                            }
                            for (int i = totalPaginas - 1; i <= totalPaginas; i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                                }
                            }
                        %>

                        <% if (paginaActual < totalPaginas) {%>
                        <a href="<%= baseURL + (paginaActual + 1)%>" class="page-button siguiente">Siguiente</a>
                        <% }%>
                    </div>

                    <button type="button" class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/mantenimiento/menuMantenimiento.jsp'">Volver</button>
                </div>
            </div>
        </main>
    </body>
</html>
