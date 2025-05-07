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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author marlon
 */
@Entity
public class Maquina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EstadoMaquina estado;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL)
    private List<Mantenimiento> mantenimientos;

    public Maquina() {
    }

    public Maquina(Long id) {
        this.id = id;

    }

    public Maquina(EstadoMaquina estado, Sucursal sucursal, List<Mantenimiento> mantenimientos) {
        this.estado = estado;
        this.sucursal = sucursal;
        this.mantenimientos = mantenimientos;
    }

    public Maquina(Long id, EstadoMaquina estado, Sucursal sucursal, List<Mantenimiento> mantenimientos) {
        this.id = id;
        this.estado = estado;
        this.sucursal = sucursal;
        this.mantenimientos = mantenimientos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoMaquina getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaquina estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", estado=" + estado + ", sucursal=" + sucursal + ", mantenimientos=" + mantenimientos + '}';
    }

}
