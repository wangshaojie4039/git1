package cn.imexue.ec.web.web.controller.school.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class SchoolReq {

	@NotBlank
	@Length(max=50)
	private String address;
	
	@NotBlank
	@Length(max=15)
	private String linkman;
	
	@NotBlank
	@Pattern(regexp="^[1]{1}[2-9]{1}[0-9]{9}$",message="手机号码格式不正确")
	private String tel;

	@Min(value=1)
	private Integer versionNo;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	
}
