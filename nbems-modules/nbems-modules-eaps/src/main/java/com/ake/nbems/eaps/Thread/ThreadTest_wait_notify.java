package com.ake.nbems.eaps.Thread;

public class ThreadTest_wait_notify {

    public static void main(String[] args) {
        // 多线程加锁目的，互相通知，唤醒/等待
        Object o = new Object();
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        new Thread(() -> {
            synchronized (o){
                for (char c : aI){
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o){
                for (char c : aC){
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
