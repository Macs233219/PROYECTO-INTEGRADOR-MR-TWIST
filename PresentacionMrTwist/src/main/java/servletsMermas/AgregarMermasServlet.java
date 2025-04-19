/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsMermas;

import InterfacesFachada.EntradaInventarioFachada;
import InterfacesFachada.MermaFachada;
import InterfacesFachada.ProductoFachada;
import entidades.EntradaInventario;
import entidades.Merma;
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
import negocioFachada.MermaFachadaImpl;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author marlon
 */
public class AgregarMermasServlet extends HttpServlet {

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
        List<Producto> productos = new ArrayList<>();
        ProductoFachada productoFachada = new ProductoFachadaImpl();
        productos = productoFachada.consultarProductos();

        // Enviar la lista al JSP
        request.setAttribute("productos", productos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mermas/agregarMermaForm.jsp");
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
            // variables preliminares
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String motivo = request.getParameter("motivo");
            Long idProducto = Long.parseLong(request.getParameter("idProducto"));
            
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Producto producto = productoFachada.consultarProducto(idProducto);
            
            // definir nueva cantidad al producto
            int nuevaCantidadProducto = producto.getCantidadTotal() - cantidad;
            if (nuevaCantidadProducto < 0) {
                nuevaCantidadProducto = 0;
            }
            
            producto.setCantidadTotal(nuevaCantidadProducto);
            
            // actualizar el producto en la base de datos y reobtenerlo
            productoFachada.actualizarProducto(producto);
            producto = productoFachada.consultarProducto(idProducto);

            // crear objeto merma
            Merma merma = new Merma(
                    producto,
                    cantidad,
                    new Date(),
                    null
            );
            
            merma.setMotivo(motivo);

            System.out.println(merma);

            // persistir merma
            MermaFachada mermaFachada = new MermaFachadaImpl();
            mermaFachada.guardarMerma(merma);

            // redirigir a página de sitio
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
