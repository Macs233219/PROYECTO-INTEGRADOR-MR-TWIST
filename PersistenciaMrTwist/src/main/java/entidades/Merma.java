/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 *
 * @author marlon
 */
@Entity
public class Merma extends SalidaInventario implements Serializable {
    
    @Lob
    private String motivo;

    public Merma() {
    }
    
    public Merma(Producto producto, int cantidad, Date fecha, Usuario usuario) {
        super(producto, cantidad, fecha, usuario);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
}
