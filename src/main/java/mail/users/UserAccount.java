package mail.users;

/**
 * Created by alexeybel on 04.10.18.
 */
public class UserAccount {
    private String strUser;
    private String strPassword;
    private String strMail;
    private boolean bSender;
    public UserAccount(String strUName,String strPass,String strMail,boolean bSender){
        this.strUser     = strUName;
        this.strPassword = strPass;
        this.strMail     = strMail;
        this.bSender     = bSender;
    }

    public String getStrUser() {
        return strUser;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public String getStrMail() {
        return strMail;
    }

    public boolean isSender() {
        return bSender;
    }
}
