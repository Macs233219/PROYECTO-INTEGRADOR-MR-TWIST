/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servletsMantenimiento;

import InterfacesFachada.SucursalFachada;
import com.google.gson.Gson;
import entidades.Sucursal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.SucursalFachadaImpl;

/**
 *
 * @author jesus
 */
@WebServlet("/api/sucursales")
public class SucursalController extends HttpServlet {
    private SucursalFachada sucursalFachada = new SucursalFachadaImpl(); // O inyectada

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Sucursal> sucursales = sucursalFachada.consultarSucursales();
        // Serializar en JSON
        String json = new Gson().toJson(sucursales);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
