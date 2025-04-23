/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsMantenimiento;

import InterfacesFachada.MantenimientoFachada;
import com.google.gson.Gson;
import entidades.Mantenimiento;
import entidades.Maquina;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MantenimientoFachadaImpl;

@WebServlet("/AgregarMantenimientoServlet")
public class AgregarMantenimientoServlet extends HttpServlet {

    private final MantenimientoFachada mantenimientoFachada = new MantenimientoFachadaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Aquí consultas los mantenimientos desde la fachada
            List<Mantenimiento> mantenimientos = mantenimientoFachada.consultarMantenimientos();

            // Aquí podrías pasar la lista de mantenimientos al JSP si lo necesitas
            request.setAttribute("mantenimientos", mantenimientos);

            // Redirige al JSP (agregarMantenimientoForm.jsp o el que ya estás utilizando)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mantenimiento/agregarMantenimiento.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Presentacion/views/mantenimiento/menuMantenimiento.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer JSON del cuerpo
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        MantenimientoRequest mantenimientoRequest = gson.fromJson(reader, MantenimientoRequest.class);

        // Parsear fecha a java.util.Date
        Date fecha = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha = sdf.parse(mantenimientoRequest.getFecha());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Formato de fecha inválido. Use yyyy-MM-dd.\"}");
            return;
        }

        // Crear el mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setDescripcion(mantenimientoRequest.getDescripcion());
        mantenimiento.setFecha(fecha);
        mantenimiento.setMaquina(new Maquina(mantenimientoRequest.getIdMaquina())); // Constructor con ID

        // Guardar usando fachada
        mantenimientoFachada.guardarMantenimiento(mantenimiento);

        // Respuesta
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write("{\"mensaje\": \"Mantenimiento agregado exitosamente\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID de mantenimiento es requerido.\"}");
            return;
        }

        try {
            Long idMantenimiento = Long.parseLong(idParam);

            // Eliminar el mantenimiento
            mantenimientoFachada.eliminarMantenimiento(idMantenimiento);

            // Si llega aquí es porque la eliminación fue exitosa
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"mensaje\": \"Mantenimiento eliminado exitosamente\"}");

        } catch (NumberFormatException e) {
            // Error en caso de que el ID no sea válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID de mantenimiento inválido.\"}");
        } catch (Exception e) {
            // Manejo de cualquier otro error, como cuando no se encuentra el mantenimiento
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error al eliminar el mantenimiento. Intente nuevamente.\"}");
        }
    }

    // Clase auxiliar para recibir el JSON
    private static class MantenimientoRequest {

        private String descripcion;
        private String fecha;
        private Long idMaquina;

        public String getDescripcion() {
            return descripcion;
        }

        public String getFecha() {
            return fecha;
        }

        public Long getIdMaquina() {
            return idMaquina;
        }
    }
}
