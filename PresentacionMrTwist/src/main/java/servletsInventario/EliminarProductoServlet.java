/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsInventario;

import entidades.Producto;
import InterfacesFachada.ProductoFachada;
import negocioFachada.ProductoFachadaImpl;
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

/**
 *
 * @author marlon
 */
public class EliminarProductoServlet extends HttpServlet {
    
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
        Long idProducto = Long.parseLong(request.getParameter("idProducto"));
        
        try {
            // Eliminar producto de la baes de datos
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            productoFachada.eliminarProducto(idProducto);

            // Enviar la lista al JSP
            response.sendRedirect("/Presentacion/views/inventario/menuInventario.jsp");
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/views/inventario/menuInventario.jsp");
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
