package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class VencimientoPuntosDAO {
	
        @PersistenceContext(unitName="puntosPU")
	private EntityManager em;
	
	public void agregar(VencimientoPuntos entity) {
		this.em.persist(entity);
	}
	
	public List<VencimientoPuntos> listar () {
		Query q = this.em.createQuery("select p from VencimientoPuntos p");
		return (List<VencimientoPuntos>) q.getResultList();
	}
}
