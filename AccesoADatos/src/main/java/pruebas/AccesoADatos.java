/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pruebas;

import daos.EntradaInventarioJpaController;
import daos.ProductoJpaController;
import daos.UsuarioJpaController;
import entidades.EntradaInventario;
import entidades.Producto;
import entidades.Usuario;
import java.util.Date;

/**
 *
 * @author marlon
 */
public class AccesoADatos {

    public static void main(String[] args) {
        UsuarioJpaController usuario = new UsuarioJpaController();
        
        ProductoJpaController productoC = new ProductoJpaController();
        
        Usuario u = new Usuario("Juanito cena", "test1", null,null);
        
//        usuario.create(u);
//        
        Producto p = new Producto("nieve chocolate", "Nieve de chocolate papu", 100.00, 10,0,null,null);
        
//        productoC.create(p);
        
        
        EntradaInventarioJpaController entradaInventario = new EntradaInventarioJpaController();
        
        EntradaInventario entradaIn = new EntradaInventario(null,10,new Date(),null);
        
        entradaInventario.create(entradaIn);
    }
}
