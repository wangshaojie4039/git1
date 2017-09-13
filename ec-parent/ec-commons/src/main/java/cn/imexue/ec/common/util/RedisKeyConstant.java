package cn.imexue.ec.common.util;

/**
 *
 * RedisKeyConstant.java
 *
 * <p>
 * Redis存储时相关key前缀的定义
 * </p>
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since  2017年2月8日
 * @author zhoudw
 * @version 1.0
 */
public class RedisKeyConstant {

    public static final String SMS_CODE = "smsv:{uid}";

    /** 萤石云accessToken的redis键 */
    public static final String YS_TOKEN_REDIS_KEY = "ys:token:code";

    /** 萤石云accessToken过期时间的redis键 */
    public static final String YS_TOKEN_EXPIRE_REDIS_KEY = "ys:token:expire";

    /**海康设备状态*/
    public static final String HIK_DEVICE_STATUS_REDIS_KEY = "hik:%s:status";

    /**考勤设备状态*/
    public static final String ATTENDANCE_DEVICE_STATUS_REDIS_KEY = "attend:%s:status";

    /**硬盘录像机状态*/
    public static final String NVR_DEVICE_STATUS_REDIS_KEY = "nvr:%s:status";

    /**考勤设备心跳*/
    public static final String ATTENDANCE_DEVICE_HEARTBEAT_REDIS_KEY = "attend:{1}:heartbeat";

    /**视频对应的房间号redis键*/
    public static final String VIDEO_GROUP_KEY = "vgroup:";

    // /**用户通讯录缓存*/
    // public static final String USER_TONG_XUN_LU_KEY = "user:{uid}:tongxunlu:{schoolid}";
    // /**用户所在班级信息缓存*/
    // public static final String USER_CLASS_ID_INFO_KEY = "user:{uid}:classids:{schoolid}";
    /**融云IMtoken key*/
    public static final String IM_TOKEN_KEY = "user:{uid}:imtoken";

    /**腾讯云服务器签名 key*/
    public static final String COS_SIGN_KEY = "cossign";

    /**百度云推送所需要的通道ID*/
    public static final String BAIDU_USER_KEY = "baidu:{1}:channelid";
}
