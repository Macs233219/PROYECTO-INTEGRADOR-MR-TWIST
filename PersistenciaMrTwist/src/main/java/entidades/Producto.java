/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marlon
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    
    @Lob
    private String descripcion;
    
    private double precioUnitario;
    private int cantidadTotal;
    private int cantidadEscasez;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<EntradaInventario> entradasInventario;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<SalidaInventario> salidasInventario;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, double precioUnitario, int cantidadTotal, int cantidadEscasez, List<EntradaInventario> entradasInventario, List<SalidaInventario> salidasInventario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadTotal = cantidadTotal;
        this.cantidadEscasez = cantidadEscasez;
        this.entradasInventario = entradasInventario;
        this.salidasInventario = salidasInventario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadEscasez() {
        return cantidadEscasez;
    }

    public void setCantidadEscasez(int cantidadEscasez) {
        this.cantidadEscasez = cantidadEscasez;
    }

    public List<EntradaInventario> getEntradasInventario() {
        return entradasInventario;
    }

    public void setEntradasInventario(List<EntradaInventario> entradasInventario) {
        this.entradasInventario = entradasInventario;
    }

    public List<SalidaInventario> getSalidasInventario() {
        return salidasInventario;
    }

    public void setSalidasInventario(List<SalidaInventario> salidasInventario) {
        this.salidasInventario = salidasInventario;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + ", cantidadTotal=" + cantidadTotal + ", cantidadEscasez=" + cantidadEscasez + ", entradasInventario=" + entradasInventario + ", salidasInventario=" + salidasInventario + '}';
    }
    
}
