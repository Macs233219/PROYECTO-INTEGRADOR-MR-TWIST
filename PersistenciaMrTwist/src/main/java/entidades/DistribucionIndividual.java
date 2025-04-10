/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author marlon
 */
@Entity
public class DistribucionIndividual extends SalidaInventario implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "distribucion_total_id")
    private DistribucionTotal distribucionTotal;

    public DistribucionIndividual() {
    }
    
    public DistribucionIndividual(Producto producto, int cantidad, Date fecha, Usuario usuario, DistribucionTotal distribucionTotal) {
        super(producto, cantidad, fecha, usuario);
        this.distribucionTotal = distribucionTotal;
    }

    public DistribucionTotal getDistribucionTotal() {
        return distribucionTotal;
    }

    public void setDistribucionTotal(DistribucionTotal distribucionTotal) {
        this.distribucionTotal = distribucionTotal;
    }

    @Override
    public String toString() {
        return "DistribucionIndividual{" + "distribucionTotal=" + distribucionTotal + '}';
    }
    
}
