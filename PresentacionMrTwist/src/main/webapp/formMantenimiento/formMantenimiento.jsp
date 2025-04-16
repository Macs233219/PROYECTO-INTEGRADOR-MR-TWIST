<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mr. Twist - Formulario de Mantenimiento</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/formMantenimiento/form_mantenimiento.css">

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
            <h2 class="menu-title">Formulario de Mantenimiento</h2>
            
            <form id="mantenimientoForm" method="post" action="procesarMantenimiento.jsp">
                <div class="form-group">
                    <label for="codigoMaquina">Código de Máquina:</label>
                    <select id="codigoMaquina" name="codigoMaquina" class="form-input" required>
                        <option value="" disabled selected>Seleccione una máquina</option>
                        <option value="M001">M001</option>
                        <option value="M002">M002</option>
                        <option value="M003">M003</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fecha">Fecha de mantenimiento:</label>
                    <input type="date" id="fecha" name="fecha" class="form-input" required>
                </div>

                <div class="form-group">
                    <label for="Descripcion">Descripción:</label>
                    <input type="text" id="Descripcion" name="descripcion" class="form-input" placeholder="Describa el mantenimiento realizado" required>
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

