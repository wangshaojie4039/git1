package cn.imexue.ec.web.service.xc.xcBus;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.mongo.xc.XcLineStatusMongoDao;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataDuplicationException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.XcBusMapper;
import cn.imexue.ec.common.mapper.ec.XcLineMapper;
import cn.imexue.ec.common.model.XcBus;
import cn.imexue.ec.common.model.mongo.XcLineStatusRecord;
import cn.imexue.ec.common.model.vo.XcBusVo;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.service.xc.xcChild.XcChildManagerService;
import cn.imexue.ec.web.web.controller.xc.xcBus.req.XcBusReq;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq;

/**
 * 文件名称： cn.imexue.ec.web.service.XcBus.XcBusManagerService.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月7日</br>
 * 功能说明： 校车管理Service <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Service
@Transactional(readOnly = true)
public class XcBusManagerService extends CRUDService<XcBus, XcBusMapper> {

	@Resource
	private XcLineMapper xcLineMapper;

	@Resource
	private XcChildManagerService xcChildManagerService;

	@Resource
	private XcLineStatusMongoDao xcLineStatusMongoDao;

	public List<? extends XcBusVo> pageLists(Map<String, Object> param) {

		List<? extends XcBusVo> list = dao.pageLists(param);
		return list;
	}

	@Transactional
	public Result changeXcBus(Long id, Integer versionNo, Integer flag) {

		Result result = new Result();
		XcBus xcBus = new XcBus();
		xcBus.setId(id);
		xcBus.setVersionNo(versionNo);
		if (flag == 1) {
			Criteria criteria = Criteria.where("busId").is(id);
			criteria.and("schoolId").is(LoginUtil.getSchoolId());
			criteria.and("isEnd").is(Constants.NO_BYTE);
			Query query2 = Query.query(criteria);
			List<XcLineStatusRecord> list = xcLineStatusMongoDao.query(query2);
			if (list.size() > 0) {
				throw new AppChkException(7001, "xcLine.isEnd.active");
			}
			xcBus.setIsDelete(Constants.YES_BYTE); // 删除
			XcBus xc = this.queryXcBusById(id);
			XcChildReq req = new XcChildReq();
			req.setLineId(xc.getLineId());
			req.setSchoolId(LoginUtil.getSchoolId());
			xcChildManagerService.deleteByParam(req);

		}
		if (flag == 2) {
			xcBus.setStatus(Constants.YES_BYTE); // 停用
		}

		if (flag == 3) {
			xcBus.setStatus(Constants.NO_BYTE); // 启用
		}

		dao.update(xcBus);

		return result;

	}

	@Transactional
	public Result SaveOrUpdateXcBus(XcBusReq req, Integer flag) {

		Result result = new Result();
		XcBus xcBus = new XcBus();
		BeanUtils.copyProperties(req, xcBus);
		if (xcLineMapper.getById(req.getLineId()) == null) {
			throw new DataNoFountException("线路：" + req.getLineName());
		}
		if (flag == 1 && dao.getByPlateNumber(req.getPlateNumber()).size() > 0) {
			throw new DataDuplicationException("车牌号：" + req.getPlateNumber());
		}
		if (flag == 1 && dao.getByBusAttendence(req.getAttendanceDeviceNo()).size() > 0) {
			throw new DataDuplicationException("设备编号：" + req.getAttendanceDeviceNo());
		}
		if (flag == 2 && dao.getByPlateNumber(req.getPlateNumber()).size() > 1) {
			throw new DataDuplicationException("车牌号：" + req.getPlateNumber());
		}
		if (flag == 2 && dao.getByBusAttendence(req.getAttendanceDeviceNo()).size() > 1) {
			throw new DataDuplicationException("设备编号：" + req.getAttendanceDeviceNo());
		}

		if (flag == 1) {
			dao.insert(xcBus);

		} else {
			XcBus xb = dao.getById(req.getId());
			if (xb == null) {
				throw new AppChkException(5001, "xcBus.id.noexist", req.getId());
			}
			Criteria criteria = Criteria.where("busId").is(req.getId());
			criteria.and("schoolId").is(LoginUtil.getSchoolId());
			criteria.and("isEnd").is(Constants.NO_BYTE);
			Query query2 = Query.query(criteria);
			List<XcLineStatusRecord> list = xcLineStatusMongoDao.query(query2);
			if (list.size() > 0) {
				throw new AppChkException(7001, "xcLine.isEnd.active");
			}
			dao.update(xcBus);

		}
		return result;

	}

	public XcBus queryXcBusById(Long id) {

		return dao.getById(id);
	}

	public List<XcBusVo> findAllXcBus(Map<String, Object> param) {

		return dao.queryAllXcBus(param);
	}

	public List<XcBusVo> findFreeXcBus(Long schoolId) {

		return dao.queryFreeXcBus(schoolId);
	}

	public XcBus findXcBusById(Long id) {

		return dao.getById(id);
	}

	public XcBus findXcBusByParam(Long schoolId, Long lineId, Integer status) {

		return dao.queryXcBusByParam(schoolId, lineId, status);
	}

}
