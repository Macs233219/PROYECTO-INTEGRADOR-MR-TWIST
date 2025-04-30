/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsInventario;

import entidades.Producto;
import InterfacesFachada.ProductoFachada;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author Chris
 */
public class BusquedaProductosServlet extends HttpServlet {

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

            int pageSize = 10;
            int paginaActual = 1;

            // Obtener número de página
            String paginaParam = request.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e) {
                    paginaActual = 1;
                }
            }

            // Obtener término de búsqueda
            final String busqueda = request.getParameter("busqueda");
            

            // Obtener y filtrar productos
            List<Producto> todosLosProductos = productoFachada.consultarProductos();
            List<Producto> productosFiltrados = todosLosProductos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                .collect(Collectors.toList());

            int totalProductos = productosFiltrados.size();
            int totalPaginas = (int) Math.ceil((double) totalProductos / pageSize);

            if (paginaActual < 1) paginaActual = 1;
            if (paginaActual > totalPaginas && totalPaginas > 0) paginaActual = totalPaginas;

            int primerResultado = (paginaActual - 1) * pageSize;
            int ultimoResultado = Math.min(primerResultado + pageSize, totalProductos);

            List<Producto> productosPagina = productosFiltrados.subList(primerResultado, ultimoResultado);

            // Atributos para el JSP
            request.setAttribute("productos", productosPagina);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("busqueda", busqueda);

            // Redirige a la misma vista del inventario
            javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventario/consultarProductos.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/views/inventario/menuInventario.jsp");
        }
    }
}
