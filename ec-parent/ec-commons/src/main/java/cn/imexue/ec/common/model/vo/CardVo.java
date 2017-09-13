package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.Card;

@Alias("CardVo")
public class CardVo extends Card {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= -8274334384362558842L;

	private String				userName;

	private String				userRole;

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getUserRole() {

		return userRole;
	}

	public void setUserRole(String userRole) {

		this.userRole = userRole;
	}

	@Override
	public String toString() {

		return "CardVo [userName=" + userName + ", userRole=" + userRole + "]";
	}

}
