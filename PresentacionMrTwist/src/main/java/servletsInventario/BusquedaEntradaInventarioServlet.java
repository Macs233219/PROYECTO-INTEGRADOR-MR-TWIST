/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsInventario;

import InterfacesFachada.EntradaInventarioFachada;
import entidades.EntradaInventario;
import negocioFachada.EntradaInventarioFachadaImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Chris
 */
public class BusquedaEntradaInventarioServlet extends HttpServlet {

    // Instanciar fachada
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EntradaInventarioFachada entradaInventarioFachada = new EntradaInventarioFachadaImpl();
            
            int pageSize = 10;
            int paginaActual = 1;

            // Obtener número de página
            String paginaParam = request.getParameter("page");
            if (paginaParam != null && !paginaParam.isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e) {
                    paginaActual = 1;
                }
            }

            // Obtener término de búsqueda
            final String busqueda = request.getParameter("busqueda");

            // Obtener las entradas de inventario filtradas por el nombre del producto
            List<EntradaInventario> todasLasEntradas = entradaInventarioFachada.consultarEntradasInventario();
            List<EntradaInventario> entradasFiltradas = todasLasEntradas.stream()
                .filter(e -> e.getProducto().getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                .collect(Collectors.toList());

            int totalEntradas = entradasFiltradas.size();
            int totalPaginas = (int) Math.ceil((double) totalEntradas / pageSize);

            if (paginaActual < 1) paginaActual = 1;
            if (paginaActual > totalPaginas && totalPaginas > 0) paginaActual = totalPaginas;

            int primerResultado = (paginaActual - 1) * pageSize;
            int ultimoResultado = Math.min(primerResultado + pageSize, totalEntradas);

            List<EntradaInventario> entradasPagina = entradasFiltradas.subList(primerResultado, ultimoResultado);

            // Atributos para el JSP
            request.setAttribute("entradasInventario", entradasPagina);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("busqueda", busqueda);

            // Redirige a la vista
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventario/consultarEntradas.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/views/inventario/menuInventario.jsp");
        }
    }
}
