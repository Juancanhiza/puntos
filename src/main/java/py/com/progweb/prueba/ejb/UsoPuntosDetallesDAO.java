package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.UsoPuntosDetalle;

@Stateless
public class UsoPuntosDetallesDAO {
	
        @PersistenceContext(unitName="puntosPU")
	private EntityManager em;
	
	public void agregar(UsoPuntosDetalle entity) {
		this.em.persist(entity);
	}
	
	public List<UsoPuntosDetalle> listar () {
		Query q = this.em.createQuery("select p from UsoPuntosDetalle p");
		return (List<UsoPuntosDetalle>) q.getResultList();
	}
}
