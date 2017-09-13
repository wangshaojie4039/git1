package cn.imexue.ec.web.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.imexue.ec.web.api.model.NoticeSendApi;

/**
 * 文件名称： cn.imexue.ec.web.api.SchoolNoticeApi.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月3日</br>
 * 功能说明： 发送学校通知 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@FeignClient(name = "api", url = "${ec.api.url}")
@RequestMapping("notice")
public interface SchoolNoticeApi {

	@RequestMapping(value = "send", method = RequestMethod.POST)
	public Map<String, Object> send(@RequestHeader("AccessToken") String AccessToken,
			@RequestBody NoticeSendApi api);
}
