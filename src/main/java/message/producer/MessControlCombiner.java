package message.producer;

import controllers.IConditionController;
import message.formatter.IMesFormatter;

import java.util.TimerTask;

public  class MessControlCombiner extends TimerTask {
    protected IMesFormatter mes;
    protected IConditionController controller;

    public MessControlCombiner (IMesFormatter message,IConditionController contr){
        this.mes        = message;
        this.controller = contr;
    }

    public void timeCheck(){
        controller.processConditions();
        if(controller.allProcessed())procsReady();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void procsReady(){
        mes.setMesageIn(controller.getInfo());
    }

    @Override
    public void run() {
        timeCheck();
    }
}
