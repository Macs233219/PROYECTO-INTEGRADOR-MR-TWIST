/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsMantenimiento;

import InterfacesFachada.SucursalFachada;
import com.google.gson.Gson;
import entidades.Sucursal;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.SucursalFachadaImpl;

@WebServlet("/api/sucursales")
public class SucursalController extends HttpServlet {

    private SucursalFachada sucursalFachada = new SucursalFachadaImpl(); // O inyectada

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Sucursal> sucursales = sucursalFachada.consultarSucursales();

        // 👇 Forzar la carga de distribucionesTotales para evitar LazyInitializationException
        for (Sucursal s : sucursales) {
            if (s.getDistribucionesTotales() != null) {
                s.getDistribucionesTotales().size(); // Esto "inicializa" la lista
            }
        }

        // Si la lista de sucursales está vacía, respondemos con un mensaje de error
        if (sucursales == null || sucursales.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
            resp.getWriter().write("{\"error\": \"No se encontraron sucursales.\"}");
            return;
        }

        // Serializar en JSON
        String json = new Gson().toJson(sucursales);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
