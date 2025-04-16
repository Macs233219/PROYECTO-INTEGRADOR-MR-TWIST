<%-- 
    Document   : consultarMermas
    Created on : 15 abr 2025, 23:45:33
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mr. Twist - Consulta de Mermas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/consultarMermas/consultar_mermas.css">
</head>
<body>
    <header class="header">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/assets/img/mr_twist_logo.png" alt="Mr. Twist Logo" class="logo">
        </div>
        <h1 class="title">Mr. Twist</h1>
        <p class="subtitle">Sistema de Control de Inventario</p>
    </header>

    <main class="main-content">
        <div class="menu-container">
            <h2 class="menu-title">Consulta de Mermas</h2>

            <div class="search-row">
                <input type="text" class="search-input" placeholder="Buscar por producto...">
                <button class="menu-button btn-buscar">Buscar</button>
            </div>

            <div class="filter-row">
                <select class="form-input">
                    <option>Fecha: Todos</option>
                </select>
            </div>

            <div class="table-container">
                <table class="data-table">
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
                        <!-- Aquí puedes usar JSTL para llenar la tabla dinámicamente -->
                        <tr>
                            <td>001</td>
                            <td>05/04/2025</td>
                            <td>Nieve de chocolate</td>
                            <td>4</td>
                            <td>
                                <button class="action-button info-button">i</button>
                                <button class="action-button delete-button">x</button>
                            </td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>04/04/2025</td>
                            <td>Nieve de fresa</td>
                            <td>3</td>
                            <td>
                                <button class="action-button info-button">i</button>
                                <button class="action-button delete-button">x</button>
                            </td>
                        </tr>
                        <tr>
                            <td>003</td>
                            <td>03/04/2025</td>
                            <td>Nieve de vainilla</td>
                            <td>5</td>
                            <td>
                                <button class="action-button info-button">i</button>
                                <button class="action-button delete-button">x</button>
                            </td>
                        </tr>
                        <tr>
                            <td>004</td>
                            <td>02/04/2025</td>
                            <td>Conos</td>
                            <td>8</td>
                            <td>
                                <button class="action-button info-button">i</button>
                                <button class="action-button delete-button">x</button>
                            </td>
                        </tr>
                        <tr>
                            <td>005</td>
                            <td>01/04/2025</td>
                            <td>Chispitas</td>
                            <td>2</td>
                            <td>
                                <button class="action-button info-button">i</button>
                                <button class="action-button delete-button">x</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="pagination">
                <button class="page-button active">1</button>
                <button class="page-button">2</button>
                <button class="page-button">3</button>
                <button class="page-button">4</button>
                <span class="pagination-dots">...</span>
            </div>
        </div>
    </main>

    <footer class="footer">
        <p>Mr. Twist - Sistema de Inventario</p>
    </footer>
</body>
</html>
