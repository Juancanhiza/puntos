package py.com.progweb.prueba.ejb;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

@Stateless
public class BolsaPuntosDAO {

    private static final Logger LOGGER = LogManager.getLogger(BolsaPuntosDAO.class);

    @Inject
    private VencimientoPuntosDAO vencimientoBean;

    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(BolsaPuntos entity) throws Exception {
        if (entity == null) {
            throw new Exception();
        }

        this.em.persist(entity);
    }

    public List<BolsaPuntos> listar() {
        Query q = this.em.createNamedQuery("BolsaPuntos.all");
        return (List<BolsaPuntos>) q.getResultList();
    }

    public void eliminar(Integer bolsa_puntosId) {
        BolsaPuntos bolsa = em.find(BolsaPuntos.class, bolsa_puntosId);
        this.em.remove(bolsa);
    }

    public void actualizar(BolsaPuntos bolsa) {
        this.em.merge(bolsa);
    }

    public List<BolsaPuntos> listarByCliente(Integer idCliente) {
        Query q = this.em.createNamedQuery("BolsaPuntos.byCliente");
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        return (List<BolsaPuntos>) q
                .setParameter("idCliente", cliente)
                .getResultList();
    }

    public Map listarByRango(Integer valorInicio, Integer valorFin) {
        Query q = this.em.createNamedQuery("BolsaPuntos.byRango");
        q.setParameter("valorInicio", valorInicio);
        q.setParameter("valorFin", valorFin);
        List<BolsaPuntos> bolsas = (List<BolsaPuntos>) q.getResultList();
        Map m = new HashMap();
        if (bolsas.isEmpty()) {
            m.put("estado", -1);
            m.put("mensaje", "No se encontro ninguna bolsa que este en el rango del monto");
            return m;
        }
        m.put("estado", 0);
        m.put("mensaje", "Exito");
        m.put("bolsas", bolsas);
        return m;
    }

    public List<Cliente> listarClienteByPuntosAVencer(Integer dias) {
        Query q = this.em.createNamedQuery("BolsaPuntos.clientesByVencimiento");

        Calendar calendar = Calendar.getInstance();
        Date actual = new Date();

        calendar.setTime(actual);
        calendar.add(Calendar.DAY_OF_YEAR, dias);

        List<Cliente> todos = (List<Cliente>) q
                .setParameter("fechaActual", actual)
                .setParameter("fechaVencimiento", calendar.getTime())
                .getResultList();

        LOGGER.info("JOIN DE TODOS LAS BOLSAS CON CLIENTES: [{}]", todos);
        return todos;
    }

    public void cargarPuntos(Integer idCliente, Integer puntos) {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);

        BolsaPuntos bolsa = new BolsaPuntos();
        bolsa.setCliente(cliente);
        bolsa.setPuntosAsignados(puntos);
        bolsa.setPuntosSaldo(puntos);
        bolsa.setPuntosUtilizados(0);

        // Se tiene que obtener cuando caducen los puntos de la tabla de vencimientos
        Integer diasDuracion = vencimientoBean.getDiasCaducidad();
        LOGGER.info("LOS DIAS DE DURACION DE LOS PUNTOS SON: [{}]", diasDuracion);

        Calendar calendar = Calendar.getInstance();
        Date actual = new Date();
        calendar.setTime(actual);
        calendar.add(Calendar.DAY_OF_YEAR, diasDuracion);

        bolsa.setFechaAsignacion(actual);
        bolsa.setFechaCaducidad(calendar.getTime());
        bolsa.setMontoOperacion(BigDecimal.ZERO);

        this.em.persist(bolsa);
    }

    public List<BolsaPuntos> listarByClienteEnOrden(Integer idCliente) {
        Query q = this.em.createNamedQuery("BolsaPuntos.byClienteEnOrden");
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        return (List<BolsaPuntos>) q
                .setParameter("idCliente", cliente)
                .getResultList();
    }

    public List<BolsaPuntos> listarBolsasVencidas(Date fechaActual) {
        Query q = this.em.createNamedQuery("BolsaPuntos.vencidas");
        return (List<BolsaPuntos>) q.setParameter("fechaActual", fechaActual).getResultList();
    }

    public boolean tieneSaldoSuficiente(Integer idCliente, Integer puntosRequeridos) {
        List<BolsaPuntos> bolsas = listarByClienteEnOrden(idCliente);
        int i = 0, montoBolsa;
        BolsaPuntos bolsaActual;
        
        while (puntosRequeridos>0 && i < bolsas.size()){
            bolsaActual = bolsas.get(i++);
            montoBolsa = bolsaActual.getPuntosSaldo();
            if ( montoBolsa >= puntosRequeridos ){
                return true;
            }else {
                puntosRequeridos = puntosRequeridos - montoBolsa;
            }
        }
        
        return puntosRequeridos == 0;
    }
}
