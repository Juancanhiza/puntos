/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.progweb.prueba.ejb;

import java.util.List;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class AsyncBean {

    private static final Logger LOGGER = LogManager.getLogger(AsyncBean.class);

    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendHttpEmail(List<String> remitentes, String asunto, String template) {
        LOGGER.info("IN: [{}, {}, {}]", remitentes, asunto, (template == null ? template : "Html largo"));
        try {
            Session session = getSession();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constantes.MAIL_USER));

            for (String remitente : remitentes) {
                message.addRecipient(RecipientType.TO, new InternetAddress(remitente));
            }
            message.setSubject(asunto);
            message.setContent(template, "text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", Constantes.MAIL_USER, Constantes.MAIL_PASSWORD);

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            LOGGER.info("Mensaje enviado correctamente");
        } catch (AddressException ex) {
            LOGGER.error("", ex);
        } catch (MessagingException ex) {
            LOGGER.error("", ex);
        }
    }

    private static Session getSession() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.username", Constantes.MAIL_USER);
        properties.put("mail.smtp.password", Constantes.MAIL_PASSWORD);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(properties, null);
        session.setDebug(false);

        return session;
    }

}
