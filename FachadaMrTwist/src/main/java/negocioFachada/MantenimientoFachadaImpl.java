/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.MantenimientoFachada;
import daos.MantenimientoJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Mantenimiento;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MantenimientoFachadaImpl implements MantenimientoFachada{
    
    private final MantenimientoJpaController mantenimientoJpaController;

    public MantenimientoFachadaImpl() {
        this.mantenimientoJpaController = new MantenimientoJpaController();
    }

    @Override
    public void guardarMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimientoJpaController.create(mantenimiento);
    }

    @Override
    public void actualizarMantenimiento(Mantenimiento mantenimiento) {
        try {
            this.mantenimientoJpaController.edit(mantenimiento);
        } catch (Exception ex) {
            Logger.getLogger(MantenimientoFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarMantenimiento(Long id) {
        try {
            this.mantenimientoJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MantenimientoFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Mantenimiento consultarMantenimiento(Long id) {
        return this.mantenimientoJpaController.findMantenimiento(id);
    }

    @Override
    public List<Mantenimiento> consultarMantenimientos() {
        return this.mantenimientoJpaController.findMantenimientoEntities();
    }
    
}
