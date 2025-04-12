/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import InterfacesFachada.EntradaInventarioFachada;
import InterfacesFachada.ProductoFachada;
import entidades.EntradaInventario;
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
import negocioFachada.EntradaInventarioFachadaImpl;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author marlon
 */
public class BusquedaEntradasInventarioServlet extends HttpServlet {

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
            List<EntradaInventario> entradasInventario = new ArrayList<>();
            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            entradasInventario = entradaInventarioFachada.consultarEntradasInventario();

            // Filtrar productos
            // Crear una nueva lista para los productos que coincidan con la búsqueda
            List<EntradaInventario> entradasInventarioFiltradas = entradasInventario.stream()
                    .filter(entradaInventario -> entradaInventario.getProducto().getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                    .collect(Collectors.toList());

            // Enviar la lista al JSP
            if (!busqueda.equals("")) {
                request.setAttribute("entradasInventario", entradasInventarioFiltradas);
            } else {
                request.setAttribute("entradasInventario", entradasInventario);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/consultasEntradaInventario.jsp");
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
