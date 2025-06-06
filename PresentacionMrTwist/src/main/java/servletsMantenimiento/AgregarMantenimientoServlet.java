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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MantenimientoFachadaImpl;
import negocioFachada.MaquinaFachadaImpl;
import negocioFachada.ProductoFachadaImpl;

/**
 *
 * @author Chris
 */
public class AgregarMantenimientoServlet extends HttpServlet {
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
        List<Maquina> maquinas = new ArrayList<>();
        MaquinaFachada maquinaFachada = new MaquinaFachadaImpl();
        maquinas = maquinaFachada.consultarMaquinas();

        // Enviar la lista al JSP
        request.setAttribute("maquinas", maquinas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mantenimiento/agregarMantenimientoForm.jsp");
        dispatcher.forward(request, response);
        response.sendRedirect("/Presentacion/views/mantenimiento/agregarMantenimientoForm.jsp");
        
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
            
            MaquinaFachada maquinaFachada = new MaquinaFachadaImpl();
            // definir variables de producto
            Long idProducto = Long.parseLong(request.getParameter("idMaquina"));
            String descripcion = request.getParameter("descripcion");
            
            Maquina maquina = maquinaFachada.consultarMaquina(idProducto);
            
            maquina.setEstado(EstadoMaquina.REVISION);
            maquinaFachada.actualizarMaquina(maquina);

            // crear objeto producto
            Mantenimiento mantenimiento = new Mantenimiento(
                    maquina,
                    descripcion, new Date());
            
            System.out.println(mantenimiento);

            // persistir producto
            MantenimientoFachada mantenimientoFachada = new MantenimientoFachadaImpl();
            mantenimientoFachada.guardarMantenimiento(mantenimiento);

            // redirigir a página de sitio
            response.sendRedirect("/Presentacion/views/mantenimiento/menuMantenimiento.jsp");
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/views/mantenimiento/menuMantenimiento.jsp");
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
