package cn.imexue.ec.web.service.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.CardMapper;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.DeviceCameraMapper;
import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.mapper.ec.XcDriverMapper;
import cn.imexue.ec.common.model.Card;
import cn.imexue.ec.common.model.Child;
import cn.imexue.ec.common.model.XcDriver;
import cn.imexue.ec.common.model.vo.CardVo;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.service.sys.SysCodeService;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.web.service.CRUDService;

@Service
@Transactional(readOnly = true)
public class CardService extends CRUDService<CardVo, CardMapper> {

    @SuppressWarnings("unused")
    private Logger log = LoggerFactory.getLogger(getClass());


    @Resource
    private DeviceCameraMapper deviceCameraMapper;

    @Resource
    private ChildMapper childMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private SchoolMapper schoolMapper;

    @Resource
    private CardMapper cardMapper;

    @Resource
    private XcDriverMapper xcDriverMapper;

    @Override
    public List<? extends CardVo> pageList(Map<String, Object> param) {

	List<? extends CardVo> pageLists = super.pageList(param);
	for (CardVo vo : pageLists) {

	    if ("C".equals(vo.getUserType())) {

		StringBuffer userName = new StringBuffer();
		ChildVo childVo = childMapper.selectById(vo.getUserId());
		if (childVo != null) {
		    userName.append(childVo.getName());
		    userName.append(childVo.getSex() == null ? "" : childVo
			    .getSex() == 1 ? ",男" : ",女");
		    ClassVo classVo = classMapper.getById(childVo.getClassId());
		    if (classVo != null) {
			userName.append("," + classVo.getName());
		    }

		    vo.setUserName(userName.toString());
		    if (vo.getUserType() != null) {
			if (vo.getUserType().equals("T")) {
			    vo.setUserRole("");
			}

		    }
		    vo.setUserRole("幼儿");
		    // vo.setUserRole(Constants.APP_USER_ROLE_TEACHER.equals(vo.getUserType())
		    // ? "老师" : "幼儿");
		} else {
		    // if (vo.getUserId().equals(0L)) {
		    vo.setUserId(null);
		    vo.setHolderRoleCode("");
		    vo.setHolderRoleName("");
		    // }

		}
	    } else if ("D".equals(vo.getUserType())) {
		XcDriver driver = xcDriverMapper.getById(vo.getUserId());
		if (driver != null) {
		    String driverName = driver.getDriverName();
		    vo.setUserName(driverName);
		} else {
		    vo.setUserId(null);
		    vo.setHolderRoleCode("");
		    vo.setHolderRoleName("");

		}

		vo.setUserRole("司机");
	    } else {
		// if (vo.getUserId().equals(0L)) {
		vo.setUserId(null);
		vo.setHolderRoleCode("");
		vo.setHolderRoleName("");
		// }

	    }

	}
	return pageLists;
    }

    @Override
    public CardVo getById(Long id) {

	CardVo device = super.getById(id);
	return device;
    }

    @Transactional
    public Long updateDevice(Card card1) {

	CardVo card = dao.getByCardNo(card1.getCardNo());

	if (card == null) {
	    throw new AppChkException(3088, "attendance.cardNo.not.exist",
		    card1.getCardNo());
	} else {
	    if (!card1.getSchoolId().equals(card.getSchoolId())) {
		throw new AppChkException(3043, "child.card.not.school",
			card1.getCardNo());
	    } else if (card.getIsActive().equals(Constants.IS_NOT_ACTIVE)) {
		throw new AppChkException(9002, "child.card.notactive",
			card1.getCardNo());
	    }
	    if (!card1.getUserId().equals(0L)) {
		Child child = childMapper.selectById(card1.getUserId());
		if (child != null) {
		    card.setUserType(card1.getUserType());

		    card.setUserId(card1.getUserId());
		    card.setVersionNo(card1.getVersionNo());
		    card.setHolderRoleCode(card1.getHolderRoleCode());
		    if (card.getHolderRoleCode() != null) {
			if (card.getHolderRoleCode().equals(
				SysCodeService.CHILD_REL_OTHER)) {
			    card.setHolderRoleName(card1.getHolderRoleName());
			} else {
			    card.setHolderRoleName(SysCodeService.getByTypeAndCode(
				    SysCodeService.CHILD_REL,
				    card1.getHolderRoleCode()));
			}
		    }

		    card.setBindTime(new Date());
		    if (card1.getUserType().equals("C")) {
			card.setUserName(child.getName());
		    }
		    dao.update(card);
		} else {
		    throw new AppChkException(9001, "child.card.userId");
		}
	    }

	    return card.getId();

	}

    }

    @Transactional
    public void deleteDevice(String cardNo, Integer versionNo) {

	CardVo card = dao.getByCardNo(cardNo);
	if (card == null) {
	    throw new AppChkException(3001, "attendance.cardNo.not.exist",
		    cardNo);
	}
	if (!card.getSchoolId().equals(card.getSchoolId())) {
	    throw new AppChkException(3043, "child.card.not.school",
		    card.getCardNo());
	}

	dao.deleteById(card.getId());
    }

