package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Cliente;

@Stateless
public class ClienteDAO {
	@PersistenceContext(unitName="puntosPU")
	private EntityManager em;
	
	public void agregar(Cliente entity) {
		this.em.persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar () {
		Query q = this.em.createQuery("select p from Cliente p");
		return (List<Cliente>) q.getResultList();
	}
}
