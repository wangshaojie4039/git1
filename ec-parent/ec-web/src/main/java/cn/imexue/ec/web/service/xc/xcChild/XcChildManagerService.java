package cn.imexue.ec.web.service.xc.xcChild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.ParentMapper;
import cn.imexue.ec.common.mapper.ec.XcChildMapper;
import cn.imexue.ec.common.model.XcChild;
import cn.imexue.ec.common.model.vo.ChildRideExtVo;
import cn.imexue.ec.common.model.vo.ChildRideVo;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassRideVoItem;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.IdNameVO;
import cn.imexue.ec.common.model.vo.SchoolChildRideVo;
import cn.imexue.ec.common.model.vo.XcChildInfoVo;
import cn.imexue.ec.common.model.vo.XcChildItemVo;
import cn.imexue.ec.common.model.vo.XcChildVo;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq;
import cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineChildReq;

/**
 * 文件名称： cn.imexue.ec.web.service.xc.xcChild.XcChildManagerService.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车幼儿管理Service<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Service
@Transactional(readOnly = true)
public class XcChildManagerService extends CRUDService<XcChild, XcChildMapper> {

	@Resource
	private ParentMapper	parentMapper;

	@Resource
	private ChildMapper		childMapper;

	@Resource
	private ClassMapper		classMapper;

	public List<? extends XcChildVo> pageLists(Map<String, Object> param) {

		List<? extends XcChildVo> list = dao.pageLists(param);

		list.forEach(x -> x.setParentVo(parentMapper.getParentByChild(x.getChildId())));

		return list;
	}

	@Transactional
	public void deleteXcChild(Long id) {

		XcChild xc = dao.getById(id);

		if (xc == null) {
			throw new AppChkException(6001, "xcChild.id.noexist", id);
		}
		dao.deleteById(id);
	}

	@Transactional
	public void SaveXcChild(XcChildReq req) {

		if (req.getIds().size() == 0) {
			throw new AppChkException(6002, "xcChild.childId.noexist", req.getSchoolId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<XcChild> list = new ArrayList<XcChild>();

		for (Long childId : req.getIds()) {
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("childId", childId);
			param.put("schoolId", req.getSchoolId());
			ChildVo c = childMapper.getChild(childId, req.getSchoolId());
			if (c == null) {
				throw new DataNoFountException("child");
			}
			List<XcChild> allreadExit = dao.findXcChildByChildId(param);
			if (allreadExit.size() > 0) {
				XcChild xc = new XcChild();
				xc.setSchoolId(req.getSchoolId());
				xc.setChildId(childId);
				xc.setLineId(req.getLineId());
				xc.setVersionNo(allreadExit.get(0).getVersionNo());
				xc.setId(allreadExit.get(0).getId());
				dao.update(xc);
				continue;
			}
			XcChild xc = new XcChild();
			xc.setSchoolId(req.getSchoolId());
			xc.setChildId(childId);
			xc.setLineId(req.getLineId());
			list.add(xc);
		}

		if (list.size() > 0) {
			map.put("list", list);
			dao.batchInsert(map);
		}

	}

	@Transactional
	public void updateXcChild(List<XcLineChildReq> param) {

		if (param.size() == 0) {
			throw new DataNoFountException("child");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<XcChild> list = new ArrayList<XcChild>();
		for (XcLineChildReq p : param) {
			XcChild xc = new XcChild();
			xc.setSchoolId(p.getSchoolId());
			xc.setId(p.getXcChildId());
			xc.setVersionNo(p.getVersionNo());
			xc.setLineId(p.getLineId());

			XcChild x = dao.getById(p.getXcChildId());
			if (x == null) {
				throw new DataNoFountException(p.getXcChildId().toString());
			}
			list.add(xc);
		}
		map.put("list", list);
		dao.batchUpdate(map);
	}

	public List<XcChildItemVo> findChildByParam(Map<String, Object> param) {

		return dao.queryChildByParam(param);
	}

	public ChildRideVo findClassBySchoolId(Long schoolId) {

		ChildRideVo info = new ChildRideVo();
		List<ClassRideVoItem> list = dao.queryClassBySchoolId(schoolId);
		Integer sum = list.stream().filter(x -> x.getClassChildTotal() > 0).mapToInt(x -> x.getClassChildTotal()).sum();

		info.setChildTotal(sum);
		info.setXcClassList(list);
		return info;
	}

	public List<XcChildInfoVo> findChildInfoByParam(Map<String, Object> param) {

		return dao.queryChildInfoByParam(param);
	}

	public List<ChildRideExtVo> findChildInfoNeedByParam(Map<String, Object> param) {

		List<ChildRideExtVo> childList = childMapper.selecChildByClassId(param);

		return childList;
	}

	@Transactional
	public void deleteByParam(XcChildReq req) {

		Map<String, Object> mapDel = new HashMap<String, Object>();
		mapDel.put("schoolId", req.getSchoolId());
		mapDel.put("lineId", req.getLineId());
		List<Long> childIdList = dao.findXcChildIdByParam(mapDel);
		if (childIdList.size() > 0) {
			// 删除该班级该线路数据
			mapDel.put("list", childIdList);
			dao.deletByParam(mapDel);
		}

	}

	public List<IdNameVO> findChildBaseByParam(Map<String, Object> param) {

		return dao.queryChildBaseByParam(param);
	}

	public List<SchoolChildRideVo> findAllChildByParam(Map<String, Object> param, Long schoolId) {

		param.put("schoolId", schoolId);
		List<SchoolChildRideVo> allList = new ArrayList<SchoolChildRideVo>();
		List<ClassVo> classVoList = classMapper.getClassBySchoolId(schoolId);

		if (!classVoList.isEmpty()) {
			for (ClassVo clazz : classVoList) {
				SchoolChildRideVo allvo = new SchoolChildRideVo();
				Long classId = clazz.getId();
				List<Long> is = new ArrayList<Long>();
				is.add(classId);
				allvo.setClassId(classId);
				allvo.setClassName(clazz.getName());
				param.put("classIds", is);
				List<ChildRideExtVo> childRideExtVo = this.findChildInfoNeedByParam(param);
				allvo.setChildRideExtVo(childRideExtVo);
				allList.add(allvo);
			}
		}
		return allList;
	}
}
