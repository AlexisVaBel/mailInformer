package message.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alexeybel on 04.10.18.
 */
public class MessageDevicesBlocked implements IMesFormatter {
    private String strBaseMessage;
    private String strBaseTheme;
    private String strLocation;

    public MessageDevicesBlocked(){
        strBaseTheme = "Список заблокированных устройств:";
    }

    @Override
    public void setMesageIn(String message) {
        strBaseMessage = message;
    }
    @Override
    public void setMesagesIn(List<String> messages) {
        final StringBuilder strBuilder = new StringBuilder();
        messages.stream().forEach(str -> {strBuilder.append(str+"\n");});
        strBaseMessage = strBuilder.toString();
    }

    @Override
    public void setLocation(String strLocation) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.strLocation = strLocation+" "+dateFormat.format(date);
    }

    @Override
    public String getTheme() {
        return strBaseTheme;
    }

    @Override
    public String getLocation() {
        return strLocation;
    }

    @Override
    public String getMessageFormated() {
        return strBaseMessage;
    }
}
