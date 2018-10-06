package mail.service;

import mail.users.UserAccount;
import message.formatter.IMesFormatter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by alexeybel on 05.10.18.
 */
public abstract class AMailSender implements IMailSender{
    protected Session m_session = null;
    protected UserAccount m_userSender = null;
    protected UserAccount m_userReceiver = null;

    @Override
    public boolean sendMessage(IMesFormatter msg) {
        if(m_session == null) return false;
        try {
            Message message = new MimeMessage(m_session);
            message.setFrom(new InternetAddress(m_userSender.getStrMail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m_userReceiver.getStrMail()));
            message.setSubject(msg.getTheme());
            message.setText(msg.getMessageFormated());
            Transport.send(message);
            System.out.println("ok all send");
        }catch (MessagingException e){
            System.err.println(e.getMessage());
            return  false;
        }
        return true;
    }

    @Override
    public boolean setUsersAccounts(UserAccount userSender, UserAccount userReceiver) {
        if((userSender==null)||(userReceiver==null))return false;
        this.m_userSender = userSender;
        this.m_userReceiver = userReceiver;
        return true;
    }

}
