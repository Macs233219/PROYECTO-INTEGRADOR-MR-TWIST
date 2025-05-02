<%-- 
    Document   : consultas
    Created on : 8 abr 2025, 11:27:03 p.m.
    Author     : user
--%>

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
        <title>Consulta de Entrada de Inventario - Mr. Twist</title>
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
                    <h1>Consulta de Entrada de inventario</h1>
                </div>

                <form action="${pageContext.request.contextPath}/busquedaEntradaInventarioServlet" method="GET">
                    <div class="search-bar">
                        <input id="busqueda" name="busqueda" type="text" class="search-input"
                               placeholder="Buscar por nombre..."
                               value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : ""%>">
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
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<EntradaInventario> entradasInventario = (List<EntradaInventario>) request.getAttribute("entradasInventario");
                            if (entradasInventario != null) {
                                for (EntradaInventario entradaInventario : entradasInventario) {
                        %>
                        <tr>
                            <%
                                Date fecha = entradaInventario.getFecha();
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                String fechaFormateada = formato.format(fecha);
                            %>
                            <td><%= fechaFormateada%></td>

                            <td><%= entradaInventario.getProducto().getNombre()%></td>
                            <td><%= entradaInventario.getCantidad()%></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/modificarEntradaInventarioServlet" method="GET" style="display:inline;">
                                    <input type="hidden" name="idEntradaInventario" value="<%= entradaInventario.getId()%>" />
                                    <button class="action-button info-button">i</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/eliminarEntradaInventarioServlet" method="POST" style="display:inline;">
                                    <input type="hidden" name="idEntradaInventario" value="<%= entradaInventario.getId()%>" />
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

                        String filtroFecha = request.getParameter("filtroFecha") != null ? (String) request.getParameter("filtroFecha") : "";
                        String baseURL;

                        if (!busquedaParam.isEmpty() || !filtroFecha.isEmpty()) {
                            baseURL = request.getContextPath() + "/busquedaEntradaInventarioServlet?busqueda="
                                    + java.net.URLEncoder.encode(busquedaParam, "UTF-8")
                                    + "&filtroFecha=" + java.net.URLEncoder.encode(filtroFecha, "UTF-8")
                                    + "&page=";
                        } else {
                            baseURL = request.getContextPath() + "/consultarEntradasServlet?page=";
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

                <button type="button" class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/menuInventario.jsp'">Volver</button>
            </div>
        </div>
            </main>
    </body>

</html>
