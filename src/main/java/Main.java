import controllers.tcp.AbstractTcpController;
import controllers.tcp.TCPRpsvrReader;
import message.formatter.IMesFormatter;
import message.formatter.MessageDevicesBlocked;
import message.producer.MessControlCombiner;

import java.util.Timer;

/**
 * Created by alexeybel on 03.10.18.
 */
public class Main {
    public static void main(String[] args) {
        IMesFormatter msg       = new MessageDevicesBlocked();
        AbstractTcpController rpsvr = new TCPRpsvrReader("localhost",30000,200);
        MessControlCombiner   mescontr = new MessControlCombiner(msg,rpsvr);
        Timer timer = new Timer(true);
        // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
        timer.scheduleAtFixedRate(mescontr, 0, 10*1000);

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

//        UserAccount userSender   = new UserAccount("alvalbel@mail.ru","AmSaTrA1987","alvalbel@mail.ru",true);
//        UserAccount userReceiver = new UserAccount("inalmi@mail.ru","","inalmi@mail.ru",false);
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
