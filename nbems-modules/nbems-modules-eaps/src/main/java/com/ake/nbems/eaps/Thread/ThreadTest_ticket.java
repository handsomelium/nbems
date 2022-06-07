package com.ake.nbems.eaps.Thread;


import java.util.concurrent.*;

public class ThreadTest_ticket {

    static int ticket = 100;

    static Thread t1;

    static Thread t2;

    private static ExecutorService pool = new ThreadPoolExecutor(5,
            10, 6, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2)
            , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        Object o = new Object();

        /*t1 = new Thread(() -> {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ticket > 0){
                        synchronized (o){
                            if (ticket > 0){
                                ticket--;
                                System.out.println(Thread.currentThread().getName() + "抢到票了，还剩__" + ticket);
                            }else {
                                return;
                            }
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
        t2.start();*/


        for (int i = 0; i < 5; i++){
            pool.execute(() -> {
                while (true){
                    if (ticket > 0){
                        synchronized (o){
                            if (ticket > 0){
                                ticket--;
                                System.out.println(Thread.currentThread().getName() + "抢到票了，还剩__" + ticket);
                            }else {
                                return;
                            }
                        }
                    }else {
                        return;
                    }
                }
            });
        }

        pool.shutdown();


    }
}
