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
public class DistribucionTotal implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean estado;
    
    @OneToMany(mappedBy = "distribucionTotal", cascade = CascadeType.ALL)
    private List<DistribucionIndividual> distribucionesIndividuales;
    
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    public DistribucionTotal() {
    }

    public DistribucionTotal(boolean estado, List<DistribucionIndividual> distribucionesIndividuales, Sucursal sucursal) {
        this.estado = estado;
        this.distribucionesIndividuales = distribucionesIndividuales;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DistribucionIndividual> getDistribucionesIndividuales() {
        return distribucionesIndividuales;
    }

    public void setDistribucionesIndividuales(List<DistribucionIndividual> distribucionesIndividuales) {
        this.distribucionesIndividuales = distribucionesIndividuales;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "DistribucionTotal{" + "id=" + id + ", estado=" + estado + ", distribucionesIndividuales=" + distribucionesIndividuales + ", sucursal=" + sucursal + '}';
    }
    
}
