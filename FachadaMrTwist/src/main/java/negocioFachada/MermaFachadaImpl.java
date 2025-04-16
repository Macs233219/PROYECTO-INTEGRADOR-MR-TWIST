/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.MermaFachada;
import daos.MermaJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Merma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MermaFachadaImpl implements MermaFachada{
    
    
    private final MermaJpaController mermaJpaController;

    public MermaFachadaImpl() {
        this.mermaJpaController = new MermaJpaController();
    }

    @Override
    public void guardarMerma(Merma merma) {
        this.mermaJpaController.create(merma);
    }

    @Override
    public void actualizarMerma(Merma merma) {
        try {
            this.mermaJpaController.edit(merma);
        } catch (Exception ex) {
            Logger.getLogger(MermaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarMerma(Long id) {
        try {
            this.mermaJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MermaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Merma consultarMerma(Long id) {
        return this.mermaJpaController.findMerma(id);
    }

    @Override
    public List<Merma> consultarMermas() {
        return this.mermaJpaController.findMermaEntities();
    }
}
