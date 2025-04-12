/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pruebas;

import InterfacesFachada.EntradaInventarioFachada;
import daos.EntradaInventarioJpaController;
import daos.ProductoJpaController;
import daos.UsuarioJpaController;
import entidades.EntradaInventario;
import entidades.Producto;
import entidades.Usuario;
import InterfacesFachada.ProductoFachada;
//import negocioFachada.ProductoFachadaImpl;
import java.util.Date;
import negocioFachada.EntradaInventarioFachadaImpl;

/**
 *
 * @author marlon
 */
public class AccesoADatos {

    public static void main(String[] args) {
//        UsuarioJpaController usuario = new UsuarioJpaController();
        
//        Usuario u = new Usuario("Juanito cena", "test1", null,null);
        
//        usuario.create(u);
//        
//          ProductoJpaController productoC = new ProductoJpaController();

//        ProductoFachada fachadaProducto = new ProductoFachadaImpl();
//        Producto producto = new Producto("Nieve de vainilla", "Nieve de vainilla papu", 100.00, 10,0,null,null);
//        fachadaProducto.guardarProducto(producto);
//        productoC.create(p);
//        
//        
//        EntradaInventarioJpaController entradaInventario = new EntradaInventarioJpaController();
//        
//        EntradaInventario entradaIn = new EntradaInventario(p,10,new Date(),u);
//        
//        entradaInventario.create(entradaIn);

//        new EntradaInventarioFachadaImpl().eliminarEntradaInventario(1L);

        for (EntradaInventario entradaInventario: new EntradaInventarioFachadaImpl().consultarEntradasInventario()) {
            System.out.println(entradaInventario);
        }

    }
}
