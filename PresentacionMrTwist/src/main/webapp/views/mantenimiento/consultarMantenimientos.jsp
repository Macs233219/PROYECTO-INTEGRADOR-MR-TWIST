<%-- 
    Document   : consultarMantenimientos
    Created on : 7 may 2025, 05:35:33
    Author     : Chris
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.EstadoMaquina" %>
<%@page import="entidades.Mantenimiento"%>
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
                <img src="${pageContext.request.contextPath}/images/logo_Empresa.png" alt="Mr. Twist Logo" class="logo" />
            </div>
        </header>
        <main class="main-content">
            <div class="container">

                <div class="content">
                    <div class="title-card">
                        <h1>Consulta de mantenimientos</h1>
                    </div>

                    <form action="${pageContext.request.contextPath}/busquedaMantenimientosServlet" method="GET">
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
                                <th>Mantenimiento</th>
                                <th>Descripcion</th>
                                <th>Sucursal</th>
                                <th>Estado de Maquina</th>
                                <th>Fecha de Mantenimiento</th>
                                <th>Accciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Mantenimiento> mantenimientos = (List<Mantenimiento>) request.getAttribute("mantenimientos");
                                if (mantenimientos != null) {

                                    List<Mantenimiento> mantenimientosFiltrados = new ArrayList<>();
                                    for (Mantenimiento mantenimiento : mantenimientos) {
                                        if (mantenimiento.getMaquina().getEstado() != EstadoMaquina.FUERA_DE_SERVICIO) {
                                            mantenimientosFiltrados.add(mantenimiento);
                                        }
                                    }

                                    for (Mantenimiento mantenimiento : mantenimientos) {
                                        if (mantenimiento.getMaquina().getEstado() == EstadoMaquina.FUERA_DE_SERVICIO) {
                                            mantenimientosFiltrados.add(mantenimiento);
                                        }
                                    }

                                    for (Mantenimiento mantenimiento : mantenimientosFiltrados) {
                            %>
                            <tr>
                                <td><%= mantenimiento.getId()%></td>
                                <td><%= mantenimiento.getDescripcion()%></td>
                                <td><%= mantenimiento.getMaquina().getSucursal().getCiudad()%></td>
                                <td><%= mantenimiento.getMaquina().getEstado()%></td>
                                <td><%= mantenimiento.getFecha()%></td>

                                <td>
                                    <%
                                        if (mantenimiento.getMaquina().getEstado() != EstadoMaquina.FUERA_DE_SERVICIO
                                                && mantenimiento.getMaquina().getEstado() != EstadoMaquina.DISPONIBLE) {
                                    %>
                                    <button type="button"
                                            class="action-button info-button"
                                            onclick="mostrarOpciones('<%= mantenimiento.getMaquina().getId()%>')">
                                        i
                                    </button>
                                    <%
                                    } else {
                                    %>
                                    <span class="disabled-label">Sin acción</span>
                                    <%
                                        }
                                    %>
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

    <div id="modalOpciones" style="display:none; position:fixed; top:30%; left:50%; transform:translate(-50%, -30%); background:#fff; padding:20px; border:1px solid #ccc; z-index:1000; box-shadow: 0px 0px 15px rgba(0,0,0,0.3); border-radius: 10px;">
        <h3>¿Qué deseas hacer con esta máquina?</h3>
        <form id="formCambioEstado" method="POST" action="${pageContext.request.contextPath}/modificarMantenimientoServlet">
            <input type="hidden" name="idMaquina" id="idMaquinaSeleccionada">
            <button type="submit" name="estado" value="DISPONIBLE">✅ Terminar mantenimiento</button>
            <button type="submit" name="estado" value="FUERA_DE_SERVICIO">❌ Desconectar máquina</button>
            <br><br>
            <button type="button" onclick="cerrarModal()">Cancelar</button>
        </form>
    </div>

    <script>
        function mostrarOpciones(idMaquina) {
            document.getElementById('idMaquinaSeleccionada').value = idMaquina;
            document.getElementById('modalOpciones').style.display = 'block';
        }

        function cerrarModal() {
            document.getElementById('modalOpciones').style.display = 'none';
        }
    </script>
</html>

