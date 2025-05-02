<%-- 
    Document   : consultas
    Created on : 8 abr 2025, 11:27:03 p.m.
    Author     : user
--%>

<%@page import="entidades.Merma"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.EntradaInventario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consulta de Mermas - Mr. Twist</title>
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
                    <h1>Consulta de Mermas</h1>
                </div>

                <form action="${pageContext.request.contextPath}/busquedaMermasServlet" method="GET">
                    <div class="search-bar">
                        <input id="busqueda" name="busqueda" type="text" class="search-input" placeholder="Buscar por nombre...">
                        <button class="search-button">Buscar</button>
                    </div>
                    
                    <div class="filters">
                    <div class="filter-container">
                        <select class="filter-select" name="filtroFecha">
                            <option value="todos">Fecha: Todos</option>
                            <option value="hoy">Fecha: Hoy</option>
                            <option value="semana">Fecha: Esta semana</option>
                            <option value="mes">Fecha: Este mes</option>
                        </select>
                    </div>
                </div>
                </form>

                <table>
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Motivo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Merma> mermas = (List<Merma>) request.getAttribute("mermas");
                            if (mermas != null) {
                                for (Merma merma : mermas) {
                        %>
                        <tr>
                            <%
                                Date fecha = merma.getFecha();
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                String fechaFormateada = formato.format(fecha);
                            %>
                            <td><%= fechaFormateada%></td>

                            <td><%= merma.getProducto().getNombre()%></td>
                            <td><%= merma.getCantidad()%></td>
                            <td><%= merma.getMotivo()%></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/eliminarMermaServlet" method="POST" style="display:inline;">
                                    <input type="hidden" name="idMerma" value="<%= merma.getId()%>" />
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
                            baseURL = request.getContextPath() + "/busquedaMermasServlet?busqueda=" + java.net.URLEncoder.encode(busquedaParam, "UTF-8") + "&pagina=";
                        } else {
                            baseURL = request.getContextPath() + "/consultarMermasServlet?pagina=";
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

                <button type="button" class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/mermas/menuMermas.jsp'">Volver</button>
            </div>
        </div>
            </main>
    </body>

</html>
