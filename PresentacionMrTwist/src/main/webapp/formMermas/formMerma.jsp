<%-- 
    Document   : formMerma
    Created on : 15 abr 2025, 23:50:33
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mr. Twist - Formulario de Mermas</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/formMermas/formMerma.css">
</head>
<body>
    <header class="header">
        <div class="logo-container">
            <img src="<%= request.getContextPath() %>/mr_twist_logo.png" alt="Mr. Twist Logo" class="logo">
        </div>
        <h1 class="title">Mr. Twist</h1>
        <p class="subtitle">Sistema de Control de Inventario</p>
    </header>

    <main class="main-content">
        <div class="menu-container">
            <h2 class="menu-title">Formulario de Mermas</h2>

            <form id="productoForm" method="post" action="procesarMermas.jsp">
                <div class="form-group">
                    <label for="conos">Nombre de producto</label>
                    <select id="conos" name="producto" class="form-input">
                        <option value="" disabled selected>Seleccione un tipo</option>
                        <option value="ninguno">Ninguno</option>
                        <option value="cajacono20">Caja conos de 20</option>
                        <option value="cajacono25">Caja conos 25</option>
                        <option value="cajacono30">Caja conos 30</option>
                        <option value="cajacono45">Caja conos 45</option>
                        <option value="vasos">Vasos</option>
                        <option value="servilletas">Servilletas</option>
                        <option value="nieveVainilla">Nieve sabor vainilla</option>
                        <option value="nieveChocolate">Nieve sabor chocolate</option>
                        <option value="chocolate">Chocolate</option>
                    </select>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="motivo">Motivo:</label>
                        <input type="text" id="motivo" name="motivo" class="form-input" placeholder="Producto dañado">
                    </div>

                    <div class="form-group">
                        <label for="cantidad">Cantidad:</label>
                        <input type="number" id="cantidad" name="cantidad" class="form-input" placeholder="0">
                    </div>

                    <div class="form-group">
                        <label for="fecha">Fecha:</label>
                        <input type="date" id="fecha" name="fecha" class="form-input">
                    </div>
                </div>

                <div class="form-buttons">
                    <button type="submit" class="menu-button btn-guardar">Guardar</button>
                    <button type="reset" class="menu-button btn-cancelar">Cancelar</button>
                </div>
            </form>
        </div>
    </main>

    <footer class="footer">
        <p>Mr. Twist - Sistema de Inventario</p>
    </footer>
</body>
</html>
