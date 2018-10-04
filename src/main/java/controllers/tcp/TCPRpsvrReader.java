package controllers.tcp;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by alexeybel on 04.10.18.
 */
public class TCPRpsvrReader extends AbstractTcpController {
    private Logger logger;
    protected InputStreamReader inReader;
    protected BufferedWriter    outWritter;

    public TCPRpsvrReader(String strHost, int iPort) {
        super(strHost, iPort);
        logger = LoggerFactory.getLogger(TCPRpsvrReader.class);
    }

    @Override
    public boolean connect() {
        logger.info("connect");
        return super.connected();
    }

    @Override
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
    public void sendInfo() {
        logger.info("sendInfo");
    }
}
