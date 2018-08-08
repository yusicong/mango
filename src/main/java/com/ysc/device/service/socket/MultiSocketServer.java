package com.ysc.device.service.socket;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/8/3.
 * @Time : 14:16
 */
import com.ysc.device.service.socket.base.ServerSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiSocketServer {
    public MultiSocketServer(){

    }
    public void startSocketServer(int port) {
        ServerSocket serverSocket = null;
        Socket socket  = null;
        try {
            serverSocket=new ServerSocket(port);
            System.out.println("ServerSocket Start:"+serverSocket);
            /**等待请求,此方法会一直阻塞,直到获得请求才往下走*/
            socket=serverSocket.accept();
            System.out.println("得到客户端地址:"+socket.getInetAddress());
            /**调用线程类*/
            new ServerSocketThread(socket).start();
        }  catch (Exception e) {
            try {
                socket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally{
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}