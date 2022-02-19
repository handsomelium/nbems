package com.ake.nbems.common.core.constant;

public class AlarmConstants {

	 public static final Integer COLLECTOR = 1;

	 public static final Integer METER = 2;
	 
	 //告警类型：1=能耗数据采集器离线
	 public static final Integer TYPE_COLL_OFLINE = 1;
	 //告警类型：2=能耗数据采集器在线无数据上传
	 public static final Integer TYPE_COLL_NODATA = 2;
	 //告警类型：3=仪表离线
	 public static final Integer TYPE_METRE_OFLINE = 3;
	 //告警类型：4=仪表在线无数据上传
	 public static final Integer TYPE_METRE_NODATA = 4;
	 //告警类型：5=抄表数据异常
	 public static final Integer TYPE_COLLECT_DATA_ERROR = 5;
	 //告警类型：6=联动告警
	 public static final Integer TYPE_LINKAGE_ERROR = 6;
	 //告警类型：7=其他
	 public static final Integer TYPE_OTHERS = 7;
	 
	 //仪表数据异常类型（alarm_type=5）：0=正常
	 public static final Integer DATAERROR_NORMAL = 0;
	 //仪表数据异常类型（alarm_type=5）：1=超出预警值
	 public static final Integer DATAERROR_LIMIT = 1;
	 //仪表数据异常类型（alarm_type=5）：2=时钟异动
	 public static final Integer DATAERROR_CLOCK = 2;
	 //仪表数据异常类型（alarm_type=5）：3=读数回退
	 public static final Integer DATAERROR_BACK = 3;
	 //仪表数据异常类型（alarm_type=5）：4=其它
	 public static final Integer DATAERROR_OTHERS = 4;
	 
	 //采集器联动异常状态:0=正常
	 public static final Integer LINKAGE_STATE_NORMAL = 0;
	 //采集器联动异常状态:1=下发失败
	 public static final Integer LINKAGE_STATE_EXEC_ERROR = 1;
	 //采集器联动异常状态:2=采集联动状态失败
	 public static final Integer LINKAGE_STATE_COLLECT_ERROR = 2;
	 
}
