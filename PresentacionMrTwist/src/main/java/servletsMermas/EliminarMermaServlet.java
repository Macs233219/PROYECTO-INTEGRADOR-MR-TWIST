/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsMermas;

import InterfacesFachada.MermaFachada;
import InterfacesFachada.ProductoFachada;
import entidades.Merma;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MermaFachadaImpl;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author marlon
 */
public class EliminarMermaServlet extends HttpServlet {

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
        
        Long idMerma = Long.parseLong(request.getParameter("idMerma"));
        
        try {
            
            // eliminar merma de la baes de datos
            MermaFachada mermaFachada = new MermaFachadaImpl();
            Merma merma = mermaFachada.consultarMerma(idMerma);
            mermaFachada.eliminarMerma(idMerma);
            
            // calcular nueva cantidad para producto
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Long idProducto = merma.getProducto().getId();
            Producto producto = productoFachada.consultarProducto(idProducto);
            int nuevaCantidadTotal = producto.getCantidadTotal() + merma.getCantidad();
            producto.setCantidadTotal(nuevaCantidadTotal);
            productoFachada.actualizarProducto(producto);
            
            // enviar a vista de menu
            response.sendRedirect("/Presentacion/views/mermas/menuMermas.jsp");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            response.sendRedirect("/Presentacion/views/mermas/menuMermas.jsp");
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
