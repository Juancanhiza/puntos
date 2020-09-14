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
        Cliente c = em.find(Cliente.class, clienteId);
        this.em.remove(c);
    }

    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
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
        LOGGER.info("CUMPLE STRING: {}", cumple);
        Date fechaCumple = new SimpleDateFormat("dd/MM/yyyy").parse(cumple);
         LOGGER.info("FECHA CUMPLEÑOS: {}", fechaCumple);
        Query q = this.em.createNamedQuery("Cliente.byCumple");
        
        
        List<Cliente> todos = (List<Cliente>) q.getResultList();
        
        LOGGER.info("LISTA DE TODOS: [{}]", todos);
        List<Cliente> listaCumple = new LinkedList<Cliente>();
        Date fechaNacimiento;
        
        for (Cliente cliente : todos) {
             fechaNacimiento = cliente.getFechaNacimiento();
             
            String fechaNacimientoString = new SimpleDateFormat("dd/MM").format(fechaNacimiento);
            String fechaCumpleString = new SimpleDateFormat("dd/MM").format(fechaCumple);
            if (fechaNacimiento != null && fechaCumple != null 
                    && fechaNacimientoString.equals(fechaCumpleString)){
                listaCumple.add(cliente);
            }
        }
        LOGGER.info("LISTA DE CUMPLEAÑOS: [{}]", listaCumple);
        
        return listaCumple;
    }
    
}
