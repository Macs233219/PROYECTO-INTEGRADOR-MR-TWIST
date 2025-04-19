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
public class EliminarEntradaInventarioServlet extends HttpServlet {
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
        Long idEntradaInventario = Long.parseLong(request.getParameter("idEntradaInventario"));
        
        try {
            
            // acceso a datos
            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            EntradaInventario entradaInventario = entradaInventarioFachada.consultarEntradaInventario(idEntradaInventario);
            entradaInventarioFachada.eliminarEntradaInventario(idEntradaInventario);
            
            // calcular nueva cantidad para producto
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Long idProducto = entradaInventario.getProducto().getId();
            Producto producto = productoFachada.consultarProducto(idProducto);
            int nuevaCantidadTotal = producto.getCantidadTotal() - entradaInventario.getCantidad();
            if (nuevaCantidadTotal < 0) {
                nuevaCantidadTotal = 0;
            }
            producto.setCantidadTotal(nuevaCantidadTotal);
            productoFachada.actualizarProducto(producto);

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
