package cn.imexue.ec.web.service.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.redis.RedisDeviceSatatusService;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.DeviceCameraMapper;
import cn.imexue.ec.common.model.Class;
import cn.imexue.ec.common.model.DeviceCamera;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.web.controller.device.req.PointDeviceReq;
import cn.imexue.ec.web.web.controller.device.req.StatusReq;

@Service
@Transactional(readOnly = true)
public class CameraService extends CRUDService<DeviceCamera, DeviceCameraMapper> {

    @Resource
    private RedisDeviceSatatusService redisDeviceSatatusService;

    @Resource
    private ClassMapper classMapper;

    @Override
    public List<? extends DeviceCamera> pageList(Map<String, Object> param) {

	List<? extends DeviceCamera> pageList = super.pageList(param);
	for (DeviceCamera vo : pageList) {
	    // 设置实时状态
	    String status = redisDeviceSatatusService.getCameraStatusInfo(vo.getDeviceNo());
	    if (status != null) {
		vo.setStatus(status);
	    }
	    Class class1 = classMapper.getById(vo.getClassId());
	    if (class1 != null) {
		vo.setClassName(class1.getName());
		vo.setInstallLocation("");
	    } else {
		vo.setClassId(null);

	    }

	}
	return pageList;
    }

    @Override
    public DeviceCamera getById(Long id) {

	DeviceCamera device = super.getById(id);
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
		String status = redisDeviceSatatusService.getCameraStatusInfo(device.getDeviceNo());
		if (status != null) {
		    device.setStatus(status);
		}
	    }
	}

	return device;
    }

    @Transactional
    public void updateDeviceBaseInfo(DeviceCamera attendance) {

	DeviceCamera device = dao.selectBydeviceNo(attendance.getDeviceNo());
	if (!(device == null || device.getId().equals(attendance.getId()))) {
	    throw new AppChkException(3001, "attendance.deviceNo.duplicate", attendance.getDeviceNo());
	}
	dao.update(attendance);
    }

    public boolean deviceNoUnique(Long deviceId, String deviceNo) {

	DeviceCamera device = dao.selectBydeviceNo(deviceNo);
	return !(device == null || device.getId().equals(deviceId));
    }

    public List<String> getStatusByDeviceNo(StatusReq<String> statusReq) {

	List<String> statusResult = new ArrayList<String>();
	List<String> statusReq1 = statusReq.getDeviceNos();
	statusResult = redisDeviceSatatusService.getCameraStatusInfo(statusReq1);

	return statusResult;
    }

    @Transactional
    public Result forbidDevice(Long id, Integer versionNo) {

	Result result = new Result();
	DeviceCamera attendance = new DeviceCamera();
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
	DeviceCamera attendance = new DeviceCamera();
	attendance.setId(id);
	attendance.setIsActive(Constants.IS_ACTIVE);
	attendance.setVersionNo(versionNo);
	int update = dao.update(attendance);
	if (update < 0) {
	    result.setIsSuccess(false);
	}
	return result;
    }

    /**
     * 方法描述: [分配设备.]<br/>
     * 初始作者: 崔业新<br/>
     * 创建日期: 2017年6月20日-上午11:42:52<br/>
     * 开始版本: 1.0.0<br/>
     * =================================================<br/>
     * 修改记录：<br/>
     * 修改作者         日期         修改内容<br/>
     * ================================================<br/>
     * @param schoolId
     * @return
     * List<ClassVo>
     *
     */
    @Transactional
    public Result updateDevice(PointDeviceReq deviceReq) {

	Result result = new Result();
	DeviceCamera device = new DeviceCamera();
	// 拷贝同名的值,注意确保没有多余的值
	BeanUtils.copyProperties(deviceReq, device);
	if (deviceReq.getInstallAddress().equals(1)) {
	    device.setClassId(0L);
	    device.setClassName("");
	    device.setInstallLocation(deviceReq.getInstallLocation() == null ? "" : deviceReq.getInstallLocation());
	}
	if (device.getClassId() == null) {
	    device.setClassId(0L);
	    device.setClassName("");
	    device.setInstallLocation(deviceReq.getInstallLocation() == null ? "" : deviceReq.getInstallLocation());
	}

	if (!device.getClassId().equals(0L)) {
	    Class class1 = classMapper.getById(device.getClassId());
	    if (class1 != null) {
		device.setInstallLocation(class1.getName());
	    }

	}
	int update = dao.update(device);
	if (update < 0) {
	    result.setIsSuccess(false);
	}
	result.setData(update);
	return result;
    }

    public List<ClassVo> getClassBySchoolId(Long schoolId) {

	Result result = new Result();
	List<ClassVo> classVos = classMapper.getClassBySchoolId(schoolId);
	result.setData(classVos);
	return classVos;
    }

    /**
     *
     *
     * 方法描述: [根据班级Id解除摄像头设备.]<br/>
     * 初始作者: 崔业新<br/>
     * 创建日期: 2017年7月14日-下午3:25:04<br/>
     * 开始版本: 1.0.0<br/>
     * =================================================<br/>
     * 修改记录：<br/>
     * 修改作者         日期         修改内容<br/>
     * ================================================<br/>
     * @param device
     * @return
     * Result
     *
     */
    @Transactional
    public void updateDeviceByClassId(Long classId) {

	try {
	    Long schoolId = LoginUtil.getSchoolId();
	    // 删除班级设备
	    List<DeviceCamera> deviceCameras = dao.selectByClassId(classId, schoolId);
	    for (DeviceCamera camera : deviceCameras) {
		camera.setClassId(0L);
		camera.setClassName("");
		camera.setInstallLocation("");
		camera.setVersionNo(camera.getVersionNo());
		dao.update(camera);
	    }

	} catch (MyBatisSystemException e) {
	    Throwable cause = e.getCause().getCause();
	    if (!(cause instanceof AppChkException)) {
		throw e;
	    }
	}
    }
}
