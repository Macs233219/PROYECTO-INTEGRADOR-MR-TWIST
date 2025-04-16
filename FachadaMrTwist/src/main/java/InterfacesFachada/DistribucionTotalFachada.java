/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.DistribucionTotal;
import java.util.List;

/**
 *
 * @author user
 */
public interface DistribucionTotalFachada {
    void guardarDistribucionTotal(DistribucionTotal distribucionTotal);
    void actualizarDistribucionTotal(DistribucionTotal distribucionTotal);
    void eliminarDistribucionTotal(Long id);
    DistribucionTotal consultarDistribucionTotal(Long id);
    List<DistribucionTotal> consultarDistribucionesTotales();
}
