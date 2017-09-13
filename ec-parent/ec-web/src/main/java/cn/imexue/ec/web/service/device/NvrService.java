package cn.imexue.ec.web.service.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.redis.RedisDeviceSatatusService;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.DeviceCameraMapper;
import cn.imexue.ec.common.mapper.ec.DeviceNvrMapper;
import cn.imexue.ec.common.model.DeviceCamera;
import cn.imexue.ec.common.model.DeviceNvr;
import cn.imexue.ec.common.model.vo.DeviceNvrVO;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.web.controller.device.req.StatusReq;

@Service
@Transactional(readOnly = true)
public class NvrService extends CRUDService<DeviceNvr, DeviceNvrMapper> {

    @Resource
    private RedisDeviceSatatusService redisDeviceSatatusService;

    @Resource
    private DeviceCameraMapper deviceCameraMapper;

    @Override
    public List<? extends DeviceNvr> pageList(Map<String, Object> param) {

	List<? extends DeviceNvr> pageList = super.pageList(param);
	for (DeviceNvr vo : pageList) {
	    // 设置实时状态
	    String status = redisDeviceSatatusService.getNvrStatusInfo(vo.getDeviceNo());
	    if (status != null) {
		vo.setStatus(status);
	    }
	}
	return pageList;
    }

    @Override
    public DeviceNvr getById(Long id) {

	DeviceNvr device = super.getById(id);

	if (device == null) {
	    throw new AppChkException(9001, "attendance.notFind");
	} else {
	    Long schoolId = LoginUtil.getSchoolId();
	    if (!device.getSchoolId().equals(schoolId)) {
		throw new AppChkException(9002, "attendance.schoolId.notIn");
	    }
	    Byte isDefault = 1;
	    if (device.getIsDefaultDeviceNo().equals(isDefault)) {
		device.setDeviceNo("");
	    }
	    Byte isActive = 1;
	    if (device.getIsActive().equals(isActive)) {
		String status = redisDeviceSatatusService.getNvrStatusInfo(device.getDeviceNo());
		if (status != null) {
		    device.setStatus(status);
		}
	    }
	}

	return device;
    }

    public DeviceNvrVO getByNvrId(Long id) {

	DeviceNvr device = super.getById(id);
	Byte isDefault = 1;
	if (device.getIsDefaultDeviceNo().equals(isDefault)) {
	    device.setDeviceNo("");
	}
	String status = redisDeviceSatatusService.getNvrStatusInfo(device.getDeviceNo());
	if (status != null) {
	    device.setStatus(status);
	}
	DeviceNvrVO deviceNvrVO = new DeviceNvrVO();
	BeanUtils.copyProperties(device, deviceNvrVO);

	List<DeviceCamera> cameras = deviceCameraMapper.selectByNvrId(deviceNvrVO.getId());
	deviceNvrVO.setList(cameras);
	return deviceNvrVO;
    }

    @Transactional
    public void updateDeviceBaseInfo(DeviceNvr deviceNvr) {

	DeviceNvr device = dao.selectBydeviceNo(deviceNvr.getDeviceNo());
	if (!(device == null || device.getId().equals(deviceNvr.getId()))) {
	    throw new AppChkException(3001, "attendance.deviceNo.duplicate", deviceNvr.getDeviceNo());
	}
	dao.update(deviceNvr);
    }

    public boolean deviceNoUnique(Long deviceId, String deviceNo) {

	DeviceNvr device = dao.selectBydeviceNo(deviceNo);
	return !(device == null || device.getId().equals(deviceId));
    }

    public List<String> getStatusByDeviceNo(StatusReq<String> statusReq) {

	List<String> statusResult = new ArrayList<String>();
	List<String> statusReq1 = statusReq.getDeviceNos();
	statusResult = redisDeviceSatatusService.getNvrStatusInfo(statusReq1);

	return statusResult;
    }

    @Transactional
    public Result forbidDevice(Long id, Integer versionNo) {

	Result result = new Result();
	DeviceNvr attendance = new DeviceNvr();
	attendance.setId(id);
	attendance.setIsActive(Constants.IS_NOT_ACTIVE);
	attendance.setVersionNo(versionNo);
	int update = dao.update(attendance);
	if (update < 0) {
	    result.setIsSuccess(false);
	}
	return result;
    }

    @Transactional
    public Result startDevice(Long id, Integer versionNo) {

	Result result = new Result();
	DeviceNvr attendance = new DeviceNvr();
	attendance.setId(id);
	attendance.setIsActive(Constants.IS_ACTIVE);
	attendance.setVersionNo(versionNo);
	int update = dao.update(attendance);
	if (update < 0) {
	    result.setIsSuccess(false);
	}
	return result;
    }

}
