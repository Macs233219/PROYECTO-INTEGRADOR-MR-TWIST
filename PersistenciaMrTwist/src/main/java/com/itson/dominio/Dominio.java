/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.itson.dominio;

import daos.EntradaInventarioJpaController;
import daos.ProductoJpaController;
import entidades.EntradaInventario;
import entidades.Producto;
import java.util.Date;

/**
 *
 * @author marlon
 */
public class Dominio {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        EntradaInventarioJpaController entradaInventarioDao = new EntradaInventarioJpaController();
        ProductoJpaController productoDao = new ProductoJpaController();
        
        Producto producto = productoDao.findProducto(3L);
        
        System.out.println(producto);
        
        entradaInventarioDao.create(new EntradaInventario(producto, 0, new Date(), null));
        
        for (EntradaInventario entradaInventario: entradaInventarioDao.findEntradaInventarioEntities()) {
            System.out.println(entradaInventario);
        }
        
    }
}
