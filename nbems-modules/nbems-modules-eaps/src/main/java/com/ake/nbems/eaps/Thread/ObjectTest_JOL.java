package com.ake.nbems.eaps.Thread;

import com.ake.nbems.eaps.domain.TestObjectByte;
import org.openjdk.jol.info.ClassLayout;


public class ObjectTest_JOL {


    public static void main(String[] args) {
        Object o = new Object();

        // 得出Object o = new Object();占16个字节
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        /*TestObjectByte testObjectByte = new TestObjectByte();
        System.out.println(ClassLayout.parseInstance(testObjectByte).toPrintable());*/
    }
}
