package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.ConceptoPuntos;

@Stateless
public class ConceptoPuntosBean {

    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(ConceptoPuntos entity) {
        this.em.persist(entity);
    }

    public List<ConceptoPuntos> listar() {
        Query q = this.em.createNativeQuery("ConceptoPuntos.all");
        return (List<ConceptoPuntos>) q.getResultList();
    }

    public void eliminar(Long Concepto_PuntosId) {
        this.em.getTransaction().begin();
        ConceptoPuntos cp = em.find(ConceptoPuntos.class, Concepto_PuntosId);
        this.em.remove(cp);
        this.em.getTransaction().commit();
    }

    public void actualizar(ConceptoPuntos concepto_puntos) {
        this.em.getTransaction().begin();
        this.em.merge(concepto_puntos);
        this.em.getTransaction().commit();
    }
    
    public ConceptoPuntos getById(Integer id){
        ConceptoPuntos ret = this.em.find(ConceptoPuntos.class, id);
        return ret;
    }
}
