/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachadas;

import entidades.Producto;
import java.util.List;

/**
 *
 * @author marlon
 */
public interface ProductoFachada {
    void crearProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Long id);
    Producto consultarProducto(Long id);
    List<Producto> consultarProductos();
}
