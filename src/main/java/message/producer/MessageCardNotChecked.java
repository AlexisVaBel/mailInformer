package message.producer;

import java.util.List;

/**
 * Created by alexeybel on 04.10.18.
 */
public class MessageCardNotChecked implements IMessagesProds {
    private String strBaseMessage;
    private String strBaseTheme;
    private String strLocation;

    @Override
    public void setMesageIn(String message) {

    }

    @Override
    public void setMesagesIn(List<String> messages) {

    }

    @Override
    public void setLocation(String strLocation) {
        this.strLocation = strLocation;
    }

    @Override
    public String getTheme() {
        return this.strBaseTheme;
    }

    @Override
    public String getLocation() {
        return this.strLocation;
    }

    @Override
    public String getMessageFormated() {
        return strBaseMessage;
    }
}
