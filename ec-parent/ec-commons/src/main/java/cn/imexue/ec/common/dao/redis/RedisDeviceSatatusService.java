package cn.imexue.ec.common.dao.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.imexue.ec.common.util.RedisKeyConstant;
import cn.imexue.ec.common.util.StringUtil;

@Service
public class RedisDeviceSatatusService {

	@Resource
	private KeyValueDAO keyValueDAO;

	/**
	 * 获取考勤设备状态
	 * 
	 * @param deviceSerial
	 * @return
	 */
	public String getAttentanceStatusInfo(String deviceSerial) {

		if (StringUtil.isBlank(deviceSerial)) {
			return "0";
		}
		String format = String.format(
				RedisKeyConstant.ATTENDANCE_DEVICE_STATUS_REDIS_KEY,
				deviceSerial);
		String status = keyValueDAO.get(format);
		if (status != null) {
			return status;
		} else {
			return "0";
		}

	}

	public List<String> getAttentanceStatusInfo(List<String> deviceSerials) {

		List<String> codelist = new ArrayList<String>();
		for (String deviceSerial : deviceSerials) {
			if (deviceSerial == null) {
				codelist.add("0");
				continue;
			}
			String format = String.format(
					RedisKeyConstant.ATTENDANCE_DEVICE_STATUS_REDIS_KEY,
					deviceSerial);
			String status = keyValueDAO.get(format);
			if (status != null) {
				codelist.add(status);
			} else {
				codelist.add("0");
			}
		}
		return codelist;
	}

	/**
	 * 获取摄像头
	 * 
	 * @param deviceSerial
	 * @return
	 */
	public String getCameraStatusInfo(String deviceSerial) {

		if (StringUtil.isBlank(deviceSerial)) {
			return "0";
		}
		String format = String.format(
				RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
		String status = keyValueDAO.get(format);
		if (status != null) {
			return status;
		} else {
			return "0";
		}
	}

	public List<String> getCameraStatusInfo(List<String> deviceSerials) {

		List<String> codelist = new ArrayList<String>();
		for (String deviceSerial : deviceSerials) {
			if (deviceSerial == null) {
				codelist.add("0");
				continue;
			}
			String format = String.format(
					RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
			String status = keyValueDAO.get(format);
			if (status != null) {
				codelist.add(status);
			} else {
				codelist.add("0");
			}
		}
		return codelist;
	}

	/**
	 * 获取考勤设备状态
	 * 
	 * @param deviceSerial
	 * @return
	 */
	public String getNvrStatusInfo(String deviceSerial) {

		if (StringUtil.isBlank(deviceSerial)) {
			return "0";
		}
		String format = String.format(
				RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
		String status = keyValueDAO.get(format);
		if (status != null) {
			return status;
		} else {
			return "0";
		}
	}

	public List<String> getNvrStatusInfo(List<String> deviceSerials) {

		List<String> codelist = new ArrayList<String>();
		for (String deviceSerial : deviceSerials) {
			if (deviceSerial == null) {
				codelist.add("0");
				continue;
			}
			String format = String.format(
					RedisKeyConstant.HIK_DEVICE_STATUS_REDIS_KEY, deviceSerial);
			String status = keyValueDAO.get(format);
			if (status != null) {
				codelist.add(status);
			} else {
				codelist.add("0");
			}
		}
		return codelist;
	}
}
