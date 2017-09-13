package cn.imexue.ec.web.service.child;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.exception.ExcelAppException;
import cn.imexue.ec.common.mapper.ec.CardMapper;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.model.vo.CardVo;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.web.web.controller.child.req.ChildCardExcel;
import cn.imexue.ec.web.web.excel.ExcelException;

@Service
@Transactional(readOnly=true)
public class ChildCardService {

	@Resource
	private CardMapper cardMapper;
	
	@Resource
	private ClassMapper classMapper;
	
	@Resource
	private ChildMapper childMapper;
	
	@Resource
	private ChildService childService;
	

	
	/**
	 * @exception 1011 幼儿不存在 
	 * @param childId
	 * @param schoolId
	 * @return
	 */
	public List<CardVo> list(Long childId,Long schoolId){
		ChildVo child = childMapper.getChild(childId, schoolId);
		if(child==null){
			throw new DataNoFountException("幼儿");
		}
		List<CardVo> cards = cardMapper.getCardByChild(childId);
		
		return cards;
	}
	
	/**
	 * 解除绑定
	 * @param id,version
	 */
	@Transactional
	public void dismiss(CardVo card){
		cardMapper.updateChildCardToNull(card);
	}
	
	/**
	 * @exception 3015 卡未激活
	 * @exception 3016 卡未绑定
	 * @exception 3017 卡号错误
	 * @exception 3018 卡不属于本学校
	 */
	@Transactional
	public void save(String cardNo,Long childId,Long schoolId){
		//卡号必须为10位数字
		if(!cardNo.matches("\\d{10}"))
			throw new AppChkException(3017, "child.card.length.noCorrect", cardNo);
		CardVo card = cardMapper.getByCardNo(cardNo);
		if(card==null){
			card = new CardVo();
			card.setCardNo(cardNo);
			card.setBindTime(new Date());
			card.setSchoolId(schoolId);
			card.setUserType("C");
			card.setUserId(childId);
			cardMapper.insertCard(card);
		}else if(Constants.IS_NOT_ACTIVE.equals(card.getIsActive())){
			throw new AppChkException(3015, "child.card.notactive", cardNo);
		}else if(!card.getSchoolId().equals(schoolId)){
			throw new AppChkException(3018, "child.card.notInSchool", cardNo);
		}else if(card.getUserId()!=null&&!card.getUserId().equals(0l)){
			if(card.getUserId().equals(childId)){
				//新绑定幼儿和原来幼儿相同，不做处理
				return;
			}
			throw new AppChkException(3016, "child.card.isbinding", cardNo);
		}else{
			card.setBindTime(new Date());
			card.setUserType("C");
			card.setUserId(childId);
			
			cardMapper.updateChildCard(card);
		}
		
	}
	
	@Transactional
	public void  importChildCard(List<ChildCardExcel> list,Long schoolId)
		{
			for (ChildCardExcel ex : list) {
				//获得班级信息
				ClassVo cls = classMapper.getByName(null, ex.getClassName(), schoolId);
				if (cls == null) {
					throw new ExcelException("excel.noFound.class", ex.getClassName());
				}
				ChildVo child=new ChildVo();
			 	child.setName(ex.getChildName());
				child.setSchoolId(schoolId);
				child.setClassId(cls.getId());
				child.setIdCardNo(ex.getIdCardNo());
				//新增幼儿
				childService.save(child, true);
				try{
					//新增卡
					save(ex.getCardNo(), child.getId(), schoolId);
				}catch(AppChkException e){
					throw new ExcelAppException(e,ex.getCardNo());
				}
		}
	}
}
