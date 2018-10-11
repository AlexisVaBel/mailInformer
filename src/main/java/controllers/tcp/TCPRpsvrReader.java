package controllers.tcp;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;

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

    private static enum  TCPRPSVR_STATES{
        STATE_STOP,
        STATE_CON,
        STATE_HNDSK,
        STATE_WORK,
        STATE_RECON
    };
    private TCPRPSVR_STATES m_state;
    private static final int    RPSVR_BUF_SIZE  = 32768;

    private     Logger logger;
    protected InputStream         inReader;
    protected   BufferedWriter    outWritter;

    public TCPRpsvrReader(String strHost, int iPort,int iTimeOut,String strFilter) throws IOException {
        super(strHost, iPort,iTimeOut,strFilter);
        m_state = TCPRPSVR_STATES.STATE_HNDSK;
        logger = LoggerFactory.getLogger(TCPRpsvrReader.class);
        inReader = super.getInputStream();
        outWritter=new BufferedWriter(new OutputStreamWriter(super.getOutputStream(), Charset.forName("UTF-8")),1024);
    }

    public boolean disconnect() {
        logger.info("disconnect");
        closeConnection();
        return this.connected();
    }

    @Override
    public void processConditions() {
        switch (m_state){
            case STATE_STOP:    logger.info("stopped");
                                stopped();
            break;
            case STATE_HNDSK:   logger.info("handshake");
                                handshaking();
            break;
            case STATE_CON:     logger.info("connecting..");
                                connecting();
            break;
            case STATE_WORK:    logger.info("work...");
                                working();
            break;
            case STATE_RECON:   logger.info("reconnecting..");
                                reconnecting();
            break;
        }
    }

    private void stopped() {
        m_state = TCPRPSVR_STATES.STATE_HNDSK;
    }

    private void handshaking() {
        if(getAnswer(RPSVR_ANSW_CON))
            m_state = TCPRPSVR_STATES.STATE_CON;
    }
    private void connecting(){
//        logger.info("process");
        if(request(RPSVR_ANSW_SETFLT, String.format(RPSVR_CMD_SETFLT,m_strFilter)))
            m_state = TCPRPSVR_STATES.STATE_WORK;
    };

    private void working() {
        if(request(RPSVR_ANSW_CRTTLIST, String.format(RPSVR_CMD_CRTTLIST,m_strFilter)))
            if(request(RPSVR_ANSW_GETTLIST, String.format(RPSVR_CMD_GETTLIST,m_strFilter)));
                logger.info("got new list");
//            m_state = TCPRPSVR_STATES.STATE_RECON;
    }

    private void reconnecting() {
        m_state = TCPRPSVR_STATES.STATE_STOP;
    }

    @Override
    public boolean allProcessed() {
        return false;
    }

    @Override
    public String getInfo() {
        logger.info("getInfoString");
        return ";";
    }

    @Override
    public List<String> getInfoList() {
        logger.info("getInfoList");
        return null;
    }

    //


    protected boolean request(String cmdCode,String cmd){
        int iAttempt =0;
        while(iAttempt < 3){
            try {
                outWritter.write(cmd);
                outWritter.flush();
                if(!getAnswer(cmdCode))
                    iAttempt++;
                else return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    protected boolean getAnswer(String strAwaitCode){
        logger.info("getting answer");
        byte[] buffin = new byte[1024];
        try {
            int i = 0;
            int n = 0;
            boolean flProcs=true;
            while(flProcs) {
                n = Math.max(1, inReader.available());
                if((n = inReader.read(buffin,i,n))==-1)return false;
                n = i + n;
                while(i < n){
                    if(buffin[i]==13 || buffin[i]==10) {
                        flProcs = false;
                        break;
                    }
                    else
                        i++;
                }
            }
            CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
            CharBuffer chBuffer = decoder.decode(ByteBuffer.wrap(buffin,0,i));
            StringBuilder   strBld = new StringBuilder(chBuffer);

            logger.info("answer is "+strBld.toString());
            if(strBld.toString().contains(strAwaitCode)) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  false;
    }

}
