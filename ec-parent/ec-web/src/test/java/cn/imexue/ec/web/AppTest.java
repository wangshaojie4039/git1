//package cn.imexue.ec.web;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.Assert;
//
//import cn.imexue.ec.common.resp.RespJson;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("dev")
//public class AppTest {
//
//	@Resource
//	private TestRestTemplate restTemplate;
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Test
//	public void test(){
//		RespJson json = restTemplate.getForObject("/getProvince", RespJson.class);
//		List<Map> province = (List<Map>) json.getData();
//		Assert.isTrue(json.getResult().equals(1));
//		Assert.isTrue(province.size()>0);
//		
//		int cityId = (int) province.get(0).get("id");
//		Map<String, Object> urlVariables = new HashMap<>();
//		urlVariables.put("id", cityId);
//		RespJson json2 = restTemplate.getForObject("/getCity?provinceId={id}", RespJson.class, urlVariables);
//		List<Map> citys = (List<Map>) json2.getData();
//		Assert.isTrue(json2.getResult().equals(1));
//		
//		int districtId = (int) citys.get(0).get("id");
//		Map<String, Object> urlVariables2 = new HashMap<>();
//		urlVariables2.put("id", districtId);
//		RespJson json3 = restTemplate.getForObject("/getDistrict?cityId={id}", RespJson.class, urlVariables2);
//		Assert.isTrue(json3.getResult().equals(1));
//	}
//	
//}
