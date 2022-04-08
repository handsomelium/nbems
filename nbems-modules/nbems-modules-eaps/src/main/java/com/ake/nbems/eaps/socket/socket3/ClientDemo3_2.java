package com.ake.nbems.eaps.socket.socket3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo3_2 {

    public static void main(String[] args) {

        System.out.println("====客户端2启动===");

        try {
            Socket socket = new Socket("127.0.0.1", 6666);

            OutputStream os = socket.getOutputStream();


            // 3、把低级的字节流包装成打印流
            PrintStream ps = new PrintStream(os);

            Scanner sc =  new Scanner(System.in);
            while (true) {
                System.out.println("请说：");
                String msg = sc.nextLine();
                // 4、发送消息
                ps.println(msg);
                ps.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
