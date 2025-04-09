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
    <title>Formulario de Producto - Mr. Twist</title>
    <link rel="stylesheet" href="../producto/stylesFormProducto.css">
</head>
<body>
    <div class="header-pattern"></div>
    <div class="form-container">
        <h1>Formulario de Producto</h1>
        <div class="logo">
            <img src="../images/logo_mrTwist.png" alt="Logo Mr. Twist">
        </div>

        <form>
            <label for="categoria">Categoría:</label>
            <select id="categoria" name="categoria">
                <option>Conos</option>
                <option>Nieve</option>
                <option>Servilletas</option>
                <option>Chocolate</option>
            </select>

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre del producto">

            <div class="row">
                <div class="col">
                    <label for="cantidad">Cantidad Total:</label>
                    <input type="number" id="cantidad" name="cantidad" value="0">
                </div>
                <div class="col">
                    <label for="precio">Precio Unitario:</label>
                    <input type="text" id="precio" name="precio" value="$0.00">
                </div>
            </div>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" placeholder="Ingrese una descripción detallada del producto"></textarea>

            <div class="buttons">
                <button type="submit" class="guardar">Guardar</button>
                <button type="button" class="cancelar">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>

