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
@Table(name="cliente")
public class Cliente {
    
    @Id
    @Column(name="id")
    @Basic(optional=false)
    @GeneratedValue(generator="clienteSec", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="clienteSec", sequenceName = "cliente_sec", allocationSize = 0)
    private Integer id;

    @Column(name="nombre", length = 50)
    @Basic(optional=false)
    private String nombre;

    @Column(name="apellido", length = 50)
    @Basic(optional=false)
    private String apellido;

    @Column(name="numero_documento")
    @Basic(optional=false)
    private Integer numeroDocumento;

    @Column(name="tipo_documento", length = 50)
    @Basic(optional=false)
    private String tipoDocumento;

    @Column(name="nacionalidad", length = 50)
    private String nacionalidad;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="telefono", length = 50)
    private String telefono;

    @Column(name="fecha_nacimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
//    @JsonBackReference
//    private List <BolsaPuntos> bolsas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fechaNacimiento;
    }

    public void setFecha_nacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
//    public List<BolsaPuntos> getBolsas() {
//        return bolsas;
//    }
//
//    public void setBolsas(List<BolsaPuntos> bolsas) {
//        this.bolsas = bolsas;
//    }
   
}
