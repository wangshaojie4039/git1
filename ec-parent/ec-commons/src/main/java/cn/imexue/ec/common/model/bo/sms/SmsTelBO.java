package cn.imexue.ec.common.model.bo.sms;

public class SmsTelBO {
	private String nationcode = "86";
	private String phone;
	
	
	public SmsTelBO(String nationcode, String phone) {
		super();
		this.nationcode = nationcode;
		this.phone = phone;
	}
	public String getNationcode() {
		return nationcode;
	}
	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
