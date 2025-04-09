<%-- 
    Document   : consultas
    Created on : 8 abr 2025, 11:27:03 p.m.
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de Entrada de Inventario - Mr. Twist</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/consultas/style_Consultas.css">
</head>

<body>
    <div class="header-pattern"></div>

    <div class="container">
        <div class="logo-container">
            <img src="../images/logo_mrTwist.png" alt="Mr. Twist Logo" class="logo" />
        </div>

        <div class="content">
            <div class="title-card">
                <h1>Consulta de Entrada de inventario</h1>
            </div>

            <div class="search-bar">
                <input type="text" class="search-input" placeholder="Buscar por código o nombre...">
                <button class="search-button">Buscar</button>
            </div>

            <div class="filters">
                <div class="filter-container">
                    <select class="filter-select">
                        <option>Fecha: Todos</option>
                        <option>Hoy</option>
                        <option>Esta semana</option>
                        <option>Este mes</option>
                    </select>
                </div>

                <div class="filter-container">
                    <select class="filter-select">
                        <option>Estado: Todos</option>
                        <option>Activo</option>
                        <option>Inactivo</option>
                    </select>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fecha</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>001</td>
                        <td>05/04/2025</td>
                        <td>Nieve de chocolate</td>
                        <td>44</td>
                        <td>
                            <button class="action-button info-button">i</button>
                            <button class="action-button delete-button">×</button>
                        </td>
                    </tr>
                    <tr>
                        <td>002</td>
                        <td>04/04/2025</td>
                        <td>Nieve de fresa</td>
                        <td>10</td>
                        <td>
                            <button class="action-button info-button">i</button>
                            <button class="action-button delete-button">×</button>
                        </td>
                    </tr>

                </tbody>
            </table>

            <div class="pagination">
                <button class="page-button active">1</button>
                <button class="page-button">2</button>
                <button class="page-button">3</button>
                <button class="page-button">4</button>
                <span class="page-button">...</span>
            </div>
        </div>
    </div>
</body>

</html>
