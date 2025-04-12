/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import InterfacesFachada.EntradaInventarioFachada;
import entidades.Producto;
import InterfacesFachada.ProductoFachada;
import entidades.EntradaInventario;
import negocioFachada.ProductoFachadaImpl;
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

/**
 *
 * @author marlon
 */
public class MenuInventarioServlet extends HttpServlet {

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

        String action = request.getParameter("action");

        if ("consultarProductos".equals(action)) {

            try {
                List<Producto> productos = new ArrayList<>();
                ProductoFachada productoFachada = new ProductoFachadaImpl();
                productos = productoFachada.consultarProductos();

                // Enviar la lista al JSP
                request.setAttribute("productos", productos);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/consultaInventario.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("menuInventario.jsp");
            }

        } else if ("agregarProducto".equals(action)) {
            response.sendRedirect("/Presentacion/producto/formProducto.jsp");
        } else if("consultarEntradasInventario".equals(action)) {
            
            List<EntradaInventario> entradasInventario = new ArrayList<>();
                EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
                entradasInventario = entradaInventarioFachada.consultarEntradasInventario();
                
                System.out.println(entradasInventario.get(0));

                // Enviar la lista al JSP
                request.setAttribute("entradasInventario", entradasInventario);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/consultasEntradaInventario.jsp");
                dispatcher.forward(request, response);
            
        } else if("agregarEntradaInventario".equals(action)) {
            response.sendRedirect("/Presentacion/inventario/entrada_inventario.jsp");
        }
        else {
            // Manejo de errores o acción predeterminada
            response.sendRedirect("menuInventario.jsp");
        }

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
