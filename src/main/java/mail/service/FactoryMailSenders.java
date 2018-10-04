package mail.service;

/**
 * Created by alexeybel on 04.10.18.
 */
public class FactoryMailSenders {
    private static FactoryMailSenders FactInstance = null;
    public static FactoryMailSenders getInstance(){
        if(FactInstance==null) FactInstance = new FactoryMailSenders();
        return  FactInstance;
    }
    public IMailSender getSender(String nameSender) throws Exception {
        switch (nameSender){
            case "mailru": return new MailRuSender();
        };
        throw new Exception("Not found class for "+nameSender);
    }

    private  FactoryMailSenders(){
        FactInstance=this;
    }
}
