/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsInventario;

import InterfacesFachada.EntradaInventarioFachada;
import InterfacesFachada.ProductoFachada;
import entidades.EntradaInventario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
public class BusquedaEntradasInventarioServlet extends HttpServlet {

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
        String busqueda = request.getParameter("busqueda");

        String filtroFecha = request.getParameter("filtroFecha");

        try {
            // Obtener productos de la base de datos
            List<EntradaInventario> entradasInventario = new ArrayList<>();
            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            entradasInventario = entradaInventarioFachada.consultarEntradasInventario();

            // Filtrar entradas de inventario
            // Crear una nueva lista para las entradas de inventario que coincidan con la búsqueda
            List<EntradaInventario> entradasInventarioFiltradas = entradasInventario.stream()
                    .filter(entradaInventario -> entradaInventario.getProducto().getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                    .collect(Collectors.toList());

            Date hoy = new Date();
            List<EntradaInventario> resultado = entradasInventarioFiltradas;

            if ("hoy".equals(filtroFecha)) {
                // Filtrar entradas con fecha igual al día de hoy
                resultado = entradasInventarioFiltradas.stream()
                        .filter(entrada -> esMismaFecha(entrada.getFecha(), hoy))
                        .collect(Collectors.toList());
            } else if ("estaSemana".equals(filtroFecha)) {
                // Filtrar entradas dentro de la semana actual
                Calendar calInicioSemana = Calendar.getInstance();
                calInicioSemana.setTime(hoy);
                calInicioSemana.set(Calendar.DAY_OF_WEEK, calInicioSemana.getFirstDayOfWeek());

                Calendar calFinSemana = Calendar.getInstance();
                calFinSemana.setTime(hoy);
                calFinSemana.set(Calendar.DAY_OF_WEEK, calFinSemana.getFirstDayOfWeek() + 6);

                resultado = entradasInventarioFiltradas.stream()
                        .filter(entrada -> entrada.getFecha().compareTo(calInicioSemana.getTime()) >= 0
                        && entrada.getFecha().compareTo(calFinSemana.getTime()) <= 0)
                        .collect(Collectors.toList());

            } else if ("esteMes".equals(filtroFecha)) {
                // Filtrar entradas dentro de la semana actual
                Calendar calInicioSemana = Calendar.getInstance();
                calInicioSemana.setTime(hoy);
                calInicioSemana.set(Calendar.DAY_OF_WEEK, calInicioSemana.getFirstDayOfWeek());

                Calendar calFinSemana = Calendar.getInstance();
                calFinSemana.setTime(hoy);
                calFinSemana.set(Calendar.DAY_OF_WEEK, calFinSemana.getFirstDayOfWeek() + 6);

                resultado = entradasInventarioFiltradas.stream()
                        .filter(entrada -> entrada.getFecha().compareTo(calInicioSemana.getTime()) >= 0
                        && entrada.getFecha().compareTo(calFinSemana.getTime()) <= 0)
                        .collect(Collectors.toList());

            }

            // Enviar la lista al JSP
            if (!busqueda.equals("")) {
                request.setAttribute("entradasInventario", resultado);
            } else {
                request.setAttribute("entradasInventario", entradasInventario);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/consultasEntradaInventario.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/Presentacion/menuInventario.jsp");
        }
    }

    // Método para comparar si dos fechas son el mismo día
    private boolean esMismaFecha(Date fecha1, Date fecha2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fecha1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(fecha2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
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
