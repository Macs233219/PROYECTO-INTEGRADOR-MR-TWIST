/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesFachada;

import entidades.EntradaInventario;
import java.util.List;

/**
 *
 * @author marlon
 */
public interface EntradaInventarioFachada {
    void guardarEntradaInventario(EntradaInventario entradaInventario);
    void actualizarEntradaInventario(EntradaInventario entradaInventario);
    void eliminarEntradaInventario(Long id);
    EntradaInventario consultarEntradaInventario(Long id);
    List<EntradaInventario> consultarEntradasInventario();
    List<EntradaInventario> consultarEntradasInventario(int offset, int limit);
    int contarEntradasInventario();
}
