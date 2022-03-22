package com.ake.nbems.eaps.socket.socket1;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.Deflater;

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

            /*// 输入流接收服务端的回应
            InputStream is = socket.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));*/

            while (true){

                System.out.println("请说：");

                String msg = sc.nextLine();

                // 4、发送消息
                ps.println(msg);

                // 打印服务端回应
                //System.out.println(br.readLine());

                ps.flush();

            }

            // 关闭资源。
            // socket.close();

            /*String strTemp = "abcdefg";

            OutputStream os = socket.getOutputStream();

            byte[] a = strTemp.getBytes(StandardCharsets.UTF_8);
            int length = a.length;
            byte[] b = new byte[4];
            b[0] = (byte) (length >> 24);
            b[1] = (byte) ((length >> 16) & 0xFF);
            b[2] = (byte) ((length >> 8) & 0xFF);
            b[3] = (byte) (length & 0xFF);
            os.write(b);
            os.write(a);

            os.close();*/



        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
