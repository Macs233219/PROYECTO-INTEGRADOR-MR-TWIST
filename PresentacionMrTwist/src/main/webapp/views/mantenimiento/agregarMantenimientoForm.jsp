<%-- 
    Document   : agregarMantenimiento
    Created on : 20 abr 2025, 7:54:20 p. m.
    Author     : jesus
--%>
<%@page import="entidades.EstadoMaquina"%>
<%@page import="entidades.Maquina"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrar Merma - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css">
        <script src="${pageContext.request.contextPath}/scripts/validaMantenimiento.js"></script>
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
                <h2 class="menu-title">Realizar Mantenimiento Maquina</h2>
                <div id="alertaCampos" class="alerta"></div>
                <form id="formularioRegistrarMantenimiento" action="${pageContext.request.contextPath}/agregarMantenimientoServlet" method="POST" onsubmit="mostrarConfirmacion(event)">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="sucursal">Sucursal</label>
                            <select class="form-input" name="idMaquina">
                                <option value="" disabled selected>Selecciona una maquina</option>
                                <%
                                    List<Maquina> maquinas = (List<Maquina>) request.getAttribute("maquinas");
                                    if (maquinas != null) {
                                        for (Maquina maquina : maquinas) {
                                            if (maquina.getEstado() != EstadoMaquina.FUERA_DE_SERVICIO) {
                                %>
                                <option value="<%= maquina.getId()%>">
                                    <%= maquina.getSucursal().getCiudad()%>
                                </option>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </select>

                            <div class="form-row">
                                <div class="form-group">
                                    <label for="descripcion">Descripción:</label>
                                    <input class="form-input" type="text" id="descripcion" name="descripcion" placeholder="Ingrese la descripcion del mantenimiento">
                                </div>
                            </div>
                        </div>
                    </div>   

                    <div class="form-buttons">
                        <button type="submit" class="menu-button btn-guardar">Guardar</button>
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/views/mantenimiento/menuMantenimiento.jsp'">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>

        <div id="confirmModal">
            <div class="modal-content">
                <h3>¿Estás seguro de que deseas realizar este mantenimiento?</h3>
                <div class="modal-buttons">
                    <button class="btn-aceptar" onclick="aceptarConfirmacion()">Aceptar</button>
                    <button class="btn-cancelar" onclick="cancelarConfirmacion()">Cancelar</button>
                </div>
            </div>
        </div>
    </body>
</html>


