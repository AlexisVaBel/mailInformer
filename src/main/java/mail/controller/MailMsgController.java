package mail.controller;

import controllers.IConditionController;
import mail.service.IMailSender;
import message.formatter.IMesFormatter;
import message.formatter.MessageDevicesBlocked;

import java.util.Arrays;
import java.util.TimerTask;

/**
 * Created by alexeybel on 09.10.18.
 */
public class MailMsgController extends TimerTask {
    private int                     m_iProcsCnt;
    private int                     m_iProcsMax;
    protected IConditionController  m_controller;
    private IMailSender             m_mailSender;
    private String                  m_taskString;

    public MailMsgController(IMailSender sender, IConditionController controller,String str,int iCntWait){
        this.m_mailSender     = sender;
        this.m_controller     = controller;
        this.m_taskString     = str;
        this.m_iProcsMax      = iCntWait;
    }

    @Override
    public void run() {
        m_controller.processConditions();
        if(m_controller.allProcessed())
            if (m_iProcsCnt < m_iProcsMax){m_iProcsCnt++;}
            else{
                m_iProcsCnt = 0;
                IMesFormatter msg         = new MessageDevicesBlocked();
                msg.setLocation(m_taskString);
                msg.setMesageIn(m_controller.getInfo());
            }
    }


    private void sendMessage(IMesFormatter msg){
        if(m_mailSender.makeSession())
            m_mailSender.sendMessage(msg);
    }

}
