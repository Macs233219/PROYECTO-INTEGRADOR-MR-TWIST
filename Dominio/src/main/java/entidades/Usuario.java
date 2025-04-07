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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marlon
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contrasena;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EntradaInventario> entradasInventario;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<SalidaInventario> salidasInventario;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, List<EntradaInventario> entradasInventario, List<SalidaInventario> salidasInventario) {
        this.nombre = nombre;
        this.contrasena = contrasena;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", contrasena=" + contrasena + ", entradasInventario=" + entradasInventario + ", salidasInventario=" + salidasInventario + '}';
    }
    
}