    public List<? extends ChildVo> selectChildList(Map<String, Object> param) {

	List<? extends ChildVo> childs = childMapper.selectChildList(param);
	/*
	 * for (ChildVo vo : childs) { if (vo.getId() != null) { Class class1 = classMapper.getById(vo.getClassId()); if
	 * (class1 != null) { vo.setClassName(class1.getName()); vo.setChildId(vo.getId()); } } }
	 */
	return childs;
    }

    @Transactional
    public CardVo create(CardVo cardvo) {

	if (!cardvo.getCardNo().matches("\\d{10}")) {
	    throw new AppChkException(3017, "child.card.length.noCorrect",
		    cardvo.getCardNo());
	}
	CardVo card = cardMapper.getByCardNo(cardvo.getCardNo());
	if (card != null) {
	    if (cardvo.getUserType().equals(Constants.CARD_USER_TYPE_DRIVER)) {
		throw new AppChkException(3045, "device.card.isbinding",
			cardvo.getCardNo());
	    }
	    if (!cardvo.getSchoolId().equals(card.getSchoolId())) {
		throw new AppChkException(3043, "child.card.not.school",
			cardvo.getCardNo());
	    }
	    cardvo.setId(card.getId());
	    updateDevice(cardvo);
	    return cardvo;
	}
	card = new CardVo();
	card.setSchoolId(cardvo.getSchoolId());
	card.setCardNo(cardvo.getCardNo());
	card.setIsActive(Constants.YES_BYTE);
	if (cardvo.getUserId() == null) {
	    cardvo.setUserId(0L);
	}
	card.setUserId(cardvo.getUserId());
	card.setCustomerId(0L);
	card.setUserType(cardvo.getUserType());
	card.setBindTime(new Date());
	card.setHolderRoleCode(cardvo.getHolderRoleCode());
	card.setHolderRoleName(cardvo.getHolderRoleName());
	cardMapper.insertCard(card);

	return cardvo;
    }

    public CardVo getByCardNo(String cardNo, Long schoolId) {

	CardVo card = cardMapper.getByCardNo(cardNo);
	if (card == null) {
	    return card;
	    // 2017-07-17注释 查询卡号的时候不报错
	    // throw new AppChkException(3001, "attendance.cardNo.not.exist",
	    // cardNo);
	} else {
	    if (card.getSchoolId() != null
		    && !card.getSchoolId().equals(schoolId)) {
		throw new AppChkException(3043, "child.card.not.school",
			card.getCardNo());
	    }
	    if (card.getIsActive().equals(Constants.IS_NOT_ACTIVE)) {
		throw new AppChkException(9002, "child.card.notactive",
			card.getCardNo());
	    }

	    if (card.getUserType() != null) {

		if ("C".equals(card.getUserType())) {
		    StringBuffer userName = new StringBuffer();
		    ChildVo childVo = childMapper.selectById(card.getUserId());
		    if (childVo != null) {
			userName.append(childVo.getName() + ",");
			userName.append(childVo.getSex() == null ? "" : childVo
				.getSex() == 1 ? "男" : "女");
			ClassVo classVo = classMapper.getById(childVo
				.getClassId());
			if (classVo != null) {
			    userName.append("," + classVo.getName());
			}

			card.setUserName(userName.toString());
			card.setUserRole("幼儿");
		    } else {
			card.setUserId(0l);
			card.setUserType("");
		    }
		} else if ("D".equals(card.getUserType())) {
		    XcDriver byId = xcDriverMapper.getById(card.getUserId());
		    if (byId != null) {
			card.setUserName(byId.getDriverName());
			card.setUserRole("司机");
		    } else {
			card.setUserId(0l);
			card.setUserType("");
		    }
		} else {
		    card.setUserId(0l);
		    card.setUserType("");
		}
	    }
	    return card;
	}

    }

    /**
     * @exception 5002 卡未激活
     * @exception 5003 卡已绑定
     * @exception 5005 卡号错误
     * @exception 5004 卡不属于本学校
     */
    @Override
    @Transactional
    public void save(CardVo card) {

	// 卡号必须为10位数字
	if (!card.getCardNo().matches("\\d{10}")) {
	    throw new AppChkException(5005, "device.card.length.noCorrect",
		    card.getCardNo());
	}
	CardVo card2 = cardMapper.getByCardNo(card.getCardNo());
	if (card2 == null) {
	    card.setCustomerId(0l);
	    card.setIsActive(Constants.YES_BYTE);
	    if (card.getUserId() != null && card.getUserId() > 0) {
		card.setBindTime(new Date());
	    }
	    cardMapper.insert(card);
	} else if (Constants.IS_NOT_ACTIVE.equals(card2.getIsActive())) {
	    throw new AppChkException(5002, "device.card.notactive", card.getCardNo());
	} else if (card2.getUserId() != 0
		&& !card2.getUserId().equals(card.getUserId())) {
	    throw new AppChkException(5003, "device.card.isbinding", card.getCardNo());
	} else if (!card2.getSchoolId().equals(card.getSchoolId())) {
	    throw new AppChkException(5004, "device.card.notInSchool",
		    card.getCardNo());
	} else {
	    card.setId(card2.getId());
	    if (card.getUserId() != null && card.getUserId() > 0) {
		card.setBindTime(new Date());
	    }

	    cardMapper.update(card);
	}

    }

}
