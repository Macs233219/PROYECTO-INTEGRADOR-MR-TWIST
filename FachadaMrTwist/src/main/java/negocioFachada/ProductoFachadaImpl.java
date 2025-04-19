/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.ProductoFachada;
import daos.ProductoJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Producto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marlon
 */
public class ProductoFachadaImpl implements ProductoFachada {

    private final ProductoJpaController productoJpaController;

    public ProductoFachadaImpl() {
        this.productoJpaController = new ProductoJpaController();
    }

    @Override
    public void guardarProducto(Producto producto) {
        this.productoJpaController.create(producto);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        try {
            this.productoJpaController.edit(producto);
        } catch (Exception ex) {
            Logger.getLogger(ProductoFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        try {
            this.productoJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProductoFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Producto consultarProducto(Long id) {
        return this.productoJpaController.findProducto(id);
    }

    @Override
    public List<Producto> consultarProductos() {
        return this.productoJpaController.findProductoEntities();
    }

    @Override
    public List<Producto> consultarProductos(int maxResults, int firstResult) {
        return this.productoJpaController.findProductoEntities(maxResults, firstResult);
    }

    @Override
    public int contarProductos() {
        return this.productoJpaController.getProductoCount();
    }

}
