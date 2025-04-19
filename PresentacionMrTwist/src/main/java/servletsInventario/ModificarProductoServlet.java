/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsInventario;

import InterfacesFachada.ProductoFachada;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author marlon
 */
public class ModificarProductoServlet extends HttpServlet {

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
        // Obtener producto por modificar
        Long idProducto = Long.parseLong(request.getParameter("idProducto"));
        ProductoFachada productoFachada = new ProductoFachadaImpl();
        Producto producto = productoFachada.consultarProducto(idProducto);

        // Establecer atributos para el formulario
        request.setAttribute("nombre", producto.getNombre());
        request.setAttribute("cantidad", producto.getCantidadTotal());
        request.setAttribute("precio", producto.getPrecioUnitario());
        request.setAttribute("escasez", producto.getCantidadEscasez());
        request.setAttribute("descripcion", producto.getDescripcion());
        request.setAttribute("idProducto", idProducto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventario/modificarProductoForm.jsp");
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
            // Obtener producto por modificar
            Long idProducto = Long.parseLong(request.getParameter("idProducto"));
            ProductoFachada productoFachada = new ProductoFachadaImpl();
            Producto producto = productoFachada.consultarProducto(idProducto);

            // definir nuevas variables de producto
            String nombre = request.getParameter("nombre");
            int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
            int nuevaEscasez = Integer.parseInt(request.getParameter("escasez"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");
            
            // modificar objeto
            producto.setNombre(nombre);
            producto.setCantidadTotal(nuevaCantidad);
            producto.setCantidadEscasez(nuevaEscasez);
            producto.setPrecioUnitario(precio);
            producto.setDescripcion(descripcion);
            
            // actualizar producto
            productoFachada.actualizarProducto(producto);

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
