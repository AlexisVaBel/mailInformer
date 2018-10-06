package mail.service;

import mail.users.UserAccount;
import message.formatter.IMesFormatter;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IMailSender {
   boolean sendMessage(IMesFormatter msg);
   boolean setUsersAccounts(UserAccount userSender,UserAccount userReceiver);
   boolean makeSession();
}
