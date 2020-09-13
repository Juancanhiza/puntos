package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

@Stateless
public class UsoPuntosDetallesBean {

    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(UsoPuntosDetalle entity) {
        this.em.persist(entity);
    }

    public List<UsoPuntosDetalle> listar() {
        Query q = this.em.createNativeQuery("UsoPuntosDetalle.all");
        return (List<UsoPuntosDetalle>) q.getResultList();
    }

    public void eliminar(Long uso_puntos_detalleId) {
        this.em.getTransaction().begin();
        UsoPuntosDetalle uso = em.find(UsoPuntosDetalle.class, uso_puntos_detalleId);
        this.em.remove(uso);
        this.em.getTransaction().commit();
    }

    public void actualizar(UsoPuntosDetalle uso) {
        this.em.getTransaction().begin();
        this.em.merge(uso);
        this.em.getTransaction().commit();
    }

    void agregarDetallesDeCabecera(UsoPuntosCabecera cabecera) {
        
    }

}
