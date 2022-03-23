package com.ake.nbems.eaps.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Slf4j
public class MyMessageEncoder extends MessageToByteEncoder<String> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		byte[] a = msg.getBytes("GBK");
		out.writeBytes(a);
		/*try {
			byte[] a = msg.getBytes("UTF-8");
			a = compress(a);
			int length = a.length;
			byte[] b = new byte[4];
			b[0] = (byte) (length >> 24);
			b[1] = (byte) ((length >> 16) & 0xFF);
			b[2] = (byte) ((length >> 8) & 0xFF);
			b[3] = (byte) (length & 0xFF);
			out.writeBytes(b);
			out.writeBytes(a);
		} catch (IOException e) {
			log.info("编码器发送数据异常-" + "异常信息:",e);
		}*/
	}
	
    /**
	 * <li>压缩算法
	 * @param data
	 * @return
	 */
    public byte[] compress(byte[] data) {
        byte[] output = new byte[0];
        Deflater compresser = new Deflater();   
        compresser.reset();   
        compresser.setInput(data);   
        compresser.finish();   
        ByteArrayOutputStream bos = new ByteArrayOutputStream();   
        try {   
            byte[] buf = new byte[1024];   
            while (!compresser.finished()) {   
                int i = compresser.deflate(buf);   
                bos.write(buf, 0, i);   
            }
            output = bos.toByteArray();   
        } catch (Exception e) {   
            output = data; 
            log.info("netty通讯解压算法，异常信息:" + e);  
        } finally {   
            try {   
                bos.close();   
            } catch (IOException e) {   
            	log.info("netty通讯解压算法，异常信息:" + e);
            }
        }
        compresser.end();   
        return output;   
    }
}
