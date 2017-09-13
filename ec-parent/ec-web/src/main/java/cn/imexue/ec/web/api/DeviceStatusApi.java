package cn.imexue.ec.web.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件名称： cn.imexue.ec.web.api.DeviceStatusApi.java<br/>
 * 初始作者： 崔业新<br/>
 * 创建日期： 2017年8月21日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@FeignClient(name = "api", url = "${ec.api.url}")
@RequestMapping("redis")
public interface DeviceStatusApi {

    @RequestMapping(value = "get/{key}", method = RequestMethod.GET)
    public Map<String, Object> getDeviceStatus(@PathVariable("key") String key);
}
