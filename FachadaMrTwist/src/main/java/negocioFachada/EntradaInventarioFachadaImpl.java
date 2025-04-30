/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.EntradaInventarioFachada;
import daos.EntradaInventarioJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.EntradaInventario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author marlon
 */
public class EntradaInventarioFachadaImpl implements EntradaInventarioFachada {

    private final EntradaInventarioJpaController entradaInventarioJpaController;

    public EntradaInventarioFachadaImpl() {
        this.entradaInventarioJpaController = new EntradaInventarioJpaController();
    }

    @Override
    public void guardarEntradaInventario(EntradaInventario entradaInventario) {
        this.entradaInventarioJpaController.create(entradaInventario);
    }

    @Override
    public void actualizarEntradaInventario(EntradaInventario entradaInventario) {
        try {
            this.entradaInventarioJpaController.edit(entradaInventario);
        } catch (Exception ex) {
            Logger.getLogger(EntradaInventarioFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarEntradaInventario(Long id) {
        try {
            this.entradaInventarioJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EntradaInventarioFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EntradaInventario consultarEntradaInventario(Long id) {
        return this.entradaInventarioJpaController.findEntradaInventario(id);
    }

    @Override
    public List<EntradaInventario> consultarEntradasInventario() {
        return this.entradaInventarioJpaController.findEntradaInventarioEntities();
    }

    @Override
    public List<EntradaInventario> consultarEntradasInventario(int offset, int limit) {
        return entradaInventarioJpaController.findEntradaInventarioEntities(limit, offset);
    }

    @Override
    public int contarEntradasInventario() {
        return entradaInventarioJpaController.getEntradaInventarioCount();
    }


}
