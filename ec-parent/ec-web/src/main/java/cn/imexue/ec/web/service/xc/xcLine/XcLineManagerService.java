package cn.imexue.ec.web.service.xc.xcLine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.mongo.MongoBaseDao;
import cn.imexue.ec.common.dao.mongo.xc.XcAttendanceMongoDao;
import cn.imexue.ec.common.dao.mongo.xc.XcLineStatusMongoDao;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataDuplicationException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.XcChildMapper;
import cn.imexue.ec.common.mapper.ec.XcDriverMapper;
import cn.imexue.ec.common.mapper.ec.XcLineMapper;
import cn.imexue.ec.common.model.XcDriver;
import cn.imexue.ec.common.model.XcLine;
import cn.imexue.ec.common.model.mongo.XcChildAttendance;
import cn.imexue.ec.common.model.mongo.XcLineStatusRecord;
import cn.imexue.ec.common.model.vo.XcBusItemVo;
import cn.imexue.ec.common.model.vo.XcBusVo;
import cn.imexue.ec.common.model.vo.XcLineBusVo;
import cn.imexue.ec.common.model.vo.XcLineItemVo;
import cn.imexue.ec.common.model.vo.XcLineVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.CRUDService;
import cn.imexue.ec.web.service.xc.xcBus.XcBusManagerService;
import cn.imexue.ec.web.service.xc.xcChild.XcChildManagerService;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq;
import cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineReq;

