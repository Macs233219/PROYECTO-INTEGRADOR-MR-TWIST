/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import daos.ProductoJpaController;
import entidades.Producto;
import InterfacesFachada.ProductoFachada;
import negocioFachada.ProductoFachadaImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marlon
 */
public class ProductosServlet extends HttpServlet {

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
        response.sendRedirect("index.jsp");
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

        try {
            // definir variables de producto
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precioUnitario = Double.parseDouble(request.getParameter("precio"));
            int cantidadTotal = Integer.parseInt(request.getParameter("cantidad"));
            int cantidadEscasez = Integer.parseInt(request.getParameter("escasez"));

            // crear objeto producto
            Producto producto = new Producto(
                    nombre,
                    descripcion,
                    precioUnitario,
                    cantidadTotal,
                    cantidadEscasez,
                    null, null);

            System.out.println(producto);

            // persistir producto
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            productoFachada.guardarProducto(producto);

            // redirigir a página de sitio
            response.sendRedirect("/Presentacion/menuInventario.jsp");
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
