package controllers.tcp;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by alexeybel on 04.10.18.
 */
public class TCPRpsvrReader extends AbstractTcpController {
    private static final String RPSVR_ANSW_CON      = "100";
    private static final String RPSVR_ANSW_SETFLT   = "101";
    private static final String RPSVR_ANSW_CRTTLIST = "103";
    private static final String RPSVR_ANSW_GETTLIST = "104";
    private static final String RPSVR_ANSW_FIXALL   = "105";
    private static final String RPSVR_ANSW_GETALL   = "106";
    private static final String RPSVR_ANSW_GETCHNG  = "107";
    private static final String RPSVR_ANSW_GETWNM   = "109";
    private static final String RPSVR_ANSW_GETPROPS = "116";
    private static final String RPSVR_ANSW_SETFLG   = "117";
    private static final String RPSVR_ANSW_GETCRC   = "118";

    private static final String RPSVR_CMD_SETFLT    = "SETFILTER %s\r\n";
    private static final String RPSVR_CMD_CRTTLIST  = "CREATETAGLIST\r\n";
    private static final String RPSVR_CMD_GETTLIST  = "GETTAGLIST %s\r\\n";
    private static final String RPSVR_CMD_FIXALL    = "FIXALL\r\n";
    private static final String RPSVR_CMD_GETALL    = "GETALL ";
    private static final String RPSVR_CMD_GETCHNG   = "GETCHG ";
    private static final String RPSVR_CMD_GETWNM    = "WNM %s\r\n";
    private static final String RPSVR_CMD_GETPROPS  = "GETPROPS";
    private static final String RPSVR_CMD_SETFLG    = "SETFLAG %s\r\n";
    private static final String RPSVR_CMD_GETCRC    = "GETCRC\r\n";

    private     Logger logger;
    protected   InputStreamReader inReader;
    protected   BufferedWriter    outWritter;

    public TCPRpsvrReader(String strHost, int iPort,int iTimeOut) {
        super(strHost, iPort,iTimeOut);
        logger = LoggerFactory.getLogger(TCPRpsvrReader.class);
    }

    public boolean disconnect() {
        logger.info("disconnect");
        closeConnection();
        return this.connected();
    }

    @Override
    public void processConditions() {
        logger.info("process");
    }

    @Override
    public boolean allProcessed() {
        return false;
    }

    @Override
    public String getInfo() {
        logger.info("getInfo");
        return ";";
    }
//if( !request("SETFILTER " + tagfilter, "101", false) )
//            return false;
//
//	    if( !request("CREATETAGLIST", "103", false ) )
//            return false;
    protected boolean request(){
        return true;
    }

}
