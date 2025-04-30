package pruebas;

import InterfacesFachada.EntradaInventarioFachada;
import InterfacesFachada.MantenimientoFachada;
import InterfacesFachada.MaquinaFachada;
import daos.EntradaInventarioJpaController;
import daos.ProductoJpaController;
import daos.UsuarioJpaController;
import entidades.EntradaInventario;
import entidades.Producto;
import entidades.Usuario;
import InterfacesFachada.ProductoFachada;
import InterfacesFachada.SucursalFachada;
import entidades.Mantenimiento;
import entidades.Maquina;
import entidades.Sucursal;
import java.util.Date;
import java.util.List;
import negocioFachada.EntradaInventarioFachadaImpl;
import negocioFachada.MantenimientoFachadaImpl;
import negocioFachada.MaquinaFachadaImpl;
import negocioFachada.ProductoFachadaImpl;
import negocioFachada.SucursalFachadaImpl;

public class AccesoADatos {

    public static void main(String[] args) {
 // Instancias de fachadas (aquí deberías usar las clases concretas, por ejemplo SucursalFachadaImpl)
        SucursalFachada sucursalFachada = new SucursalFachadaImpl();
        MaquinaFachada maquinaFachada = new MaquinaFachadaImpl();
        MantenimientoFachada mantenimientoFachada = new MantenimientoFachadaImpl();

        // Prueba: Crear y guardar una sucursal
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Centro");
        sucursal.setCiudad("Av. Principal #123");
        sucursalFachada.guardarSucursal(sucursal);

        // Consultar y mostrar sucursales
        List<Sucursal> sucursales = sucursalFachada.consultarSucursales();
        System.out.println("Sucursales registradas:");
        for (Sucursal s : sucursales) {
            System.out.println(s.getId() + ": " + s.getNombre());
        }

        // Prueba: Crear y guardar una máquina
        Maquina maquina = new Maquina();
        maquinaFachada.guardarMaquina(maquina);

        // Prueba: Crear y guardar mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setDescripcion("Mantenimiento preventivo");
        mantenimiento.setFecha(new Date());
        mantenimiento.setMaquina(maquina); // Asignar a la máquina creada
        mantenimientoFachada.guardarMantenimiento(mantenimiento);

        // Consultar mantenimientos
        List<Mantenimiento> mantenimientos = mantenimientoFachada.consultarMantenimientos();
        System.out.println("Mantenimientos registrados:");
        for (Mantenimiento m : mantenimientos) {
            System.out.println(m.getId() + ": " + m.getDescripcion());
        }
    }
}
