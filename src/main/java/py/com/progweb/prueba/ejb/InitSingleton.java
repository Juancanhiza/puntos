/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.model.BolsaPuntos;

@Startup
@Singleton
public class InitSingleton {

    @Inject
    BolsaPuntosBean bolsaBean;

    private static final Logger LOGGER = LogManager.getLogger(InitSingleton.class);

    @PostConstruct
    public void init() {
        LOGGER.info("[Iniciando el singleton de BackEnd]");
    }

    @Schedule(hour = "*", minute = "0", second = "*/1", persistent = false)
    public void actualizarBolsasPuntos() {
        LOGGER.info("[Actualizando la bolsa de puntos]");
        try {
            List<BolsaPuntos> bolsasVencidas = bolsaBean.listarBolsasVencidas();
            for (BolsaPuntos bolsaVencida : bolsasVencidas) {
                bolsaVencida.setEstado("VENCIDO");
                bolsaBean.actualizar(bolsaVencida);
            }
            LOGGER.info("Se marcaron como vencidas: [{}] bolsas", bolsasVencidas.size());
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

}
