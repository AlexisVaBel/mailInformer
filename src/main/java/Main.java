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
        AbstractTcpController rpsvr = new TCPRpsvrReader("localhost",30010,200);
        rpsvr.connect();
        rpsvr.processConditions();
        rpsvr.disconnect();

//        UserAccount userSender   = new UserAccount("alvalbel@mail.ru","AmSaTrA1987","alvalbel@mail.ru",true);
//        UserAccount userReceiver = new UserAccount("inalmi@mail.ru","","inalmi@mail.ru",false);
//        IMessagesProds msg       = new MessageDevicesBlocked();
//        msg.setLocation("Овсезавод рефакторинг классов");
//        msg.setMesagesIn(Arrays.asList("114 Нория подпор","875 Шнек РКС","12 ДВУ","14 ДСУ"));
//        try {
//            System.out.println("mailer ready");
//            IMailSender mailSender = (FactoryMailSenders.getInstance()).getSender("mailru");
//            System.out.println("mailer making session");
//            mailSender.setUsersAccounts(userSender, userReceiver);
//            if(mailSender.makeSession())
//                mailSender.sendMessage(msg);
//        }catch (Exception e){
//            System.err.println(e);
//        }

    }
}
