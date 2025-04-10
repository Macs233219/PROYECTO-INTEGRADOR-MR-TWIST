<%-- 
    Document   : entrada_inventario
    Created on : 8 abr 2025, 10:34:23 p.m.
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Formulario Entrada Inventario</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/inventario/entrada_inventario.css" />
</head>
<body>
  <div class="header-pattern"></div>

  <div class="form-container">
    <h1>Formulario de entrada<br>inventario</h1>
    <img src="../images/logo_mrTwist.png" alt="Mr. Twist Logo" class="logo" />
    
    <label for="producto">Producto a agregar a entrada:</label>
    <select id="producto">
      <option disabled selected>Seleccione el producto...</option>
    </select>

    <label for="cantidad">Cantidad a agregar:</label>
    <input type="number" id="cantidad" value="0" />

    <div class="botones">
      <button class="guardar">Guardar</button>
      <button class="cancelar">Cancelar</button>
    </div>
  </div>
</body>
</html>