package mail.service;

import mail.users.UserAccount;
import message.producer.IMessagesProds;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IMailSender {
   boolean sendMessage(IMessagesProds msg);
   boolean setUsersAccounts(UserAccount userSender,UserAccount userReceiver);
   boolean makeSession();
}
