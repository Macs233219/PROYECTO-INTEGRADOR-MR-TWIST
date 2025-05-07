/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsMantenimiento;

import InterfacesFachada.MantenimientoFachada;
import InterfacesFachada.MaquinaFachada;
import entidades.EstadoMaquina;
import entidades.Mantenimiento;
import entidades.Maquina;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MantenimientoFachadaImpl;
import negocioFachada.MaquinaFachadaImpl;

/**
 *
 * @author Chris
 */
public class ModificarMantenimientoServlet extends HttpServlet {
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
        Long idMaquina = Long.parseLong(request.getParameter("idMaquina"));
        String nuevoEstado = request.getParameter("estado");
        
        MaquinaFachada maquinaFachada = new MaquinaFachadaImpl();

        Maquina maquina = maquinaFachada.consultarMaquina(idMaquina); 
        maquina.setEstado(EstadoMaquina.valueOf(nuevoEstado)); 

        maquinaFachada.actualizarMaquina(maquina); 

        response.sendRedirect("/Presentacion/views/mantenimiento/menuMantenimiento.jsp");
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
