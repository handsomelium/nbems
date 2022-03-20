package com.ake.nbems.eaps.netty.nio;

import com.ake.nbems.eaps.netty.util.ByteBufferUtil;

import java.nio.ByteBuffer;

public class TestByteBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        buffer.flip();

        buffer.get(new byte[4]);

        ByteBufferUtil.debugAll(buffer);

        buffer.rewind();

        System.out.println((char)buffer.get());

        /*System.out.println((char) buffer.get(3));
        ByteBufferUtil.debugAll(buffer);*/


    }
}
