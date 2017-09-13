package cn.imexue.ec.common.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.mapper.ec.CityMapper;
import cn.imexue.ec.common.mapper.ec.DistrictMapper;
import cn.imexue.ec.common.mapper.ec.ProvinceMapper;
import cn.imexue.ec.common.model.vo.IdNameVO;

/**
 * 省市区相关的服务
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年2月28日
 * @author lijianfeng
 * @version 1.0
 */
@Service
@Transactional(readOnly=true)
public class PcdService {
	private static final Logger log = LoggerFactory.getLogger(PcdService.class);
	
	private static Map<Long, String> PROVINCE_MAP;
	private static Map<Long, String> CITY_MAP;
	private static Map<Long, String> DISTRICT_MAP ;
	private static List<IdNameVO> PROVINCE_ID_NAME_LIST;
	private static Map<Long , List<IdNameVO>> PROVINCE_CITY_MAP;
	private static Map<Long , List<IdNameVO>> CITY_DISTRICT_MAP;
	
	@Resource
	private ProvinceMapper provinceMapper;
	
	@Resource
	private CityMapper cityMapper;
	
	@Resource
	private DistrictMapper districtMapper;
	
//	@PostConstruct
	public void init() {
		log.info("ready to init province/city/district");
		PROVINCE_MAP = new HashMap<Long, String>();
		CITY_MAP = new HashMap<Long, String>();
		DISTRICT_MAP = new HashMap<Long, String>();
		PROVINCE_ID_NAME_LIST=new ArrayList<IdNameVO>();
		PROVINCE_CITY_MAP=new HashMap<Long, List<IdNameVO>>();
		CITY_DISTRICT_MAP=new HashMap<Long, List<IdNameVO>>();
		
		List<IdNameVO> provinces = provinceMapper.selectAll();
		//获得所有的省信息
		PROVINCE_ID_NAME_LIST.addAll(provinces);
		
		for (IdNameVO vo: provinces) {
			PROVINCE_MAP.put(vo.getId(), vo.getName());
			
			//获得省下的所有市
			List<IdNameVO> cities=cityMapper.selectByProvinceId(vo.getId());
			PROVINCE_CITY_MAP.put(vo.getId(), cities);
		}
		
		List<IdNameVO> cities = cityMapper.selectAll();
		for (IdNameVO vo: cities) {
			CITY_MAP.put(vo.getId(), vo.getName());
			
			//获得市下的所有区
			List<IdNameVO>  districts=districtMapper.selectByCityId(vo.getId());
			CITY_DISTRICT_MAP.put(vo.getId(), districts);
		}
		
		List<IdNameVO> districts = districtMapper.selectAll();
		for (IdNameVO vo: districts) {
			DISTRICT_MAP.put(vo.getId(), vo.getName());
		}
		
		log.info("init province/city/district complete.");
	}
	
	@Cacheable(key="#id")
	public String getProvinceNameById(Long id) {
		Optional<IdNameVO> option = this.getProvince().stream().filter(x->x.getId().equals(id)).findFirst();
		if(option.isPresent()){
			return option.get().getName();
		}
		return null;
	}
	
	@Cacheable(key="#id")
	public String getCityNameById(Long id) {
		Optional<IdNameVO> option = cityMapper.selectAll().stream().filter(x->x.getId().equals(id)).findFirst();
		if(option.isPresent()){
			return option.get().getName();
		}
		return null;
	}
	@Cacheable(key="#id")
	public String getDistrictNameById(Long id) {
		Optional<IdNameVO> option = districtMapper.selectAll().stream().filter(x->x.getId().equals(id)).findFirst();
		if(option.isPresent()){
			return option.get().getName();
		}
		return null;
	}
	@Cacheable(key="#id")
	public List<IdNameVO> getCityByProvinceId(Long id) {
		List<IdNameVO> list = cityMapper.selectByProvinceId(id);
		if(list== null){
			return new ArrayList<>();
		}
		return list;
	}
	@Cacheable(key="#id")
	public List<IdNameVO> getDistrictByCityId(Long id) {
		List<IdNameVO> list = districtMapper.selectByCityId(id);
		if(list== null){
			return new ArrayList<>();
		}
		return list;
	}
	@Cacheable
	public List<IdNameVO> getProvince(){
		return provinceMapper.selectAll();
	}
	
}
