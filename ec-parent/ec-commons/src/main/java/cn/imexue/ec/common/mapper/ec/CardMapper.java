package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.Card;
import cn.imexue.ec.common.model.vo.CardVo;

@Mapper
public interface CardMapper extends BaseMapper<CardVo> {

    void updateChildCardToNull(Card card);

    List<CardVo> getCardByChild(Long childId);

    void updateChildCard(CardVo card);

    CardVo getByCardNo(@Param("cardNo") String cardNo);

    void insertCard(CardVo card);
    
    /**
     * 
     *
     * 方法描述: [将卡的用户清空]</br>
     * 初始作者: wangshaojie<br/> 
     * 创建日期: 2017年8月4日-上午10:26:07<br/> 
     * 开始版本: 2.0.0<br/> 
     * =================================================<br/>
     * 修改记录：<br/>
     * 修改作者         日期         修改内容<br/>
     * ================================================<br/>
     * @param cardNo
     * void
     *
     */
    void updateUserToNull(String cardNo);

}
