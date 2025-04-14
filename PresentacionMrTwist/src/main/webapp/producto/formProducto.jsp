<%-- 
    Document   : formProducto
    Created on : 8 abr 2025, 11:17:01 p.m.
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agregar Producto - Mr. Twist</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/producto/stylesFormProducto.css">
    </head>
    <body>
        <div class="header-pattern"></div>
        <div class="form-container">
            <h1>Formulario de Producto</h1>
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo_mrTwist.png" alt="Logo Mr. Twist">
            </div>

            <form action="${pageContext.request.contextPath}/productosServlet" method="POST">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre del producto">

                <div class="row">
                    <div class="col">
                        <label for="cantidad">Cantidad Total:</label>
                        <input type="number" id="cantidad" name="cantidad" value="0" min="0">
                    </div>
                    <div class="col">
                        <label for="precio">Precio Unitario:</label>
                        <input type="number" id="precio" name="precio" value="0" min="0">
                    </div>
                </div>

                <label for="cantidad">Cantidad Escasez:</label>
                <input type="number" id="escasez" name="escasez" value="0" min="0">

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" placeholder="Ingrese una descripción detallada del producto"></textarea>

                <div class="buttons">
                    <button type="submit" class="guardar">Guardar</button>
                    <button type="button" class="cancelar" onclick="window.location.href='${pageContext.request.contextPath}/menuInventario.jsp'">Cancelar</button>
                </div>
            </form>
        </div>
    </body>
</html>

