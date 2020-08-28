package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.ConceptoPuntos;

@Stateless
public class ConceptoPuntosDAO {
    
    @PersistenceContext(unitName="puntosPU")
    private EntityManager em;
    
    public void agregar(ConceptoPuntos entity) {
		this.em.persist(entity);
	}
	
	public List<ConceptoPuntos> listar () {
		Query q = this.em.createQuery("select p from ConceptoPuntos p");
		return (List<ConceptoPuntos>) q.getResultList();
	}
}
