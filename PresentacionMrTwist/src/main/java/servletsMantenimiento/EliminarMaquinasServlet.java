/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsMantenimiento;

import InterfacesFachada.MaquinaFachada;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MaquinaFachadaImpl;

/**
 *
 * @author Chris
 */
public class EliminarMaquinasServlet  extends HttpServlet{
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
        Long idMaquina = Long.parseLong(request.getParameter("idMaquina"));
        
        try {
            // Eliminar producto de la baes de datos
            MaquinaFachada productoFachada = new MaquinaFachadaImpl();
            productoFachada.eliminarMaquina(idMaquina);

            // Enviar la lista al JSP
            response.sendRedirect("/Presentacion/views/mantenimiento/menuManetenimiento.jsp");
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/views/mantenimiento/menuManetenimiento.jsp");
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
