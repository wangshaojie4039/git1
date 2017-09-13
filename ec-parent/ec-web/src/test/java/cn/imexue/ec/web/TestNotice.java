package cn.imexue.ec.web;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.imexue.ec.web.api.SchoolNoticeApi;
import cn.imexue.ec.web.api.model.NoticeSendApi;

/**
 * 文件名称： cn.imexue.ec.web.TestNotice.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月3日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNotice {

	private Logger			log	= LoggerFactory.getLogger(getClass());

	@Resource
	private SchoolNoticeApi	schoolNoticeApi;

	@Test
	public void test() {

		NoticeSendApi api = new NoticeSendApi();
		api.setTitle("徐丽莎，标题");
		api.setSchoolId(14l);
		api.setContent("徐丽莎胜利徐");
		ArrayList<Long> classIds = new ArrayList<Long>();
		classIds.add(1L);
		api.setClassIds(classIds);

		Map<String, Object> map2 = schoolNoticeApi.send("T18512525713|e10adc3949ba59abbe56e057f20f883e", api);
		log.debug("{}", map2);

	}

}
