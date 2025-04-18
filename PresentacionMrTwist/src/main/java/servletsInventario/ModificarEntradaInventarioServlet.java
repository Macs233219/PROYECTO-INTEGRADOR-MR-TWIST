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
import java.util.ArrayList;
import java.util.List;
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
public class ModificarEntradaInventarioServlet extends HttpServlet {

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
        Long idEntradaInventario = Long.parseLong(request.getParameter("idEntradaInventario"));

        List<Producto> productos = new ArrayList<>();
        ProductoFachada productoFachada = new ProductoFachadaImpl();
        productos = productoFachada.consultarProductos();
        request.setAttribute("productos", productos);

        EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
        EntradaInventario entradaInventario = entradaInventarioFachada.consultarEntradaInventario(idEntradaInventario);
        request.setAttribute("cantidad", entradaInventario.getCantidad());
        request.setAttribute("idProductoSeleccionado", entradaInventario.getProducto().getId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventario/modificar_entrada_inventario.jsp");
        dispatcher.forward(request, response);
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
