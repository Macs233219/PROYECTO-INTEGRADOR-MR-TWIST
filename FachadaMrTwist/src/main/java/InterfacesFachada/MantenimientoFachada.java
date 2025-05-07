/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Mantenimiento;
import java.util.List;

/**
 *
 * @author user
 */
public interface MantenimientoFachada {
    void guardarMantenimiento(Mantenimiento mantenimiento);
    void actualizarMantenimiento(Mantenimiento mantenimiento);
    void eliminarMantenimiento(Long id);
    Mantenimiento consultarMantenimiento(Long id);
    List<Mantenimiento> consultarMantenimientos();
    List<Mantenimiento> consultarMantenimientos(int maxResults, int firstResult);
    int contarMantenimientos();
}
