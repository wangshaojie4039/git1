package cn.imexue.ec.web.service.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.redis.RedisDeviceSatatusService;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.DeviceAttendanceMapper;
import cn.imexue.ec.common.mapper.ec.XcBusMapper;
import cn.imexue.ec.common.model.DeviceAttendance;
import cn.imexue.ec.common.model.XcBus;
import cn.imexue.ec.common.model.common.KeyValuePair;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.DeviceAttendanceVo;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.web.controller.device.req.StatusReq;

@Service
@Transactional(readOnly = true)
public class AttendanceService extends CRUDService<DeviceAttendance, DeviceAttendanceMapper> {

    @Resource
    private RedisDeviceSatatusService redisDeviceSatatusService;

    @Resource
    private XcBusMapper xcBusMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends DeviceAttendance> pageList(Map<String, Object> param) {

	List<? extends DeviceAttendance> pageList = super.pageList(param);
	List<DeviceAttendanceVo> pageLists = new ArrayList<DeviceAttendanceVo>();

	for (DeviceAttendance vo : pageList) {

	    // 设置实时状态
	    String status = redisDeviceSatatusService.getAttentanceStatusInfo(vo.getDeviceNo());

	    if (status != null) {
		vo.setStatus(status);
	    }

	    DeviceAttendanceVo attendanceVo = new DeviceAttendanceVo();
	    BeanUtils.copyProperties(vo, attendanceVo);
	    attendanceVo.setProductTypeStr(vo.getProductType().equals("ATTENDANCE") ? "考勤机" : "校车考勤机");
	    pageLists.add(attendanceVo);

	}
	Page<DeviceAttendanceVo> page = (Page<DeviceAttendanceVo>) param.get(Page.PAGEKEY);
	page.setList(pageLists);
	return pageLists;
    }

    public DeviceAttendance getByIdOrDeviceNo(Long id, String deviceNo) {

	DeviceAttendance device = dao.getByIdOrDeviceNo(id, deviceNo);
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
		String status = redisDeviceSatatusService.getAttentanceStatusInfo(device.getDeviceNo());
		if (status != null) {
		    device.setStatus(status);
		}
	    }

	}

	return device;
    }

    @Transactional
    public void updateDeviceBaseInfo(DeviceAttendance attendance) {

	DeviceAttendance device = dao.selectBydeviceNo(attendance.getDeviceNo());
	if (!(device == null || device.getId().equals(attendance.getId()))) {
	    throw new AppChkException(3001, "attendance.deviceNo.duplicate", attendance.getDeviceNo());
	}
	dao.update(attendance);
    }

    public boolean deviceNoUnique(Long deviceId, String deviceNo) {

	DeviceAttendance device = dao.selectBydeviceNo(deviceNo);
	return !(device == null || device.getId().equals(deviceId));
    }

    public List<String> getStatusByDeviceNo(StatusReq<String> statusReq) {

	List<String> statusReq1 = statusReq.getDeviceNos();
	List<String> statusResult = redisDeviceSatatusService.getAttentanceStatusInfo(statusReq1);
	return statusResult;
    }

    @Transactional
    public Result forbidDevice(Long id, Integer versionNo) {

	Result result = new Result();
	DeviceAttendance attendanceVo = dao.getById(id);
	List<XcBus> xcBus = xcBusMapper.getByBusAttendence(attendanceVo.getDeviceNo());
	for (XcBus xcbus : xcBus) {
	    xcbus.setAttendanceDeviceNo("");
	    xcBusMapper.update(xcbus);
	}

	DeviceAttendance attendance = new DeviceAttendance();
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
	DeviceAttendance attendance = new DeviceAttendance();
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
     * 方法描述: [校车考勤机：设备编号-设备名称.]<br/>
     * 初始作者: 钟小平<br/>
     * 创建日期: 2017年7月21日-下午3:02:30<br/>
     * 开始版本: 2.0.0<br/>
     * =================================================<br/>
     * 修改记录：<br/>
     * 修改作者 日期 修改内容<br/>
     * ================================================<br/>
     *
     * @param schoolId
     * @return
     *         List<KeyValuePair>
     */
    public List<KeyValuePair> findBusDeviceAttendance(Long schoolId) {

	HashMap<String, Object> param = new HashMap<String, Object>();

	param.put("schoolId", schoolId);
	param.put("productType", Constants.PRODUCT_BUS_ATTENDANCE);
	return dao.findBusDeviceAttendance(param);
    }
}
