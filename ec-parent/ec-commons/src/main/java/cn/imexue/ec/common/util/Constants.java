package cn.imexue.ec.common.util;

import java.math.BigDecimal;

public class Constants {

    /* session参数 */

    /** ↓↓↓↓↓↓↓↓↓↓以下为代码相关的常量↓↓↓↓↓↓↓↓↓↓ */
    /* public code */
    public static final int YES_INT = 1;

    public static final int NO_INT = 0;

    public static final Byte YES_BYTE = 1;

    public static final Byte NO_BYTE = 0;

    public static final String YES_STRING = "1";

    public static final String NO_STRING = "0";

    public static final BigDecimal MAX_AMOUNT = new BigDecimal(100000000000L);

    public static final BigDecimal ZERO_AMOUNT = new BigDecimal(0);

    public static final Long ZERO_LONG = 0L;

    /* 请求方式 */
    public static final String REQ_METHOD_POST = "POST";

    public static final String REQ_METHOD_GET = "GET";

    public static final String APP_USER_ROLE_PARENT = "P";

    public static final String APP_USER_ROLE_TEACHER = "T";

    public static final String APP_USER_ROLE_SCHOOL_LEADER = "D";

    public static final String SCHOOL_LEADER_TAG = "园长";

    public static final String CLASS_LEADER_TAG = "班主任";

    public static final String CLASS_TEACHER_TAG = "老师";

    public static final String YOUKE_TAG = "游客";

    /* 系统角色标识 */
    public static final String SYS_ROLE = "SYS";

    /* 系统角色ID */
    public static final Long SYS_ROLE_ID = 0L;

    /* 代理商登录表 */
    public static final Byte IS_ADMIN = 1; // 是管理员

    public static final Byte IS_NOT_ADMIN = 0; // 不是管理员

    public static final Byte IS_ACTIVE = 1; // 启用

    public static final Byte IS_NOT_ACTIVE = 0; // 禁用

    // 不是
    /* 代理商登录表 */
    public static final Byte IS_CUSTOMER = 1; // 代理商

    public static final Byte IS_ORDERMAN = 2; // 订货商

    /* 代理商通知表 */

    /** 同一手机号一天可获取验证码的最多次数 */
    public static final int SMSCODE_ONEDAY_MAX_COUNT = 50;

    /** 验证码有效期为10分钟 */
    public static final long SMSCODE_EXPIRE_TIME = 10 * 60 * 1000;

    /** 腾讯云服务器签名有效期 （4小时），单位：秒 */
    public static final long COS_SING_EXPIRE_TIME = 4 * 60 * 60;

    /** 系统角色 */
    public static final String SYSTEM_ROLE = "SYS";

    /** The Constant HEADER_NAME_TOKEN. */
    public static final String HEADER_NAME_TOKEN = "AccessToken";

    /** 萤石云获取accessToken的请求地址 */
    public static final String YS_ACCESS_TOKEN_URL = "https://open.ys7.com/api/lapp/token/get";

    public static final String YS_DEVICE_LIST_URL = "https://open.ys7.com/api/lapp/device/list";

    public static final String YS_DEVICE_INFO_URL = "https://open.ys7.com/api/lapp/device/info";

    /** 绑定摄像头 */
    public static final String YS_DEVICE_NVR_CAMERA_ADD = "https://open.ys7.com/api/lapp/device/ipc/add";

    /** 解除绑定摄像头 */
    public static final String YS_DEVICE_NVR_CAMERA_DEL = "https://open.ys7.com/api/lapp/device/ipc/delete";

    /** 萤石云设备注册的请求地址 */
    public static final String YS_DEVICE_ADD_URL = "https://open.ys7.com/api/lapp/device/add";

    /** 萤石云设备获得直播观看地址请求地址 */
    public static final String YS_DEVICE_LIVE_ADDRESS_URL = "https://open.ys7.com/api/lapp/live/address/limited";

    /** 账户禁用状态 */
    public static final int APPUSER_STATUS_UNACTIVE = 0;

    /** 产品类型（视频：CAMERA） */
    public static final String CAMERA = "CAMERA";

    /** 上架产品标志 */
    public static final int IS_ON_SALE = 1;

    /** app端订单支付新建状态 */
    public static final byte ORDER_CREATE_STATUS = 1;

    /** app端订单支付完成状态 */
    public static final byte ORDER_PAY_STATUS = 0;

