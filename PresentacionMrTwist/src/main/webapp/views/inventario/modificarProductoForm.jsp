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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css">
    </head>

    <body>
        <header class="header">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo_Empresa.png" alt="Logo Mr. Twist" class="logo">
            </div>
            <h1 class="title">Mr. Twist</h1>
            <p class="subtitle">Sistema de Control de Inventario</p>
        </header>    

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Modificar Producto</h2>

                <form action="${pageContext.request.contextPath}/modificarProductoServlet" method="POST">
                    
                    <input type="hidden" name="idProducto" value="${idProducto}" />

                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input class="form-input" type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre del producto" value="${nombre}">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="cantidad">Cantidad Total:</label>
                            <input class="form-input" type="number" id="cantidad" name="cantidad" value="${cantidad}" min="0">
                        </div>

                        <div class="form-group">
                            <label for="precio">Precio Unitario:</label>
                            <input class="form-input" type="number" id="precio" name="precio" value="${precio}" min="0">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="cantidad">Cantidad Escasez:</label>
                            <input class="form-input" type="number" id="escasez" name="escasez" value="${escasez}" min="0">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="descripcion">Descripción:</label>
                            <textarea class="form-input" id="descripcion" name="descripcion" placeholder="Ingrese una descripción detallada del producto">${descripcion}</textarea>
                        </div>
                    </div>

                    <div class="form-buttons">
                        <button type="submit" class="menu-button btn-guardar">Guardar</button>
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/menuInventario.jsp'">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>

    </body>
</html>

