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
                    <h1>Consulta de Entrada de inventario</h1>
                </div>

                <form action="${pageContext.request.contextPath}/busquedaEntradasInventarioServlet" method="POST">
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
                                <form action="${pageContext.request.contextPath}/modificarEntradaInventarioServlet" method="POST" style="display:inline;">
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
