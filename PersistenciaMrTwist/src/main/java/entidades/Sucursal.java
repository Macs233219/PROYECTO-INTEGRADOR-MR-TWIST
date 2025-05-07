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

/**
 *
 * @author marlon
 */
@Entity
public class Sucursal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private String nombreEncargado;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<DistribucionTotal> distribucionesTotales;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<Maquina> maquinas;

    public Sucursal() {
    }

    public Sucursal(String nombre, String ciudad, String nombreEncargado, List<DistribucionTotal> distribucionesTotales, List<Maquina> maquinas) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.nombreEncargado = nombreEncargado;
        this.distribucionesTotales = distribucionesTotales;
        this.maquinas = maquinas;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public List<DistribucionTotal> getDistribucionesTotales() {
        return distribucionesTotales;
    }

    public void setDistribucionesTotales(List<DistribucionTotal> distribucionesTotales) {
        this.distribucionesTotales = distribucionesTotales;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", nombreEncargado=" + nombreEncargado + '}';
    }

}
