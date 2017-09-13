package cn.imexue.ec.common.mapper.ec;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.DeviceNvr;

public interface DeviceNvrMapper extends BaseMapper<DeviceNvr> {

	DeviceNvr selectBydeviceNo(String deviceNo);

}
