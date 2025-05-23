/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import InterfacesFachada.MaquinaFachada;
import InterfacesFachada.SucursalFachada;
import entidades.EstadoMaquina;
import entidades.Maquina;
import entidades.Sucursal;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.MaquinaFachadaImpl;
import negocioFachada.SucursalFachadaImpl;

/**
 *
 * @author Chris
 */
public class CrearDatosInicialesServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        SucursalFachada sucursalFachada = new SucursalFachadaImpl();
        MaquinaFachada maquinaFachada = new MaquinaFachadaImpl();

        try {

            // Crear sucursal solo si no existe
            Sucursal sucursal = sucursalFachada.consultarSucursal("Ciudad Obregon");
            if (sucursal == null) {
                sucursal = new Sucursal();
                sucursal.setCiudad("Ciudad Obregon");
                sucursal.setNombreEncargado("alfredito");
                sucursalFachada.guardarSucursal(sucursal);
            }

            // Crear máquina solo si no hay ninguna en esta sucursal
            List<Maquina> maquinas = maquinaFachada.buscarMaquinaPorSucursalId(sucursal.getId());
            if (maquinas == null || maquinas.isEmpty()) {
                Maquina nuevaMaquina = new Maquina();
                nuevaMaquina.setEstado(EstadoMaquina.DISPONIBLE);
                nuevaMaquina.setSucursal(sucursal);
                maquinaFachada.guardarMaquina(nuevaMaquina);
            }

        } catch (Exception e) {
            log("Error al inicializar datos por defecto: ", e);
        }
    }

}
