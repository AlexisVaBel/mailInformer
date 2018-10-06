package controllers.tcp;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by alexeybel on 04.10.18.
 */
public class TCPRpsvrReader extends AbstractTcpController {
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
}
