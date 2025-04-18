/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsInventario;

import InterfacesFachada.EntradaInventarioFachada;
import InterfacesFachada.ProductoFachada;
import entidades.EntradaInventario;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class EntradasInventarioServlet extends HttpServlet {

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

        try {
            // definir variables de producto
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Long idProducto = Long.parseLong(request.getParameter("idProducto"));
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Producto producto = productoFachada.consultarProducto(idProducto);
            
            producto.setCantidadTotal(producto.getCantidadTotal() + cantidad);
            productoFachada.actualizarProducto(producto);
            
            producto = productoFachada.consultarProducto(idProducto);

            // crear objeto producto
            EntradaInventario entradaInventario = new EntradaInventario(
                    producto,
                    cantidad,
                    new Date(),
                    null
            );

            System.out.println(entradaInventario);

            // persistir entradaInventario
            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            entradaInventarioFachada.guardarEntradaInventario(entradaInventario);
            

            // redirigir a página de sitio
            response.sendRedirect("/Presentacion/menuInventario.jsp");
        } catch (Exception e) {
            e.printStackTrace(System.out);
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
