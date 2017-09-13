package cn.imexue.ec.common.model.vo;

import java.util.List;

import cn.imexue.ec.common.model.Dynamic;
import cn.imexue.ec.common.model.DynamicComment;

public class DynamicVO extends Dynamic  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6205118387544989203L;
	

	
	private String userInfo;
	
	private Integer commentCount ;
	
	private Integer  likeCount ;
	
	private List<String> likeNames;

	private List<DynamicComment> comments;
	
	private Byte isDirector;
	
	
	private List<String> classNames;

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}



	public List<String> getLikeNames() {
		return likeNames;
	}

	public void setLikeNames(List<String> likeNames) {
		this.likeNames = likeNames;
	}

	public List<DynamicComment> getComments() {
		return comments;
	}

	public void setComments(List<DynamicComment> comments) {
		this.comments = comments;
	}

	public Byte getIsDirector() {
		return isDirector;
	}

	public void setIsDirector(Byte isDirector) {
		this.isDirector = isDirector;
	}
	
	

	public List<String> getClassNames() {
		return classNames;
	}

	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}



	
	
	
	

}
