/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.SucursalFachada;
import daos.SucursalJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Sucursal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SucursalFachadaImpl implements SucursalFachada {
    
     private final SucursalJpaController sucursalJpaController;

    public SucursalFachadaImpl() {
        this.sucursalJpaController = new SucursalJpaController();
    }

    @Override
    public void guardarSucursal(Sucursal sucursal) {
        this.sucursalJpaController.create(sucursal);
    }

    @Override
    public void actualizarSucursal(Sucursal sucursal) {
        try {
            this.sucursalJpaController.edit(sucursal);
        } catch (Exception ex) {
            Logger.getLogger(SucursalFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarSucursal(Long id) {
        try {
            this.sucursalJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SucursalFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Sucursal consultarSucursal(Long id) {
        return this.sucursalJpaController.findSucursal(id);
    }

    @Override
    public List<Sucursal> consultarSucursales() {
        return this.sucursalJpaController.findSucursalEntities();
    }
    
}