/**
 * 文件名称： cn.imexue.ec.web.service.xc.xcLine.XcLineService.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路service<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Service
@Transactional(readOnly = true)
public class XcLineManagerService extends CRUDService<XcLine, XcLineMapper> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private XcBusManagerService xcBusManagerService;

	@Resource
	private XcChildManagerService xcChildManagerService;

	@Resource
	private XcChildMapper xcChildMapper;

	@Resource
	private XcDriverMapper xcDriverMapper;

	@Resource
	private MongoBaseDao mongoBaseDao;

	@Resource
	private XcAttendanceMongoDao xcAttendanceMongoDao;

	@Resource
	private XcLineStatusMongoDao xcLineStatusMongoDao;

	public List<? extends XcLineVo> pageLists(Map<String, Object> param) {

		List<? extends XcLineVo> list = dao.pageLists(param);
		return list;

	}

	/**
	 * 方法描述: [删除线路.]<br/>
	 * 初始作者: 钟小平<br/>
	 * 创建日期: 2017年7月27日-下午2:51:37<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param id
	 *            线路id
	 * @param versionNo
	 *            版本号
	 */
	@Transactional
	public void deleteXcLine(Long id, Integer versionNo) {

		XcLine xl = dao.getById(id);
		if (xl == null) {
			throw new DataNoFountException("xcLine");
		}
		Criteria criteria = Criteria.where("lineId").is(id);
		criteria.and("schoolId").is(LoginUtil.getSchoolId());
		criteria.and("isEnd").is(Constants.NO_BYTE);
		Query query2 = Query.query(criteria);
		List<XcLineStatusRecord> list = xcLineStatusMongoDao.query(query2);
		if (list.size() > 0) {
			throw new AppChkException(7001, "xcLine.isEnd.active");
		}

		XcLine x = new XcLine();
		x.setId(id);
		x.setVersionNo(versionNo);
		x.setIsDelete(Constants.YES_BYTE);
		dao.update(x);
		XcChildReq req = new XcChildReq();
		req.setLineId(id);
		req.setSchoolId(LoginUtil.getSchoolId());
		xcChildManagerService.deleteByParam(req);// 删除幼儿

	}

	/**
	 * 方法描述: [保持校车线路.]<br/>
	 * 初始作者: 钟小平<br/>
	 * 创建日期: 2017年7月27日-下午2:52:11<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param req
	 *            void
	 */
	@Transactional
	public XcLine saveXcLine(XcLineReq req) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", req.getSchoolId());
		param.put("lineName", req.getLineName());
		RespJson result = this.validateName(param);
		if (result.getResult() == 0) {
			throw new DataDuplicationException(req.getLineName());
		}
		XcLine line = new XcLine();
		BeanUtils.copyProperties(req, line);
		dao.insert(line);

		return line;
		/*
		 * XcBusChangeReq bus = req.getXcBusChangeReq();
		 * if (bus != null) {
		 * XcBusReq reqbus = new XcBusReq();
		 * reqbus.setId(bus.getId());
		 * reqbus.setVersionNo(bus.getVersionNo());
		 * reqbus.setLineId(line.getId());
		 * xcBusManagerService.SaveOrUpdateXcBus(reqbus, 2);
		 * }
		 */
		/*
		 * List<XcLineChildAllReq> list = req.getXcLineChildAllReqList();
		 * for (XcLineChildAllReq xc : list) {
		 * List<Long> ids = new ArrayList<Long>();
		 * for (XcLineChildReq ls : xc.getXcLineChildReqList()) {
		 * ids.add(ls.getXcChildId());
		 * }
		 * XcChildReq xls = new XcChildReq();
		 * xls.setClassId(xc.getClassId());
		 * xls.setIds(ids);
		 * xcChildManagerService.SaveXcChild(xls);
		 * }
		 */

	}

	/**
	 * 方法描述: [查询线路.]<br/>
	 * 初始作者: 钟小平<br/>
	 * 创建日期: 2017年7月27日-下午2:52:51<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param schoolId
	 *            学校id
	 * @param id
	 * @return
	 *         XcLineItemVo 校车线路详情vo
	 */
	public XcLineItemVo getXcLine(Long schoolId, Long id) {

		XcLineItemVo vo = new XcLineItemVo();
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("lineId", id);
		param.put("schoolId", schoolId);
		XcLine line = dao.getById(id);
		if (line == null) {
			throw new DataNoFountException("xcLine");
		}
		BeanUtils.copyProperties(line, vo);
		List<XcBusVo> bus = xcBusManagerService.findAllXcBus(param);
		if (bus.size() > 0) {
			XcBusItemVo busvo = new XcBusItemVo();
			busvo.setPlateNumber(bus.get(0).getPlateNumber());
			busvo.setModel(bus.get(0).getModel());
			busvo.setNuclearSeating(bus.get(0).getNuclearSeating());
			busvo.setId(bus.get(0).getId());
			vo.setXcBusItemVo(busvo);
		}

		return vo;
	}

	public List<XcLineBusVo> findAllBySchoolId(Long schoolId) {

		return dao.findAllBySchoolId(schoolId);

	}

	@Transactional
	public void updateXcLine(XcLineReq req) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", req.getSchoolId());
		param.put("lineName", req.getLineName());
		param.put("id", req.getId());
		RespJson result = this.validateName(param);
		if (result.getResult() == 0) {
			throw new DataDuplicationException(req.getLineName());
		}
		Criteria criteria = Criteria.where("lineId").is(req.getId());
		criteria.and("schoolId").is(req.getSchoolId());
		criteria.and("isEnd").is(Constants.NO_BYTE);
		Query query2 = Query.query(criteria);
		List<XcLineStatusRecord> list = xcLineStatusMongoDao.query(query2);
		if (list.size() > 0) {
			throw new AppChkException(7001, "xcLine.isEnd.active");
		}

		XcLine line = new XcLine();
		BeanUtils.copyProperties(req, line);
		dao.update(line);
		/*
		 * XcBusChangeReq bus = req.getXcBusChangeReq();
		 * if (bus != null) {
		 * XcBusReq reqbus = new XcBusReq();
		 * reqbus.setId(bus.getId());
		 * reqbus.setVersionNo(bus.getVersionNo());
		 * reqbus.setLineId(line.getId());
		 * xcBusManagerService.SaveOrUpdateXcBus(reqbus, 2);
		 * }
		 * List<Long> list = req.getIds();
		 * if (null != list && list.size() > 0) {
		 * XcChildReq xls = new XcChildReq();
		 * xls.setSchoolId(req.getSchoolId());
		 * xls.setIds(req.getIds());
		 * xls.setLineId(req.getId());
		 * xcChildManagerService.SaveXcChild(xls);
		 * }
		 */

	}

	/**
	 * 方法描述: [校验线路名称是否重复.]<br/>
	 * 初始作者: 钟小平<br/>
	 * 创建日期: 2017年7月27日-下午2:55:28<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param param
	 * @return
	 *         RespJson
	 */
	public RespJson validateName(Map<String, Object> param) {

		RespJson rep = new RespJson();
		List<XcLine> list = dao.queryAllByParam(param);
		if (param.get("id") != null && dao.getById((Long) param.get("id")).getLineName().equals(param.get("lineName"))) {
			rep = RespJsonFactory.buildSuccess();
			return rep;
		}
		if (list.size() > 0) {
			rep = RespJsonFactory.buildFailure(param.get("lineName") + "已存在！");
		} else {
			rep = RespJsonFactory.buildSuccess();
		}
		return rep;
	}

	public XcLineItemVo getTrack(Long schoolId, Long lineId) {

		XcLineItemVo xcLine = getXcLine(schoolId, lineId);
		Long driverId = xcLine.getDriverId();
		if (driverId != null) {
			XcDriver driver = xcDriverMapper.getById(driverId);
			xcLine.setDriver(driver);
		}
		Query query = Query.query(Criteria.where("lineId").is(lineId));
		query.with(new Sort(new Order(Direction.DESC, "createTime")));
		query.limit(1);
		List<XcLineStatusRecord> find = mongoBaseDao.getMongoTemplate().find(query, XcLineStatusRecord.class, "xc_line_status_record");
		if (!find.isEmpty()) {
			xcLine.setLineStatus(find.get(0));
		}
		// 统计幼儿
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String attDate = format2.format(new Date());
		// 判断上午还是下午
		Calendar instance = Calendar.getInstance();
		Date now = instance.getTime();
		instance.set(Calendar.HOUR_OF_DAY, 12);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		Date noon = instance.getTime();
		Integer attType;
		if (now.after(noon)) {
			// 下午
			attType = 3;
		} else {
			attType = 1;
		}

		Criteria criteria = Criteria.where("attDay").is(attDate);
		criteria.and("schoolId").is(schoolId);
		criteria.and("attType").is(attType);
		Query query2 = Query.query(criteria);
		List<XcChildAttendance> list = xcAttendanceMongoDao.query(query2);
		log.debug("统计应乘信息:{},线路:{}", list.size(), xcLine.getLineName());
		xcLine.setTotalRide(list.size());
		if (!find.isEmpty()) {
			Query query3 = Query.query(Criteria.where("lineStatusRecordId").is(find.get(0).getId()));
			long count = mongoBaseDao.getMongoTemplate().count(query3, "xc_child_card_record");
			xcLine.setRide((int) count);
		} else {
			xcLine.setRide(0);
		}

		return xcLine;
	}

}
