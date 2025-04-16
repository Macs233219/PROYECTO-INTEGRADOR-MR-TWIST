/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocioFachada;

import InterfacesFachada.UsuarioFachada;
import daos.UsuarioJpaController;
import daos.exceptions.NonexistentEntityException;
import entidades.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UsuarioFachadaImpl implements UsuarioFachada{
    
    private final UsuarioJpaController usuarioJpaController;

    public UsuarioFachadaImpl() {
        this.usuarioJpaController = new UsuarioJpaController();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        this.usuarioJpaController.create(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        try {
            this.usuarioJpaController.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        try {
            this.usuarioJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioFachadaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario consultarUsuario(Long id) {
        return this.usuarioJpaController.findUsuario(id);
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        return this.usuarioJpaController.findUsuarioEntities();
    }
    
}
