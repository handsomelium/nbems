package com.ake.nbems.eaps.Thread;


public class ThreadTest_ticket {

    static int ticket = 100;

    static Thread t1;

    static Thread t2;

    public static void main(String[] args) {

        Object o = new Object();

        t1 = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o){
                        if (ticket > 0){
                            ticket--;
                            System.out.println(Thread.currentThread().getName() + "抢到票了，还剩__" + ticket);
                        }else {
                            return;
                        }
                    }

                }



        });


        t2 = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o){
                        if (ticket > 0){
                            ticket--;
                            System.out.println(Thread.currentThread().getName() + "抢到票了，还剩__" + ticket);
                        }else {
                            return;
                        }
                    }

                }


        });

        t1.start();
        t2.start();


    }
}
