package cn.imexue.ec.web.web.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.vo.IdNameVO;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.service.sys.PcdService;

@RestController
public class AreaController {

	@Resource
	private PcdService pcdService;
	
	/**
	 * @api {GET} /getCity 获取城市列表
	 * @apiGroup common
	 * @apiName getCity
	 * 
	 * @apiParam {Number} provinceId 省份id
	 */
	@RequestMapping(value="/getCity",method=RequestMethod.GET)
	public RespJson getCity(Long provinceId) {
		List<IdNameVO> provinceId2 = pcdService.getCityByProvinceId(provinceId);
		return RespJsonFactory.buildSuccess(provinceId2);
	}

	/**
	 * @api {GET} /getDistrict 获取区域列表
	 * @apiGroup common
	 * @apiName getDistrict
	 * 
	 * @apiParam {Number} cityId 城市id
	 */
	@RequestMapping(value="/getDistrict",method=RequestMethod.GET)
	public RespJson getDistrict(Long cityId) {
		List<IdNameVO> districtByCityId = pcdService.getDistrictByCityId(cityId);
		
		return RespJsonFactory.buildSuccess(districtByCityId);
	}
	
	/**
	 * @api {GET} /getProvince 获取省份列表
	 * @apiGroup common
	 * @apiName getProvince
	 * 
	 * @apiSuccessExample  {json} Success-Response:
	 * [
	    {
	      "id": 1,
	      "name": "北京市"
	    },
	    {
	      "id": 2,
	      "name": "天津市"
	    },
	    ..
	    ]
	 */
	@RequestMapping(value="/getProvince",method=RequestMethod.GET)
	public RespJson getProvince(){
		List<IdNameVO> province = pcdService.getProvince();
		return RespJsonFactory.buildSuccess(province) ;
	}
}
