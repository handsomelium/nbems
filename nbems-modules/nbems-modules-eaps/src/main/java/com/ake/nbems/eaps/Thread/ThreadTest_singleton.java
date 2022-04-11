package com.ake.nbems.eaps.Thread;

/**
 * @Author lium
 * @Date 2022/4/11
 * @Description
 */
public class ThreadTest_singleton {

    private volatile static ThreadTest_singleton singleton = null;


    public ThreadTest_singleton(){}



    public static ThreadTest_singleton getSingleton(){
        if (singleton == null){

            synchronized (ThreadTest_singleton.class){
                if (singleton == null){
                    singleton = new ThreadTest_singleton();
                }

            }
        }
        return singleton;
    }


}
