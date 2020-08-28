package py.com.progweb.prueba.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="vencimiento_puntos")
public class VencimientoPuntos {
    @Id
    @Column(name="id")
    @Basic(optional=false)
    @GeneratedValue(generator="vencimientoPuntosSec", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="vencimientoPuntosSec", sequenceName = "vencimiento_puntos_sec", allocationSize = 0)
    private Integer id;
    
    @Column(name="fecha_inicio_validez")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(name="fecha_fin_validez")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    @Column(name="dias_duracion")
    @Basic(optional=false)
    private Integer diasDuracion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(Integer diasDuracion) {
        this.diasDuracion = diasDuracion;
    }
  
}
