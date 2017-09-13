package cn.imexue.ec.web.model.mongo.xc;

public class XcLineStatistics {

	private String lineName;

	private Long lineId;
	
	private Integer upTotal;//上学应乘

	private Integer up;//上学实乘

	private Integer downTotal;//放学应乘

	private Integer down;

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Integer getUpTotal() {
		return upTotal;
	}

	public void setUpTotal(Integer upTotal) {
		this.upTotal = upTotal;
	}

	public Integer getUp() {
		return up;
	}

	public void setUp(Integer up) {
		this.up = up;
	}

	public Integer getDownTotal() {
		return downTotal;
	}

	public void setDownTotal(Integer downTotal) {
		this.downTotal = downTotal;
	}

	public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	
}
