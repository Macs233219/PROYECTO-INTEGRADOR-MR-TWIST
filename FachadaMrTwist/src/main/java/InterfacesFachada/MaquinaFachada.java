/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Maquina;
import java.util.List;

/**
 *
 * @author user
 */
public interface MaquinaFachada {
    void guardarMaquina(Maquina maquina);
    void actualizarMaquina(Maquina maquina);
    void eliminarMaquina(Long id);
    Maquina consultarMaquina(Long id);
    List<Maquina> consultarMaquinas();
    List<Maquina> consultarMaquinas(int maxResults, int firstResult);
    int contarMaquinas();
}
