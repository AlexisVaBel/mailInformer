package mail.service;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IMailSender {
   boolean sendMessage(String strTheme,String strMessage,String strSourcePlace, String strDistPlace);
   boolean makeSession(String strUsername, String strPassword);
}