    /** 性别：男 */
    public static final byte SEX_MALE = 1;

    /** 性别：女 */
    public static final byte SEX_FEMALE = 2;

    /** 性别：未知 */
    public static final byte SEX_UNKNOWN = 0;

    /** 持卡人用户类型：幼儿 */
    public static final String CARD_USER_TYPE_CHILD = "C";

    /** 持卡人用户类型：司机 */
    public static final String CARD_USER_TYPE_DRIVER = "D";

    /** 老师学校角色 */
    public static final String SCHOOL_TEACHER_ROLE = "T";

    /** 园长学校角色 */
    public static final String SCHOOL_DIRECTOR_ROLE = "D";

    /** 分隔符号 */
    public static final String SEPARATOR = ":";

    /** 付款的前后状态 **/
    public static final Byte ORDER_CANCLE = -1; // 取消

    // //
    // 取消
    public static final Byte ORDER_FINISH = 0; // 完成

    // //
    // 完成
    public static final Byte ORDER_NEWSORDER = 1; // 新建

    // //
    // 新建
    public static final Byte ORDER_PAYMENT = 2; // 已付款

    // //
    // 已付款
    public static final Byte ORDER_SURE_PAYMENT = 3; // 待确认付款

    // //
    // 已确认付款
    public static final Byte ORDER_SEND = 4; // 已发货

    // //
    // 已发货
    /** 财务确认付款 **/
    public static final Byte PAYMENT_TRUE = 1; // 确认付款

    public static final Byte PAYMENT_FALSE = 0; // 未确认付款

    /** 付款方式 **/
    public static final String OFFLINE = "offline"; // 线下

    /** 代理级别 **/
    public static final Byte PROVINCE = 1; // 省代

    public static final Byte CITY = 2; // 市代

    public static final Byte AREA = 3; // 区代

    /** 是否为默认地址 */
    public static final Byte ISDEFAULT = 1; // 默认地址

    public static final Byte NOTDEFAULT = 0; // 不是默认地址

    /** 考勤区分 */
    public static final String ATTENDANCE_IN = "in"; // 入园

    public static final String ATTENDANCE_OUT = "out"; // 出园

    public static final String ATTEND = "1"; // 已打卡

    public static final String UN_ATTEND = "0"; // 未打卡

    /** 考勤画面用显示信息 */
    public static final String SCHOOL_TITLE = "全园";

    public static final String ATTEND_IN_TITLE = "签到考勤";

    public static final String ATTEND_OUT_TITLE = "签退考勤";

    public static final String ATTEND_RATE_TITLE = "出勤率";

    public static final String ATTEND_CNT_TITLE = "出勤数";

    public static final String UNATTEND_RATE_TITLE = "缺勤率";

    public static final String UNATTEND_CNT_TITLE = "缺勤数";

    public static final String ATTEND_LEAVE_TYPE = "LEAVE_TYPE";

    /** 系统代码库中的类型 */
    public static final String LEAVE_TYPE = "LEAVE_TYPE"; // 请假类型

    /** 支付方式：支付宝 */
    public static final String PAY_WAY_ALI = "alipay";

    /** 支付方式：微信 */
    public static final String PAY_WAY_WEIXIN = "weixin";

    /** 提现状态：已提现 */
    public static final String WITHDRAW_YES = "1";

    /** 提现状态：处理中 */
    public static final String WITHDRAW_NO = "0";

    /** 产品类型 **/
    /** 摄像头 **/
    public static final String PRODUCT_CAMERA = "CAMERA";

    /** 存储摄像头 **/
    public static final String PRODUCT_CAMERA_NVR = "NVR";

    /** 考勤机 **/
    public static final String PRODUCT_ATTENDANCE = "ATTENDANCE";

    /** 校车考勤机 **/
    public static final String PRODUCT_BUS_ATTENDANCE = "BUS_ATTENDANCE";

    /** 卡 **/
    public static final String PRODUCT_CARD = "CARD";

    /** 通知是否已读 **/
    public static final Byte IS_READ = 1; // 是

    public static final Byte NOT_READ = 0; // 否

    // excel保存地址
    public static final String PROJECT_TJJGD_DOWNLOAD_PATH = "project";

    // ////////////////////////////短信模板//////////////////////////////////////////
    /** 腾讯短信签名，须加在短信内容的最前面 */
    public static final String SMS_ORANGE_SIGN = "【幼儿帮】";

