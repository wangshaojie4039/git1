package cn.imexue.ec.common.util;

/**
 * 错误码常量
 * 
 */
public class CodeConstants {
	// 1***为token相关的错误码
	public static int INVALID_PASSWORD_CODE = 1001;
	public static String INVALID_PASSWORD_MSG = "invalid uid and password.";
	public static int USER_NOT_EXIST_CODE = 1002;
	public static String USER_NOT_EXIST_MSG = "user account is not exist";
	public static int INACTIVE_USER_CODE = 1003;
	public static String INACTIVE_USER_MSG = "user is inactive.";
	public static int EMPTY_TOKEN_CODE = 1010;
	public static String EMPTY_TOKEN_MSG = "access_token can not be empty.";
	public static int INVALID_TOKEN_CODE = 1011;
	public static String INVALID_TOKEN_MSG = "invalid access token.";
	// 2***为验证码相关的错误码
	public static int PHONE_TOO_MUCH_REQUEST_CODE = 2001;
	public static String PHONE_TOO_MUCH_REQUEST_MSG = "too much request.";
	public static int PHONE_INVALID_CODE = 2002;
	public static String PHONE_INVALID_MSG = "invalid verifyCode.";
	public static int INVALID_SIGN_CODE = 2003;
	public static String INVALID_SIGN_MSG = "invalid sign.";
	public static int BEYOND_MAX_COUNT_CODE = 2004;
	public static String BEYOND_MAX_COUNT_MSG = "beyond the maximum count of days";
	// 3***为支付相关的错误码
	public static int PAY_REPEATED_CODE = 3001;
	public static String PAY_REPEATED_MSG = "repeated pay request.";
	// 4***为考勤卡相关的错误码
	public static int CARDNO_NOT_EXIST = 4001;
	public static String CARDNO_NOT_EXIST_MSG = "cardNO is not existed.";

	public static int CARDNO_HAVE_BEEN_USED = 4002;
	public static String CARDNO_HAVE_BEEN_USED_MSG = "cardNO has been used.";

	public static int TEACHER_ALREADY_IN_CLASS_CODE = 5001;
	public static String TEACHER_ALREADY_IN__CLASS_MSG = "this teacher is already in current class.";
	public static int PARENT_AND_CHILD_EXISTED_CODE = 5002;
	public static String PARENT_AND_CHILD_EXISTED_MSG = "parent and child already existed.";
	
	// 6***为考勤机相关错误码
	public static int INVALID_DEVICE_SECRET_CODE = 6001;
	public static String INVALID_DEVICE_SECRET_MSG = "invalid device_secret.";
	public static int DEVICENO_NOT_EXIST_CODE = 6002;
	public static String DEVICENO_NOT_EXIST_MSG = "device_no is not exist";
	public static int INACTIVE_DEVICE_CODE = 6003;
	public static String INACTIVE_DEVICE_MSG = "device is inactive.";
	public static int INACTIVE_CARD_CODE = 6004;
	public static String INACTIVE_CARD_MSG = "card is inactive.";
	public static int INACTIVE_SCHOOL_CODE = 6005;
	public static String INACTIVE_SCHOOL_MSG = "school is inactive.";
	
	// 8***为校验相关
	public static int EMPTY_PARAM_CODE = 8001;
	public static String EMPTY_PARAM_MSG = "parameter {0} can not be empty.";
	public static int INVALID_PARAM_CODE = 8002;
	public static String INVALID_PARAM_MSG = "invalid parameter {0}.";
	public static int INVALID_FORMAT_CODE = 8003;
	public static String INVALID_FORMAT_MSG = "incorrect format for parameter {0}.";
	public static int AT_LEAST_ONE_PARAM_CODE = 8004;
	public static String AT_LEAST_ONE_PARAM_MSG = "at least one parameter is required.";
	public static int DATA_TOO_LONG_CODE = 8005;
	public static String DATA_TOO_LONG_MSG = "data too long for parameter {0}";
	


	// 负数为系统级别的错误
	public static int SYSTEM_ERROR_CODE = -1;
	public static String SYSTEM_ERROR_MSG = "system error.";

	public static int PERMISSION_DENIED_CODE = -2;
	public static String PERMISSION_DENIED_MSG = "permission denied.";

	public static int CODE_OK = 200;
	public static String MSG_OK = "ok";
}
