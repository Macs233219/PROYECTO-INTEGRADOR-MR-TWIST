/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.MaquinaFachada;
import daos.MaquinaJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Maquina;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MaquinaFachadaImpl implements MaquinaFachada {

    private final MaquinaJpaController maquinaJpaController;

    public MaquinaFachadaImpl() {
        this.maquinaJpaController = new MaquinaJpaController();
    }

    @Override
    public void guardarMaquina(Maquina maquina) {
        this.maquinaJpaController.create(maquina);
    }

    @Override
    public void actualizarMaquina(Maquina maquina) {
        try {
            this.maquinaJpaController.edit(maquina);
        } catch (Exception ex) {
            Logger.getLogger(MaquinaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarMaquina(Long id) {
        try {
            this.maquinaJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MaquinaFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Maquina consultarMaquina(Long id) {
        return this.maquinaJpaController.findMaquina(id);
    }

    @Override
    public List<Maquina> consultarMaquinas() {
        return this.maquinaJpaController.findMaquinaEntities();
    }

    @Override
    public List<Maquina> consultarMaquinas(int maxResults, int firstResult) {
        return this.maquinaJpaController.findMaquinaEntities(maxResults, firstResult);
    }

    @Override
    public List<Maquina> buscarMaquinaPorSucursalId(Long sucursalId) {
        return this.maquinaJpaController.buscarPorSucursalId(sucursalId);
    }

    @Override
    public int contarMaquinas() {
        return this.maquinaJpaController.getMaquinaCount();
    }

}
