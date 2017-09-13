package cn.imexue.ec.web.web.controller.device;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.Card;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.CardVo;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.device.CardService;
import cn.imexue.ec.web.web.controller.child.req.ChildReq;
import cn.imexue.ec.web.web.controller.device.req.CardQuery;
import cn.imexue.ec.web.web.controller.device.req.PointCardReq;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

/**
 * 考勤设备类
 *
 * @author hl
 */
@Api(value = "考勤设备类")
@RestController
@RequestMapping("card")
@Role(RoleType.D)
public class CardController {

    @Resource
    private CardService cardService;

    /**
     * @api {POST} /card/list 分页获取设备信息
     * @apiGroup card
     * @apiName list
     * @apiParam {String} deviceNo 卡号
     * @apiSuccess {String} list 参考<a href="#api-card-get">get</a>
     * @apiSuccess {String} cardNo 卡号
     * @apiSuccess {String} userName 拥有者
     * @apiSuccess {String} userRole 拥有者角色
     * @apiSuccess {Date} bindTime 分配时间
     * @apiSuccess {Long} id 卡Id
     * @apiSuccess {Number} versionNo 版本号
     * @apiSuccessExample {json} Success-Response:
    					{
    					}
     *
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public RespJson list(Page<CardVo> page, @RequestBody CardQuery query) {

	Long schoolId = LoginUtil.getSchoolId();
	page.getSearch().put("schoolId", schoolId);
	page.checkOrder(CardVo.class);
	cardService.pageList(page.getSearch());

	return RespJsonFactory.buildSuccess(page);
    }

    /**
     * @api {POST} /card/pointCard 分配卡
     * @apiGroup card
     * @apiName pointCard
     * @apiParam {String} cardNo 卡号
     * @apiParam {String} userType 用户类型(老师或幼儿： T - 老师、C - 幼儿)
     * @apiParam {Long} userId 用户ID
     * @apiParam {String} holderRoleCode (持有者 	（1）爸爸：BB
                                                                                                                                                （2）妈妈：MM
                                                                                                                                                （3）爷爷：YY
                                                                                                                                                （4）奶奶：NN
                                                                                                                                                （5）外公：WG
                                                                                                                                                （6）外婆：WP
    						（7）其它：OTHER
                			)
     * @apiParam {Number} versionNo 版本
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "cardNo":"55"
     *                  "userType": "T",
     *                  "userId": "1",
     *                  "versionNo" : 1,
     *                  "holderRoleCode":"班级/。。。"
     *                  "holderRoleName":"持卡人"
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = " 分配卡")
    @RequestMapping(value = "pointCard", method = RequestMethod.POST)
    public RespJson pointCard(@RequestBody @Valid PointCardReq deviceReq) {

	Card device2 = new Card();
	// 拷贝同名的值,注意确保没有多余的值
	BeanUtils.copyProperties(deviceReq, device2);
	Long schoolId = LoginUtil.getSchoolId();
	device2.setSchoolId(schoolId);
	Long cardId = cardService.updateDevice(device2);
	return RespJsonFactory.buildSuccess(cardId);
    }

    /**
     * @api {POST} /card/deleteCard   删除卡
     * @apiGroup card
     * @apiName deleteCard
     * @apiParam {String} cardNo 卡号
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "cardNo": 1,
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "删除卡")
    @RequestMapping(value = "deleteCard", method = RequestMethod.POST)
    public RespJson deleteCard(@RequestBody PointCardReq cardReq) {

	// 拷贝同名的值,注意确保没有多余的值
	cardService.deleteDevice(cardReq.getCardNo(), cardReq.getVersionNo());
	return RespJsonFactory.buildSuccess();
    }

    /**
     * @api {POST} /card/childList 获取学社列表
     * @apiGroup card
     * @apiName childList
     * @apiParam {Long} schoolId 学校Id
     * @apiParam {String} name 学生名字
     * @apiSuccess {String} list 参考<a href="#api-card-get">get</a>
     * @apiSuccessExample {json} Success-Response:
    					{
    					}
     */
    @RequestMapping(value = "childList", method = RequestMethod.POST)
    public RespJson childList(@RequestBody ChildReq childReq) {

	Long schoolId = LoginUtil.getSchoolId();
	Map<String, Object> map = new HashMap<>();
	map.put("schoolId", schoolId);
	List<? extends ChildVo> list = cardService.selectChildList(map);
	map.clear();
	map.put("list", list);
	return RespJsonFactory.buildSuccess(map);
    }

    /**
     * @api {POST} /card/createCard 新建卡
     * @apiGroup card
     * @apiName cardCreate
     * @apiParam {String} cardNo 卡号
     * @apiParam {String} holderRoleCode 持卡人角色
     * @apiParam {Long} userId 用户Id
     * @apiParam {String} userType 用户类型
     * @apiSuccess {String} list 参考<a href="#api-card-get">get</a>
     * @apiParamExample {json} Request-Example:
    			{
    			  "cardNo": "string",
    			  "holderRoleCode": "string",
    			  "userId": 0,
    			  "userType": "string",
    			}
     * @apiSuccessExample {json} Success-Response:
    					{
    					  "result": 1,
    					  "code": null,
    					  "msg": "成功",
    					  "data": {
    					    "id": null,
    					    "versionNo": 1,
    					    "cardNo": "9323299000",
    					    "schoolId": 1048,
    					    "customerId": null,
    					    "userType": "C",
    					    "userId": 1024,
    					    "orderNo": null,
    					    "confirmTime": null,
    					    "holderRoleCode": "BB",
    					    "holderRoleName": null,
    					    "bindTime": null,
    					    "isActive": null,
    					    "userName": null,
    					    "userRole": null
    					  }
    					}
     */
    @RequestMapping(value = "createCard", method = RequestMethod.POST)
    public RespJson create(@RequestBody PointCardReq cardReq) {

	CardVo device2 = new CardVo();
	// 拷贝同名的值,注意确保没有多余的值
	BeanUtils.copyProperties(cardReq, device2);

	Long schoolId = LoginUtil.getSchoolId();
	device2.setSchoolId(schoolId);
	CardVo cardVo = cardService.create(device2);
	return RespJsonFactory.buildSuccess(cardVo);
    }

    /**
     * @api {GET} /card/getByCardNo/{cardNo} 根据卡号获取
     * @apiGroup card
     * @apiName cardGetByNo
     *
     */
    @RequestMapping(value = "getByCardNo/{cardNo}", method = RequestMethod.GET)
    public RespJson getByCardNo(@PathVariable("cardNo") String cardNo) {

	Long schoolId = LoginUtil.getSchoolId();

	CardVo card = cardService.getByCardNo(cardNo, schoolId);

	return RespJsonFactory.buildSuccess(card);
    }

}
