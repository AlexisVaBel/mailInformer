import controllers.tcp.AbstractTcpController;
import controllers.tcp.TCPRpsvrReader;
import mail.service.FactoryMailSenders;
import mail.service.IMailSender;
import mail.users.UserAccount;
import message.formatter.IMesFormatter;
import message.formatter.MessageDevicesBlocked;

import java.util.Timer;

/**
 * Created by alexeybel on 03.10.18.
 */
public class Main {
    private static UserAccount userSender    = null;
    private static UserAccount userRecepient = null;
    private static IMailSender mailSender    = null;


    private static void makeSender(){
        try {
            mailSender = (FactoryMailSenders.getInstance()).getSender("mailru");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeUsers(){
        userSender      = new UserAccount("alvalbel@mail.ru","AmSaTrA1987","alvalbel@mail.ru",true);
        userRecepient   = new UserAccount("inalmi@mail.ru","","inalmi@mail.ru",false);
    }

    public static void main(String[] args) {

        AbstractTcpController rpsvr = new TCPRpsvrReader("localhost",30000,200);

        Timer timer = new Timer(true);
        // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
//        timer.scheduleAtFixedRate(mescontr, 0, 10*1000);

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask прекращена");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        rpsvr.closeConnection();

//        IMesFormatter msg       = new MessageDevicesBlocked();
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
