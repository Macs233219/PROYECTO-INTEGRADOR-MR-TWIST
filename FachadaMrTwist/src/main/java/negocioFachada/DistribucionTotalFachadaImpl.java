/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.DistribucionTotalFachada;
import daos.DistribucionTotalJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.DistribucionTotal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DistribucionTotalFachadaImpl implements DistribucionTotalFachada{
  
    private final DistribucionTotalJpaController distribucionTotalJpaController;

    public DistribucionTotalFachadaImpl() {
        this.distribucionTotalJpaController = new DistribucionTotalJpaController();
    }

    @Override
    public void guardarDistribucionTotal(DistribucionTotal distribucionTotal) {
        try {
            distribucionTotalJpaController.create(distribucionTotal);
        } catch (Exception ex) {
            Logger.getLogger(DistribucionTotalFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarDistribucionTotal(DistribucionTotal distribucionTotal) {
        try {
            distribucionTotalJpaController.edit(distribucionTotal);
        } catch (Exception ex) {
            Logger.getLogger(DistribucionTotalFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarDistribucionTotal(Long id) {
        try {
            distribucionTotalJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DistribucionTotalFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DistribucionTotal consultarDistribucionTotal(Long id) {
        return distribucionTotalJpaController.findDistribucionTotal(id);
    }

    @Override
    public List<DistribucionTotal> consultarDistribucionesTotales() {
        return distribucionTotalJpaController.findDistribucionTotalEntities();
    }

}
