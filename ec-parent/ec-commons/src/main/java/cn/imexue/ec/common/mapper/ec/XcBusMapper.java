package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.XcBus;
import cn.imexue.ec.common.model.vo.XcBusVo;

@Mapper
public interface XcBusMapper extends BaseMapper<XcBus> {

	List<XcBusVo> pageLists(Map<String, Object> map);

	List<XcBusVo> queryAllXcBus(Map<String, Object> param);

	List<XcBusVo> queryFreeXcBus(@Param("schoolId") Long schoolId);

	XcBus queryXcBusByParam(@Param("schoolId") Long schoolId, @Param("lineId") Long lineId, @Param("status") Integer status);

	List<XcBus> getByPlateNumber(@Param("plateNumber") String plateNumber);

	List<XcBus> getByBusAttendence(@Param("attendanceDeviceNo") String attendanceDeviceNo);

}
