package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.DeviceAttendance;
import cn.imexue.ec.common.model.common.KeyValuePair;

public interface DeviceAttendanceMapper extends BaseMapper<DeviceAttendance> {

    DeviceAttendance selectBydeviceNo(String deviceNo);

    List<KeyValuePair> findBusDeviceAttendance(Map<String, Object> param);

    DeviceAttendance getByIdOrDeviceNo(@Param("id") Long id, @Param("deviceNo") String deviceNo);
}
