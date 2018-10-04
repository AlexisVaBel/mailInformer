import controllers.tcp.AbstractTcpController;
import controllers.tcp.TCPRpsvrReader;
import mail.service.FactoryMailSenders;
import mail.service.IMailSender;
import mail.users.UserAccount;
import message.producer.IMessagesProds;
import message.producer.MessageDevicesBlocked;
import java.util.Arrays;

/**
 * Created by alexeybel on 03.10.18.
 */
public class Main {
    public static void main(String[] args) {
        AbstractTcpController rpsvr = new TCPRpsvrReader("localhost",30010);
        rpsvr.connect();
        rpsvr.processConditions();
        rpsvr.disconnect();
//        UserAccount userSender   = new UserAccount("alvalbel@mail.ru","AmSaTrA1987","alvalbel@mail.ru",true);
//        UserAccount userReceiver = new UserAccount("lebllex@gmail.com","","lebllex@gmail.com",false);
//
//        IMessagesProds msg       = new MessageDevicesBlocked();
//
//        msg.setLocation("Овсезавод");
//        msg.setMesagesIn(Arrays.asList("114 Нория подпор","875 Шнек РКС","12 ДВУ","14 ДСУ"));

//        try {
//            System.out.println("mailer ready");
//            IMailSender mailSender = (FactoryMailSenders.getInstance()).getSender("mailru");
//            System.out.println("mailer making session");
//            mailSender.makeSession(userSender.getStrUser(), userSender.getStrPassword());
//            mailSender.sendMessage(msg.getLocation()+" "+msg.getTheme(),msg.getMessageFormated(), userSender.getStrMail(), userReceiver.getStrMail());
//        }catch (Exception e){
//            System.err.println(e);
//        }

    }
}
