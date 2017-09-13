// package cn.imexue.ec.web.service;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
//
// import javax.annotation.Resource;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// import cn.imexue.ec.common.util.RedisKeyConstant;
// import cn.imexue.ec.common.util.StringUtil;
// import cn.imexue.ec.web.api.DeviceStatusApi;
//
// @Service
// public class DeviceSatatusService {
//
// private final static Logger logger = LoggerFactory.getLogger(DeviceSatatusService.class);
//
// @Resource
// private DeviceStatusApi deviceStatusApi;
//
// /**
// * 获取考勤设备状态
// * @param deviceSerial
// * @return
// */
// public String getAttentanceStatusInfo(String deviceSerial) {
//
// if (StringUtil.isBlank(deviceSerial)) {
// return "0";
// }
// String format = String.format(RedisKeyConstant.ATTENDANCE_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// String status = getValueFromApi(format);
// if (status != null) {
// return status;
// } else {
// return "0";
// }
//
// }
//
// public List<String> getAttentanceStatusInfo(List<String> deviceSerials) {
//
// List<String> codelist = new ArrayList<String>();
// for (String deviceSerial : deviceSerials) {
// if (deviceSerial == null) {
// codelist.add("0");
// continue;
// }
// String format = String.format(RedisKeyConstant.ATTENDANCE_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// String status = getValueFromApi(format);
// if (status != null) {
// codelist.add(status);
//
// } else {
// codelist.add("0");
// }
// }
// return codelist;
// }
//
// /**
// * 获取摄像头
// * @param deviceSerial
// * @return
// */
// public String getCameraStatusInfo(String deviceSerial) {
//
// if (StringUtil.isBlank(deviceSerial)) {
// return "0";
// }
// String format = String.format(RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// return getValueFromApi(format);
//
// }
//
// public List<String> getCameraStatusInfo(List<String> deviceSerials) {
//
// List<String> codelist = new ArrayList<String>();
// for (String deviceSerial : deviceSerials) {
// if (deviceSerial == null) {
// codelist.add("0");
// continue;
// }
// String format = String.format(RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// String status = getValueFromApi(format);
// if (status != null) {
// codelist.add(status);
// } else {
// codelist.add("0");
// }
// }
// return codelist;
// }
//
// /**
// * 获取考勤设备状态
// * @param deviceSerial
// * @return
// */
// public String getNvrStatusInfo(String deviceSerial) {
//
// if (StringUtil.isBlank(deviceSerial)) {
// return "0";
// }
// String format = String.format(RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// String status = getValueFromApi(format);
// if (status != null) {
// return status;
// } else {
// return "0";
// }
// }
//
// public List<String> getNvrStatusInfo(List<String> deviceSerials) {
//
// List<String> codelist = new ArrayList<String>();
// for (String deviceSerial : deviceSerials) {
// if (deviceSerial == null) {
// codelist.add("0");
// continue;
// }
// String format = String.format(RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
// String status = getValueFromApi(format);
// if (status != null) {
// codelist.add(status);
// } else {
// codelist.add("0");
// }
// }
// return codelist;
// }
//
// private String getValueFromApi(String key) {
//
// try {
// Map<String, Object> obj = deviceStatusApi.getDeviceStatus(key);
// String status = null == obj.get("data") ? "0" : obj.get("data").toString();
// if ("null".equals(status)) {
// return "0";
// }
// if (status != null) {
// return status;
// } else {
// return "0";
// }
//
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// return "0";
// }
// }
