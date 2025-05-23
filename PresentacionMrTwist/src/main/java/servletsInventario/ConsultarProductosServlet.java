/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsInventario;

import InterfacesFachada.ProductoFachada;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author marlon
 */
public class ConsultarProductosServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductoFachada productoFachada = new ProductoFachadaImpl();

            // Parámetros de paginación
            int pageSize = 10;
            int paginaActual = 1;

            // Verifica si se envió el número de página
            String paginaParam = request.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e) {
                    paginaActual = 1;
                }
            }

            // Obtener total de productos y calcular total de páginas
            int totalProductos = productoFachada.contarProductos();
            int totalPaginas = (int) Math.ceil((double) totalProductos / pageSize);

            // Validar que la página esté dentro del rango
            if (paginaActual < 1) {
                paginaActual = 1;
            }
            if (paginaActual > totalPaginas) {
                paginaActual = totalPaginas;
            }

            // Obtener productos para la página actual
            int primerResultado = (paginaActual - 1) * pageSize;
            List<Producto> productos = productoFachada.consultarProductos(pageSize, primerResultado);

            // Enviar los datos a la vista
            request.setAttribute("productos", productos);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventario/consultarProductos.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/views/inventario/menuInventario.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String busqueda = request.getParameter("busqueda");

        try {
            // Obtener productos de la base de datos
            List<Producto> productos = new ArrayList<>();
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            productos = productoFachada.consultarProductos();

            // Filtrar productos
            // Crear una nueva lista para los productos que coincidan con la búsqueda
            List<Producto> productosFiltrados = productos.stream()
                    .filter(producto -> producto.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                    .collect(Collectors.toList());

            // Enviar la lista al JSP
            if (!busqueda.equals("")) {
                request.setAttribute("productos", productosFiltrados);
            } else {
                request.setAttribute("productos", productos);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/consultaInventario.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/menuInventario.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
