package message.producer;




import java.util.List;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IMessagesProds {
    void setMesageIn(String message);
    void setMesagesIn(List<String> messages);
    void setLocation(String strLocation);
    String getTheme();
    String getLocation();
    String getMessageFormated();
}
