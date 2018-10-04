package mail.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by alexeybel on 04.10.18.
 */
public class MailRuSender implements IMailSender {
    Session m_session = null;
    public boolean sendMessage(String strTheme, String strMessage,String strSourcePlace, String strDistPlace) {
        if(m_session == null) return false;

        try {
            Message message = new MimeMessage(m_session);
            message.setFrom(new InternetAddress(strSourcePlace));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strDistPlace));
            message.setSubject(strTheme);
            message.setText(strMessage);
            Transport.send(message);
            System.out.println("ok all send");
        }catch (MessagingException e){
            System.err.println(e.getMessage());
            return  false;
        }
        return true;
    }

    public boolean makeSession(String username, String password) {
        final String uname = username;
        final String passw = password;
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
        return true;
    }
}
