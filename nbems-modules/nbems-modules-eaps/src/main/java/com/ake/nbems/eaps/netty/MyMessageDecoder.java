package com.ake.nbems.eaps.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MyMessageDecoder extends ByteToMessageDecoder {
	
	private static final byte[] highDigits;
    private static final byte[] lowDigits;
    
    static {
        byte[] digits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        byte[] high = new byte[256];
        byte[] low = new byte[256];
        for (int i = 0; i < 256; i++) {
            high[i] = digits[i >>> 4];
            low[i] = digits[i & 15];
        }
        highDigits = high;
        lowDigits = low;
    }
    
    public static String byteArray2StringSplitBySpace(byte[] in) {
        StringBuilder sb = new StringBuilder(in.length * 3);
        for (byte b : in) {
            int byteValue = b & 255;
            sb.append((char) highDigits[byteValue]);
            sb.append((char) lowDigits[byteValue]);
            sb.append(' ');
        }
        return sb.toString();
    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	 
        //读取当前数据字节流
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        // String body = (new String(data, StandardCharsets.US_ASCII));
        String body = byteToString(data);
        System.out.println(body);
        out.add(body);

    }

    public String byteToString(byte[] data) {
        int index = data.length;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                index = i;
                break;
            }
        }
        byte[] temp = new byte[index];
        Arrays.fill(temp, (byte) 0);
        System.arraycopy(data, 0, temp, 0, index);
        String str;
        try {
            str = new String(temp, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        return str;
    }



}
