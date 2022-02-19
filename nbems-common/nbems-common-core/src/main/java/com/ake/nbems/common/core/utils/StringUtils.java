package com.ake.nbems.common.core.utils;

import com.ake.nbems.common.core.text.StrFormatter;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author lium
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/** 空字符串 */
	private static final String NULLSTR = "";

	/** 下划线 */
	private static final char SEPARATOR = '_';

	/**
	 * 获取参数不为空值
	 * 
	 * @param value defaultValue 要判断的value
	 * @return value 返回值
	 */
	public static <T> T nvl(T value, T defaultValue) {
		return value != null ? value : defaultValue;
	}

	/**
	 * * 判断一个Collection是否为空， 包含List，Set，Queue
	 * 
	 * @param coll 要判断的Collection
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return isNull(coll) || coll.isEmpty();
	}

	/**
	 * * 判断一个Collection是否非空，包含List，Set，Queue
	 * 
	 * @param coll 要判断的Collection
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * * 判断一个对象数组是否为空
	 * 
	 * @param objects 要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * * 判断一个对象数组是否非空
	 * 
	 * @param objects 要判断的对象数组
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * * 判断一个Map是否为空
	 * 
	 * @param map 要判断的Map
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	/**
	 * * 判断一个Map是否为空
	 * 
	 * @param map 要判断的Map
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * * 判断一个字符串是否为空串
	 * 
	 * @param str String
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(String str) {
		return isNull(str) || NULLSTR.equals(str.trim());
	}

	/**
	 * * 判断一个字符串是否为非空串
	 * 
	 * @param str String
	 * @return true：非空串 false：空串
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * * 判断一个对象是否为空
	 * 
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 * 
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * * 判断一个对象是否是数组类型（Java基本型别的数组）
	 * 
	 * @param object 对象
	 * @return true：是数组 false：不是数组
	 */
	public static boolean isArray(Object object) {
		return isNotNull(object) && object.getClass().isArray();
	}

	/**
	 * 去空格
	 */
	public static String trim(String str) {
		return (str == null ? "" : str.trim());
	}

	/**
	 * 截取字符串
	 * 
	 * @param str   字符串
	 * @param start 开始
	 * @return 结果
	 */
	public static String substring(final String str, int start) {
		if (str == null) {
			return NULLSTR;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return NULLSTR;
		}

		return str.substring(start);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str   字符串
	 * @param start 开始
	 * @param end   结束
	 * @return 结果
	 */
	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return NULLSTR;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return NULLSTR;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 * 
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params   参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isEmpty(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}

	/**
	 * 下划线转驼峰命名
	 */
	public static String toUnderScoreCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (i > 0) {
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			} else {
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1)) {
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
				sb.append(SEPARATOR);
			} else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str  验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
	 * 例如：HELLO_WORLD->HelloWorld
	 * 
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String convertToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母大写
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String[] camels = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	/**
	 * 驼峰式命名法 例如：user_name->userName
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj;
	}

	public static String getStrByStrArray(String[] array, String separator) {
		if (isNull(separator)) {
			separator = "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(separator).append(array[i]).append(separator);
			if (i != array.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static String getStrByStrList(List<String> list, String separator) {
		if (isNull(separator)) {
			separator = "";
		}
		if (list == null) {
			list = new ArrayList<String>();
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(separator).append(list.get(i)).append(separator);
			if (i != list.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 版本号比较
	 *
	 * @param v1
	 * @param v2
	 * @return 0代表相等，1代表左边大，-1代表右边大
	 *         Utils.compareVersion("1.0.358_20180820090554","1.0.358_20180820090553")=1
	 */
	public static int compareVersion(String v1, String v2) {
		if (v1.equals(v2)) {
			return 0;
		}
		String[] version1Array = v1.split("[._]");
		String[] version2Array = v2.split("[._]");
		int index = 0;
		int minLen = Math.min(version1Array.length, version2Array.length);
		long diff = 0;

		while (index < minLen
				&& (diff = Long.parseLong(version1Array[index]) - Long.parseLong(version2Array[index])) == 0) {
			index++;
		}
		if (diff == 0) {
			for (int i = index; i < version1Array.length; i++) {
				if (Long.parseLong(version1Array[i]) > 0) {
					return 1;
				}
			}

			for (int i = index; i < version2Array.length; i++) {
				if (Long.parseLong(version2Array[i]) > 0) {
					return -1;
				}
			}
			return 0;
		} else {
			return diff > 0 ? 1 : -1;
		}
	}

	public static boolean isJSonStr(String str) {
		try {
			JSONObject.parse(str);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	public static String getStrByIntList(List<Integer> idList) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < idList.size(); i++) {
			sb.append(idList.get(i));
			if (i != idList.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static String getStrByLongList(List<Long> idList) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < idList.size(); i++) {
			sb.append(idList.get(i));
			if (i != idList.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static boolean isFunStr(String funStr) {
		funStr = funStr.trim();
		if (funStr.contains("function")) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String str) {
		// 正则表达式可以匹配所有的数字,包括负数
		Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
		String bigStr;
		try {
			bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
			// 异常,说明包含非数字
			return false;
		}
		// matcher是全匹配
		Matcher isNum = pattern.matcher(bigStr);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 复制字符串本身方法
	 * 
	 * @param str=待复制字符串
	 * @param times=复制次数
	 * @return copiedStr=复制好的字符串
	 */
	public static String getCopiedStr(String str, int times) {
		String copiedStr = "";
		for (int i = 0; i < times; i++) {
			copiedStr = copiedStr.concat(str);
		}
		return copiedStr;
	}

	/**
	 * @Description : 在前面补0
	 * @param str=待补零的字符串
	 * @param len=字符串长度
	 * @return : String 补0后的字符串
	 */
	private static String getZerosString(String str, int len) {
		int l = str.length();
		int fl = len - l;
		String zs = "";
		for (int i = 0; i < fl; i++) {
			zs += "0";
		}
		str = zs.concat(str);
		return str;
	}

	/**
	 * @Description : 将浮点数的小时转为小时:分钟格式
	 * @param hour=浮点数的小时
	 * @return : String '小时:分钟'格式的字符串
	 */
	public static String convertToTime(double hour) {
		String time = "";
		String hourStr = String.valueOf(hour);
		if (hourStr.contains(".")) {
			String[] splitInfo = hourStr.split("\\.");
			String pHour = splitInfo[0];
			String pMinute = splitInfo[1];
			pMinute = "0.".concat(pMinute);
			pHour = getZerosString(pHour, 6);
			double minute = Double.valueOf(pMinute);
			minute = minute * 60;
			BigDecimal lbd = new BigDecimal(Double.toString(minute));
			minute = lbd.setScale(0, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			pMinute = String.valueOf(minute);
			pMinute = pMinute.contains(".") ? pMinute.split("\\.")[0] : pMinute;
			pMinute = getZerosString(pMinute, 2);
			time = pHour.concat(":").concat(pMinute);
		} else {
			hourStr = getZerosString(hourStr, 6);
			time = hourStr.concat(":00");
		}
		return time;
	}

	public static void getValueList(List<String> valueList) {
		for (int i = 0, len = valueList.size(); i < len; i++) {
			String value = changeScienceNumber(valueList.get(i));
			valueList.set(i, value);
		}
	}

	public static String changeScienceNumber(String scienceNumber) {
		if (scienceNumber.matches("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$")) {
			BigDecimal bd = new BigDecimal(scienceNumber);
			// 科学计数转为小数
			scienceNumber = bd.toPlainString();
		}
		return scienceNumber;
	}

	/**
	 * 判断集合中是否存在某字符串
	 * 
	 * @param collection
	 * @param str
	 * @return
	 */
	public static boolean isExists(String[] collection, String str) {
		boolean isExists = false;
		for (String temp : collection) {
			if (str.equals(temp)) {
				isExists = true;
				break;
			}
		}
		return isExists;
	}

	/**
	 * 检查字符串是否符合规则
	 * 
	 * @param regex=规则表达式
	 * @param str=匹配的字符串
	 * @return
	 */
	public static boolean checkStrMatcher(String regex, String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 字符串转成整型List
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> str2intList(String str) {
		List<Integer> list = null;
		try {
			String[] arr = str.split(",");
			list = new ArrayList<Integer>();
			for (String subStr : arr) {
				list.add(Integer.valueOf(subStr));
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 电话号码隐藏
	 * 
	 * @param str
	 * @return
	 */
	public static String telSecurity(String tel) {
		if(!isEmpty(tel)) {
			if(tel.length()==11) {
				return tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
			} else {
				return tel;
			}
		} else {
			return "";
		}
	}

}