/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.DistribucionIndividual;
import java.util.List;

/**
 *
 * @author user
 */
public interface DistribucionIndividualFachada {
    void guardarDistribucionIndividual(DistribucionIndividual distribucionIndividual);
    void actualizarDistribucionIndividual(DistribucionIndividual distribucionIndividual);
    void eliminarDistribucionIndividual(Long id);
    DistribucionIndividual consultarDistribucionIndividual(Long id);
    List<DistribucionIndividual> consultarDistribucionesIndividuales();
}
