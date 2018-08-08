package com.ysc.device.service.socket.base;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/8/3.
 * @Time : 14:15
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread{
    private Socket socket = null;
    private BufferedReader br = null;
    private PrintWriter pw = null;
    //声明构造函数,接收客户端请求socket
    public ServerSocketThread(Socket socket)
    {
        this.socket=socket;

    }
    @Override
    public void run() {
        while(true){
        try {
            //注意此处得到的socket的输入流为socket的输入流即上方的(private Socket socket = null; )
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //向客户端返回消息的PrintWriter对象
            pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            String str = br.readLine();
                if(str.equals("END")){
                    br.close();
                    pw.close();
                    socket.close();
                    break;
                }
            System.out.println("Client Socket Message:"+str);
            pw.println("Message Received");
            pw.flush();
        } catch (Exception e) {
            try {
                br.close();
                pw.close();
                socket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
         }

    }

}