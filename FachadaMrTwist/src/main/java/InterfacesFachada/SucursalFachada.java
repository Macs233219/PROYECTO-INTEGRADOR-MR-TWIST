/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Sucursal;
import java.util.List;

/**
 *
 * @author user
 */
public interface SucursalFachada {
    void guardarSucursal(Sucursal sucursal);
    void actualizarSucursal(Sucursal sucursal);
    void eliminarSucursal(Long id);
    Sucursal consultarSucursal(Long id);
    List<Sucursal> consultarSucursales();
}
