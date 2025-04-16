/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface UsuarioFachada {
    void guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    Usuario consultarUsuario(Long id);
    List<Usuario> consultarUsuarios();
}
