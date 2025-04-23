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
public class Maquina implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private EstadoMaquina estado;
    
    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL)
    private List<Mantenimiento> mantenimientos;

    public Maquina() {
    }

     public Maquina(Long id) {
        this.id = id;
     
    }
     
    public Maquina(Long id, EstadoMaquina estado, List<Mantenimiento> mantenimientos) {
        this.id = id;
        this.estado = estado;
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

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", estado=" + estado + ", mantenimientos=" + mantenimientos + '}';
    }
    
}
