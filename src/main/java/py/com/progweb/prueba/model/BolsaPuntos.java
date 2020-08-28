package py.com.progweb.prueba.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="bolsa_puntos")
public class BolsaPuntos {
    @Id
    @Column(name="id")
    @Basic(optional=false)
    @GeneratedValue(generator="bolsaPuntosSec", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="bolsaPuntosSec", sequenceName = "bolsa_puntos_sec", allocationSize = 0)
    private Integer id;
    
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional= false)
//    @JsonManagedReference
    private Cliente cliente;
    
    @Column(name="fecha_asignacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Basic(optional=false)
    private Date fechaAsignacion;
    
    @Column(name="fecha_caducidad")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Basic(optional=false)
    private Date fechaCaducidad;
    
    @Column(name="puntos_utilizados")
    @Basic(optional=false)
    private Integer puntosAsignados;
    
    @Column(name="puntos_asignados")
    @Basic(optional=false)
    private Integer puntosUtilizados;
    
    @Column(name="puntos_saldo")
    @Basic(optional=false)
    private Integer puntosSaldo;
    
    @Column(name="monto_operacion")
    @Basic(optional=false)
    private BigDecimal montoOperacion;
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bolsa")
//    @JsonBackReference
//    private List<UsoPuntosDetalle> usoPuntos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getPuntosAsignados() {
        return puntosAsignados;
    }

    public void setPuntosAsignados(Integer puntosAsignados) {
        this.puntosAsignados = puntosAsignados;
    }

    public Integer getPuntosUtilizados() {
        return puntosUtilizados;
    }

    public void setPuntosUtilizados(Integer puntosUtilizados) {
        this.puntosUtilizados = puntosUtilizados;
    }

    public BigDecimal getMontoOperacion() {
        return montoOperacion;
    }

    public Integer getPuntosSaldo() {
        return puntosSaldo;
    }

    public void setPuntosSaldo(Integer puntosSaldo) {
        this.puntosSaldo = puntosSaldo;
    }

    public void setMontoOperacion(BigDecimal montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

//    public List<UsoPuntosDetalle> getUsoPuntos() {
//        return usoPuntos;
//    }
//
//    public void setUsoPuntos(List<UsoPuntosDetalle> usoPuntos) {
//        this.usoPuntos = usoPuntos;
//    }
    
}
