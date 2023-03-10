/**
 * <p>Title: PicUtils.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) AKE 2019<／p>
 * <p>Company: AKE<／p>
 * @author GuoJM
 * @date 2019年3月29日
 * @version 1.0
 */
package com.ake.system.utils;

import com.github.pagehelper.util.StringUtil;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author GuoJM
 *
 */
public class PicUtils {

	public static final int MINI_WIDTH = 400;
	public static final int MINI_HEIGHT = 300;
	public static final String MINI_SUFFIX = "_400x300";
	public static final String STATIC_CONTEXT = "/resource";

	/**
	 * 根据指定大小和指定精度压缩图片
	 * 
	 * @param srcPath                 源图片地址
	 * @param desPath                 目标图片地址
	 * @param desFilesize             指定图片大小，单位kb
	 * @param accuracy                精度，递归压缩的比率，建议小于0.9
	 * @return
	 */
	public static String commpressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy) {
		if (StringUtil.isEmpty(srcPath)) {
			return null;
		}
		if (!new File(srcPath).exists()) {
			return null;
		}
		try {
			File srcFile = new File(srcPath);
			long srcFileSize = srcFile.length();
			System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");

			// 1、先转换成jpg
			Thumbnails.of(srcPath).scale(1f).toFile(desPath);
			// 递归压缩，直到目标文件大小小于desFileSize
			commpressPicCycle(desPath, desFileSize, accuracy);

			File desFile = new File(desPath);
			System.out.println("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
			System.out.println("图片压缩完成！");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return desPath;
	}

	private static void commpressPicCycle(String desPath, long desFileSize, double accuracy) throws IOException {
		File srcFileJPG = new File(desPath);
		long srcFileSizeJPG = srcFileJPG.length();
		// 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
		if (srcFileSizeJPG <= desFileSize * 1024) {
			return;
		}
		// 计算宽高
		BufferedImage bim = ImageIO.read(srcFileJPG);
		int srcWdith = bim.getWidth();
		int srcHeigth = bim.getHeight();
		int desWidth = new BigDecimal(srcWdith).multiply(new BigDecimal(accuracy)).intValue();
		int desHeight = new BigDecimal(srcHeigth).multiply(new BigDecimal(accuracy)).intValue();

		Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
		commpressPicCycle(desPath, desFileSize, accuracy);
	}
	
	public static boolean isPic(String suffix) {
		if(suffix.endsWith(".jpg")||suffix.endsWith(".png")||suffix.endsWith(".jpeg")||suffix.endsWith(".gif")) {
			return true;
		} else {
			return false;
		}
	}
	
}
