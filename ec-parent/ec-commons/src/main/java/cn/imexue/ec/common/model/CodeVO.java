package cn.imexue.ec.common.model;

/**
 * 系统代码键值对VO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年3月7日
 * @author wangshaojie
 * @version 1.0
 */
public class CodeVO {

	private String	key;

	private String	value;

	public CodeVO() {

	}

	public CodeVO(String key, String value) {

		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {

		return key;
	}

	public void setKey(String key) {

		this.key = key;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	@Override
	public String toString() {

		return "CodeVO [key=" + key + ", value=" + value + "]";
	}

}
