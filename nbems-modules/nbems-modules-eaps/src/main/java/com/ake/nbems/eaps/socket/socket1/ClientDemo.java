package com.ake.nbems.eaps.socket.socket1;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {

        try {

            // 1、创建Socket通信管道请求有服务端的连接
            // public Socket(String host, int port)
            // 参数一：服务端的IP地址
            // 参数二：服务端的端口
            Socket socket = new Socket("127.0.0.1", 7777);

            // 2、从socket通信管道中得到一个字节输出流 负责发送数据
            OutputStream os = socket.getOutputStream();

            // 3、把低级的字节流包装成打印流
            PrintStream ps = new PrintStream(os);

            Scanner sc = new Scanner(System.in);

            while (true){

                System.out.println("请说：");

                String msg = sc.nextLine();

                // 4、发送消息
                ps.println(msg);

                ps.flush();

            }

            // 关闭资源。
            // socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
