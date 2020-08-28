package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Stateless
public class UsoPuntosCabeceraDAO {
	
        @PersistenceContext(unitName="puntosPU")
	private EntityManager em;
	
	public void agregar(UsoPuntosCabecera entity) {
		this.em.persist(entity);
	}
	
	public List<UsoPuntosCabecera> listar () {
		Query q = this.em.createQuery("select p from UsoPuntosCabecera p");
		return (List<UsoPuntosCabecera>) q.getResultList();
	}
}
