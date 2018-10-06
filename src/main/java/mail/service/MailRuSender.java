package mail.service;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by alexeybel on 04.10.18.
 */
public class MailRuSender extends AMailSender {


    public MailRuSender() {
        System.out.println("creating mailru sender");
    }

    @Override
    public boolean makeSession() {
        final String uname = super.m_userSender.getStrUser();
        final String passw = super.m_userSender.getStrPassword();
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.host","smtp.mail.ru");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        m_session = Session.getInstance(props,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(uname,passw);
            }
        });
        return false;
    }

}
