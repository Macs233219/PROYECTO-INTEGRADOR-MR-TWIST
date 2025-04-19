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
import java.util.Date;
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
        Long idEntradaInventario = Long.parseLong(request.getParameter("idEntradaInventario"));

        List<Producto> productos = new ArrayList<>();
        ProductoFachada productoFachada = new ProductoFachadaImpl();
        productos = productoFachada.consultarProductos();
        request.setAttribute("productos", productos);

        EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
        EntradaInventario entradaInventario = entradaInventarioFachada.consultarEntradaInventario(idEntradaInventario);
        request.setAttribute("idEntradaInventario", idEntradaInventario);
        request.setAttribute("cantidad", entradaInventario.getCantidad());
        request.setAttribute("idProductoSeleccionado", entradaInventario.getProducto().getId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventario/modificarEntradaForm.jsp");
        dispatcher.forward(request, response);
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
            Long idEntradaInventario = Long.parseLong(request.getParameter("idEntradaInventario"));

            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            EntradaInventario entradaInventario = entradaInventarioFachada.consultarEntradaInventario(idEntradaInventario);

            // definir variables de producto
            int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
            Long idNuevoProducto = Long.parseLong(request.getParameter("idProducto"));
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Producto producto = productoFachada.consultarProducto(idNuevoProducto);

            if (nuevaCantidad < entradaInventario.getCantidad()) {
                
                int diferencia = entradaInventario.getCantidad() - nuevaCantidad;
                
                int nuevaCantidadProducto = producto.getCantidadTotal() - diferencia;
                
                // si la cantidad de producto es menor a la diferencia se vuelve 0
                if (nuevaCantidadProducto < 0) {
                    nuevaCantidadProducto = 0;
                }
                
                producto.setCantidadTotal(nuevaCantidadProducto);
                productoFachada.actualizarProducto(producto);
                
            } else if (nuevaCantidad > entradaInventario.getCantidad()) {
                int diferencia = nuevaCantidad - entradaInventario.getCantidad();
                
                int nuevaCantidadProducto = producto.getCantidadTotal() + diferencia;
                
                producto.setCantidadTotal(nuevaCantidadProducto);
                productoFachada.actualizarProducto(producto);
            }

            producto = productoFachada.consultarProducto(idNuevoProducto);

            entradaInventario.setCantidad(nuevaCantidad);
            entradaInventario.setProducto(producto);

            System.out.println(entradaInventario);

            // persistir entradaInventario
            entradaInventarioFachada.actualizarEntradaInventario(entradaInventario);

            // redirigir a página de sitio
            response.sendRedirect("/Presentacion/views/inventario/menuInventario.jsp");
        } catch (Exception e) {
            e.printStackTrace(System.out);
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
