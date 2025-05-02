/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.Merma;
import java.util.List;

/**
 *
 * @author user
 */
public interface MermaFachada {
    void guardarMerma(Merma merma);
    void actualizarMerma(Merma merma);
    void eliminarMerma(Long id);
    Merma consultarMerma(Long id);
    List<Merma> consultarMermas();
    List<Merma> consultarMermas(int maxResults, int firstResult);
    int contarMermas();
}
