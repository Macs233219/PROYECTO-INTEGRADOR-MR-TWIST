package pruebas;

import InterfacesFachada.EntradaInventarioFachada;
import daos.EntradaInventarioJpaController;
import daos.ProductoJpaController;
import daos.UsuarioJpaController;
import entidades.EntradaInventario;
import entidades.Producto;
import entidades.Usuario;
import InterfacesFachada.ProductoFachada;
import java.util.Date;
import negocioFachada.EntradaInventarioFachadaImpl;
import negocioFachada.ProductoFachadaImpl;

public class AccesoADatos {

    public static void main(String[] args) {
        UsuarioJpaController usuarioController = new UsuarioJpaController();
        
        Usuario u = new Usuario("Juanito cena", "test1", null, null);
        usuarioController.create(u);
        
        ProductoJpaController productoC = new ProductoJpaController();
        ProductoFachada fachadaProducto = new ProductoFachadaImpl();
        Producto producto = new Producto("Nieve de vainilla", "Nieve de vainilla papu", 100.00, 10, 0, null, null);
        
        // Guardar el producto a través de la fachada
        fachadaProducto.guardarProducto(producto);
        
        // Si la fachada ya maneja la persistencia, no es necesario llamar a productoC.create
        // productoC.create(producto); // Eliminar esta línea si la fachada maneja la persistencia
        
        EntradaInventarioJpaController entradaInventarioController = new EntradaInventarioJpaController();
        EntradaInventario entradaIn = new EntradaInventario(producto, 10, new Date(), u);
        entradaInventarioController.create(entradaIn);

        // Eliminar entrada de inventario con ID 1L
        new EntradaInventarioFachadaImpl().eliminarEntradaInventario(1L);

        // Consultar y mostrar entradas de inventario
        for (EntradaInventario entrada : new EntradaInventarioFachadaImpl().consultarEntradasInventario()) {
            System.out.println(entrada);
        }
    }
}
