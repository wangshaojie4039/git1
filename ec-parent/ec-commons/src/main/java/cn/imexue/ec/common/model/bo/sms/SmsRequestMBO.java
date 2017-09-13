package cn.imexue.ec.common.model.bo.sms;

import java.util.List;

public class SmsRequestMBO {
	private List<SmsTelBO> tel;
	private String type;
	private String msg;
	private String sig;
	private String extend;
	private String ext;
	
	
	
	public List<SmsTelBO> getTel() {
		return tel;
	}
	public void setTel(List<SmsTelBO> tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
