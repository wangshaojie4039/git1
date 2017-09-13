package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.DeviceCamera;

public interface DeviceCameraMapper extends BaseMapper<DeviceCamera> {

	DeviceCamera selectBydeviceNo(String deviceNo);

	List<DeviceCamera> selectByNvrId(Long nvrId);

	List<DeviceCamera> selectByClassId(@Param("classId") Long classId, @Param("schoolId") Long schoolId);
}
