package py.com.progweb.prueba.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.model.Cliente;

@Stateless
public class ClienteBean {
    private static final Logger LOGGER = LogManager.getLogger(ClienteBean.class);
    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(Cliente entity) {
        this.em.persist(entity);
    }

    public List<Cliente> listar() {
        Query q = this.em.createNamedQuery("Cliente.all");
        return (List<Cliente>) q.getResultList();
    }

    public void eliminar(Long clienteId) {
        this.em.getTransaction().begin();
        Cliente c = em.find(Cliente.class, clienteId);
        this.em.remove(c);
        this.em.getTransaction().commit();
    }

    public void actualizar(Cliente cliente) {
        this.em.getTransaction().begin();
        this.em.merge(cliente);
        this.em.getTransaction().commit();
    }
    
    public List<Cliente> listarByNombre(String nombreCliente) {
        Query q = this.em.createNamedQuery("Cliente.byNombre");
        return (List<Cliente>) q
                .setParameter("nombreCliente", '%' + nombreCliente + '%')
                .getResultList();
    }
    
    public List<Cliente> listarByApellido(String nombreApellido) {
        Query q = this.em.createNamedQuery("Cliente.byApellido");
        return (List<Cliente>) q
                .setParameter("nombreApellido", '%' + nombreApellido + '%')
                .getResultList();
    }
    
    public List<Cliente> listarByCumple(String cumple) throws ParseException {
        LOGGER.info("CUMPLE: {}", cumple);
        Date fechaCumple = new SimpleDateFormat("dd/MM/yyyy").parse(cumple);
        Query q = this.em.createNamedQuery("Cliente.byCumple");
        
        
        List<Cliente> todos = (List<Cliente>) q.getResultList();
        
        LOGGER.info("LISTA DE TODOS: [{}]", todos);
        List<Cliente> listaCumple = new LinkedList<Cliente>();
        Date fechaNacimiento = null;
        
        for (Cliente cliente : todos) {
             fechaNacimiento = cliente.getFechaNacimiento();
            if (fechaNacimiento != null && fechaCumple != null && fechaNacimiento.getMonth() == fechaCumple.getMonth() && fechaNacimiento.getDay() == fechaCumple.getDay()){
                listaCumple.add(cliente);
            }
        }
        LOGGER.info("LISTA DE CUMPLEAŅOS: [{}]", listaCumple);
        
        return listaCumple;
    }
    
}
