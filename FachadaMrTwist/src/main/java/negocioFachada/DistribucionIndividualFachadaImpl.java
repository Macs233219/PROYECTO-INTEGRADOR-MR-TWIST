/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.DistribucionIndividualFachada;
import daos.DistribucionIndividualJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.DistribucionIndividual;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DistribucionIndividualFachadaImpl implements DistribucionIndividualFachada{
    
    private final DistribucionIndividualJpaController distribucionIndividualJpaController;

    public DistribucionIndividualFachadaImpl() {
        this.distribucionIndividualJpaController = new DistribucionIndividualJpaController();
    }

    @Override
    public void guardarDistribucionIndividual(DistribucionIndividual distribucionIndividual) {
        try {
            distribucionIndividualJpaController.create(distribucionIndividual);
        } catch (Exception ex) {
            Logger.getLogger(DistribucionIndividualFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarDistribucionIndividual(DistribucionIndividual distribucionIndividual) {
        try {
            distribucionIndividualJpaController.edit(distribucionIndividual);
        } catch (Exception ex) {
            Logger.getLogger(DistribucionIndividualFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarDistribucionIndividual(Long id) {
        try {
            distribucionIndividualJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DistribucionIndividualFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DistribucionIndividual consultarDistribucionIndividual(Long id) {
        return distribucionIndividualJpaController.findDistribucionIndividual(id);
    }

    @Override
    public List<DistribucionIndividual> consultarDistribucionesIndividuales() {
        return distribucionIndividualJpaController.findDistribucionIndividualEntities();
    }
}
