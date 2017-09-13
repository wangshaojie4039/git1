package cn.imexue.ec.web.service.notice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.mapper.ec.SchoolNoticeMapper;
import cn.imexue.ec.common.mapper.ec.SchoolNoticeReceiverMapper;
import cn.imexue.ec.common.mapper.ec.TeacherMapper;
import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.SchoolNotice;
import cn.imexue.ec.common.model.SchoolNoticeReceiver;
import cn.imexue.ec.common.model.vo.SchoolNoticeItemVo;
import cn.imexue.ec.common.model.vo.SchoolNoticeReceiverVo;
import cn.imexue.ec.common.model.vo.SchoolNoticeVo;

/**
 * 文件名称： cn.imexue.ec.web.service.notice.NoticeService.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年6月30日</br>
 * 功能说明： 学校通知Service <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Service
@Transactional(readOnly = true)
public class SchoolNoticeService {

	@Resource
	private SchoolNoticeMapper			schoolNoticeMapper;

	@Resource
	private SchoolNoticeReceiverMapper	schoolNoticeReceiverMapper;

	@Resource
	private TeacherMapper				teacherMapper;

	@Resource
	private SchoolMapper				schoolMapper;

	@Resource
	private ClassMapper					classMapper;

	public List<? extends SchoolNoticeVo> pageList(Map<String, Object> param) {

		List<? extends SchoolNoticeVo> list = schoolNoticeMapper.pageList(param);
		list.forEach(x -> x.setTeacherInfo(teacherMapper.getById(x.getTeacherId())));
		return list;

	}

	@Transactional
	public void deleteSchoolNotice(Long id, Integer versionNo) {

		SchoolNotice sn = schoolNoticeMapper.getById(id);
		if (sn == null) {
			throw new AppChkException(4001, "schoolNotice.id.noexist", id);
		}

		SchoolNotice nsn = new SchoolNotice();
		nsn.setId(id);
		nsn.setDeleteTime(new Date());
		nsn.setIsDelete((byte) 1);
		nsn.setVersionNo(versionNo);
		schoolNoticeMapper.updateByPrimaryKeySelective(nsn);
	}

	@Transactional
	public void updateSchoolNoticeReceiver(Long schoolId, Long schoolNoticeId, Long uid, String role) {

		SchoolNoticeReceiver sn = schoolNoticeReceiverMapper.getByNoticeIdAndUid(schoolId, schoolNoticeId, uid, role);
		if (sn != null) {
			SchoolNoticeReceiver nsn = new SchoolNoticeReceiver();
			nsn.setId(sn.getId());
			nsn.setIsRead((byte) 1);
			nsn.setVersionNo(sn.getVersionNo());
			schoolNoticeReceiverMapper.update(nsn);
		}

	}

	/**
	 * 方法描述: [通知详情.]</br>
	 * 初始作者: zhongxp<br/>
	 * 创建日期: 2017年7月1日-下午3:47:15<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param id
	 * @return
	 *         SchoolNoticeItemVo
	 */
	public SchoolNoticeItemVo getNoticeItemById(Long id) {

		SchoolNotice sni = schoolNoticeMapper.getById(id);
		if (sni == null) {
			throw new AppChkException(4001, "schoolNotice.id.noexist", id);
		}
		SchoolNoticeItemVo sniv = new SchoolNoticeItemVo();
		BeanUtils.copyProperties(sni, sniv);
		School s = schoolMapper.getById(sni.getSchoolId());

		List<SchoolNoticeReceiverVo> snrr = schoolNoticeReceiverMapper.getBySchoolNoticeIdAndIsRead(sni.getSchoolId(),
				id, 1); // 已读
		List<SchoolNoticeReceiverVo> snrn = schoolNoticeReceiverMapper.getBySchoolNoticeIdAndIsRead(sni.getSchoolId(),
				id, 0); // 已读

		sniv.setTeacherVo(teacherMapper.getTeacherById(sni.getTeacherId(), sni.getSchoolId()));
		sniv.setSchool(s);
		sniv.setHasReadSchoolNoticeReceiver(snrr);
		sniv.setNotReadSchoolNoticeReceiver(snrn);

		return sniv;
	}
}
