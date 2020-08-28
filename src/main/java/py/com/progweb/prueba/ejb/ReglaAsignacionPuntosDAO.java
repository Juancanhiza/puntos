package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.ReglaAsignacionPuntos;

@Stateless
public class ReglaAsignacionPuntosDAO {
    
    @PersistenceContext(unitName="puntosPU")
    private EntityManager em;
    
    public void agregar(ReglaAsignacionPuntos entity) {
		this.em.persist(entity);
	}
	
	public List<ReglaAsignacionPuntos> listar () {
		Query q = this.em.createQuery("select p from ReglaAsignacionPuntos p");
		return (List<ReglaAsignacionPuntos>) q.getResultList();
	}
}
