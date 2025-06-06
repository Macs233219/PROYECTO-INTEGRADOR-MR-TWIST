/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Producto;
import java.util.List;

/**
 *
 * @author marlon
 */
public interface ProductoFachada {
    void guardarProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Long id);
    Producto consultarProducto(Long id);
    List<Producto> consultarProductos();
    List<Producto> consultarProductos(int maxResults, int firstResult);
    int contarProductos();

}
