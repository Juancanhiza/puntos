/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.progweb.prueba.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Startup
@Singleton
public class InitSingleton {

    private static final Logger LOGGER = LogManager.getLogger(InitSingleton.class);

    @PostConstruct
    public void init() {
        LOGGER.info("[Iniciando el singleton de BackEnd]");
    }

    @Schedule(minute = "*/5", second = "0", persistent = false)
    public void actualizarBolsasPuntos() {
        LOGGER.info("[actualizando la bolsa de puntoss]");
    }

}
