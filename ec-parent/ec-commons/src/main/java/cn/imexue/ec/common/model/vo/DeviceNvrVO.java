package cn.imexue.ec.common.model.vo;

import java.util.List;

import cn.imexue.ec.common.model.DeviceCamera;
import cn.imexue.ec.common.model.DeviceNvr;

public class DeviceNvrVO extends DeviceNvr {

    /**
     * 字段描述: [字段功能描述]
     */
    private static final long serialVersionUID = -1576416456294905960L;

    private List<DeviceCamera> list;

    public List<DeviceCamera> getList() {

	return list;
    }

    public void setList(List<DeviceCamera> list) {

	this.list = list;
    }

    @Override
    public String toString() {

	return "DeviceNvrVO [list=" + list + "]";
    }

}
