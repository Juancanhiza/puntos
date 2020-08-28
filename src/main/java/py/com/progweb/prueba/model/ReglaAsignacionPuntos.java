package py.com.progweb.prueba.model;

import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="regla_asignacion_puntos")
public class ReglaAsignacionPuntos {
    
    @Id
    @Column(name="id")
    @Basic(optional=false)
    @GeneratedValue(generator="reglaAsignacionPuntosSec", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="reglaAsignacionPuntosSec", sequenceName = "regla_asignacion_puntos_sec", allocationSize = 0)
    private Integer id;
     
    @Column(name="limite_inferior")
    @Basic(optional=false)
    private BigDecimal limiteInferior;
    
    @Column(name="limite_superior")
    @Basic(optional=false)
    private BigDecimal limiteSuperior;

    @Column(name="monto_equivalencia")
    @Basic(optional=false)
    private BigDecimal montoEquivalencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(BigDecimal limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public BigDecimal getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(BigDecimal montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }
    
}
