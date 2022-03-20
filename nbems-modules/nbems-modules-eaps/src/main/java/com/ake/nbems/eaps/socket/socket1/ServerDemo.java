package com.ake.nbems.eaps.socket.socket1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

    public static void main(String[] args) {

        System.out.println("===服务端启动成功===");

        try {

            // 1、注册端口
            ServerSocket serverSocket = new ServerSocket(7777);

            while (true){
                // 2、必须调用accept方法：等待接收客户端的Socket连接请求，建立Socket通信管道
                Socket socket = serverSocket.accept();

                // 3、从socket通信管道中得到一个字节输入流
                InputStream inputStream = socket.getInputStream();

                // 4、把字节输入流包装成缓冲字符输入流进行消息的接收
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                // 5、按照行读取消息
                String msg;

                while ((msg = br.readLine()) != null){

                    System.out.println(socket.getRemoteSocketAddress() + "说了：" + msg);

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