    /** 验证码短信模板 */
    public static final Integer TEMPLATE_VALIDATE_CODE = 1;

    public static final String SMS_VALIDATE_CODE = "验证码：{1}，用于e学幼儿帮的身份验证，{2}分钟内有效，请妥善保管。";

    /** 家长加入班级短信模板 */
    public static final Integer TEMPLATE_PARENT_JOIN_CLASS = 11;

    public static final String SMS_PARENT_JOIN_CLASS = "尊敬的家长{1}，欢迎您加入{2}，现已开通相关权限，可登录e学幼儿帮软件查看，谢谢！客服热线：4006002588";

    /** 家长开户短信模板 */
    public static final Integer TEMPLATE_PARENT_CREATE = 12;

    public static final String SMS_PARENT_CREATE = "尊敬的家长{1}，您的密码为{2}，用于登录e学幼儿帮软件，请妥善保管，谢谢！";

    /** 老师开户短信模板 */
    public static final Integer TEMPLATE_TEACHER_CREATE = 21;

    public static final String SMS_TEACHER_CREATE = "尊敬的老师{1}，您的密码为{2}，用于登录e学幼儿帮，请妥善保管，谢谢！";

    /** 老师加入学校短信模板 */
    public static final Integer TEMPLATE_TEACHER_JOIN_SCHOOL = 22;

    public static final String SMS_TEACHER_JOIN_SCHOOL = "尊敬的老师{1}，欢迎您加入{2}，现已开通相关权限，可登录e学幼儿帮软件查看，谢谢！客服热线：4006002588";

    /** 代理商开户短信模板 */
    public static final Integer TEMPLATE_CUSTOMER_CREATE = 31;

    public static final String SMS_CUSTOMER_CREATE = "尊敬的服务商{1}，您的密码为{2}，用于登录e学幼儿帮后台，请妥善保管。";

    /** 代理商重置短信模板 */
    public static final Integer TEMPLATE_CUSTOMER_PASSWORD_RESET = 32;

    public static final String SMS_CUSTOMER_PASSWORD_RESET = "尊敬的服务商{1}，您的密码已变更为{2}，请妥善保管，谢谢！客服热线：4006002588";

    /** 代理商通知短信模板 */
    public static final Integer TEMPLATE_CUSTOMER_NOTICE = 33;

    public static final String SMS_CUSTOMER_NOTICE = "尊敬的服务商{1}，您收到一条标题为“{2}”的通知，详情请登录e学幼儿帮系统查看。";

    /** 总校开户短信模板 */
    public static final Integer TEMPLATE_SCHOOL_GROUP_CREATE = 41;

    public static final String SMS_SCHOOL_GROUP_CREATE = "尊敬的用户{1}，您的密码为{2}，用于登录e学幼儿帮，请妥善保管，谢谢！";

    /** 总校重置短信模板 */
    public static final Integer TEMPLATE_SCHOOL_GROUP_PASSWORD_RESET = 42;

    public static final String SMS_SCHOOL_GROUP_PASSWORD_RESET = "尊敬的用户{1}，您的密码已变更为{2}，请妥善保管，谢谢！客服热线：4006002588";

    /** 早中晚餐食谱蔡菜单类型 **/
    public static final String BREAKFAST = "B";

    public static final String BREAKFAST_PLUS = "BP";

    public static final String LUNCH = "D";

    public static final String LUNCH_PLUS = "DP";

    public static final String DINNER = "D2";

    public static final String DINNER_PLUS = "D2P";

    /** 考勤状态：1-正常 **/
    public static final byte ATTENDANCE_STATUS_NORMAL = 1;

    /** 考勤状态：2-迟到or早退 **/
    public static final byte ATTENDANCE_STATUS_UNNORMAL = 2;

    /** 考勤状态：3-请假 **/
    public static final byte ATTENDANCE_STATUS_LEAVE = 3;

    /** 考勤状态：4-忘打卡 **/
    public static final byte ATTENDANCE_STATUS_FORGET = 4;

    /** 心知天气预报开放API **/
    public static final String WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";

    /** 心知天气apikey **/
    public static final String WEATHER_API_KEY = "ufsomiwphwcgpfk6";

    /** 融云系统用户 **/
    public static final String RC_SYSTEM_USER = "admin001";

    /** IOS操作系统 **/
    public static final String PLATFORM_IOS = "ios";

    /** 安卓操作系统 **/
    public static final String PLATFORM_ANDROID = "android";

}
