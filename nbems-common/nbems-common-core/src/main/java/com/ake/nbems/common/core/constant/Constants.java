package com.ake.nbems.common.core.constant;

/**
 * 通用常量信息
 * 
 * @author lium
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";
    
    /**
     * 验证码尝试次数
     */
    public static final String GATEWAY_CODETRY_KEY = "gateway:codetry:";
    
    /**
     * 验证码尝试次数
     */
    public static final String SYS_CURRENTPROJECT_KEY = "sys:currentproject:";
    
    /**
     * 超过10内登陆失败三次需要获取验证码
     */
    public static final Integer CODETRY_EXPIRATION = 60;

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    
    /**
     * 行政区域表cache key
     */
    public static final String BASE_DISTRICT_KEY = "base_district:";
    
    /**
     * WeChat当前业主的id
     */
    public static final String SYS_WECHATOWNERID_KEY = "sys:currentwechat_ownerid:";
    
    /**
     * 仪表类别
     */
	public static final String METER_CAT_TIME = "时间法";
	public static final String METER_CAT_ENERGY = "能量型";
	public static final String METER_CAT_VAV = "VAV";
	public static final String METER_CAT_ELEC = "电表";
	public static final String METER_CAT_WATER = "水表";
	public static final String METER_CAT_GAS = "气表";
	public static final String METER_CAT_STEAM = "蒸汽表";
	
	/**
	 * 指令类别
	 */
	public static final int CMD_TYPE_CONTROL = 1;
	public static final int CMD_TYPE_COLLECT = 2;
	public static final int CMD_TYPE_BEOADCAST = 3;
	public static final int CMD_TYPE_LINKAGE = 4;
	public static final int CMD_TYPE_AUTO_COLLECT = 5;
	public static final int CMD_TYPE_OPEN_CHANNEL = 6;
	public static final int CMD_TYPE_CLOSE_CHANNEL = 7;
	public static final int CMD_TYPE_UPDATE_RATE = 8;
}
