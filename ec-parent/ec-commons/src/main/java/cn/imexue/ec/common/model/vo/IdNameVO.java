package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

@Alias("IdNameVO")
public class IdNameVO {

	private Long	id;
	private String	name;

	public IdNameVO() {

	}

	public IdNameVO(Long id, String name) {

		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public String toString() {

		return "IdNameVO [id=" + id + ", name=" + name + "]";
	}

}
