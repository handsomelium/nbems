package com.ake.nbems.eaps.Thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadTest_LockSupport {

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {

        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        t1 = new Thread(() -> {

            for (char c : aI){

                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {

            for (char c : aC){

                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);

            }
        });



        t1.start();
        t2.start();
    }
}
