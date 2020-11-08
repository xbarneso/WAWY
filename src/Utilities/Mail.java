/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.Properties;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message.RecipientType;

/**
 *
 * @author xbarn
 */
public class Mail {

    public Mail() {

    }

    public void envia_mail(String to, String fileFull) {

        try {
            
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            //props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); //TLS

            final String username = "WAWYxbarnes@gmail.com";//change accordingly
            final String password = "WAWY_xbarnes_123";//change accordingly

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("WAWY_xbarnes@gmail.com"));
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject("WAWY - Report Requested");
            String bodyMail = "Hola! Pots trobar adjunt el fitxer amb les dades requerides. ";

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(bodyMail, "text/html");
            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();

            attachPart.attachFile(fileFull);
            multipart.addBodyPart(attachPart);
            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
        }

    }
}
