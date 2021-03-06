package controllers.tcp;

import controllers.IConditionController;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by alexeybel on 04.10.18.
 */
public abstract class AbstractTcpController implements IConditionController {
    protected Socket m_socket = null;
    protected String m_strFilter =null;

    public AbstractTcpController(String strHost, int iPort,int iTimeOut,String filter) throws IOException {
        this.m_strFilter = filter;
        m_socket = new Socket();
        m_socket.connect(new InetSocketAddress(strHost, iPort),iTimeOut);

    }

    public void closeConnection(){
        try {
            m_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connected(){
        if(m_socket == null) return false;
        return !(m_socket.isClosed());
    }

    public InputStream getInputStream(){
        try {
            return m_socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OutputStream getOutputStream(){
        try {
            return m_socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        };
        return null;
    }
}
