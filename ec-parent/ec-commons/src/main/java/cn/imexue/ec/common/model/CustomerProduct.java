package cn.imexue.ec.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerProduct {

	private Long		id;

	private String		productType;

	private String		name;

	private String		model;

	private String		imageUrl;

	private String		unit;

	private String		scale;

	private BigDecimal	provinceOriginalPrice;

	private BigDecimal	provincePrice;

	private BigDecimal	cityOriginalPrice;

	private BigDecimal	cityPrice;

	private BigDecimal	districtOriginalPrice;

	private BigDecimal	districtId;

	private Byte		isOnSale;

	private Date		createTime;

	private String		creatorRole;

	private Long		creatorId;

	private Date		updateTime;

	private String		updaterRole;

	private Long		updaterId;

	private String		details;

	// ////////////////以下为自定义字段////////////////////
	/* 产品价格 */
	private BigDecimal	productPrice;
	/* 产品价格 */
	private BigDecimal	productOriginalPrice;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getProductType() {

		return productType;
	}

	public void setProductType(String productType) {

		this.productType = productType;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getModel() {

		return model;
	}

	public void setModel(String model) {

		this.model = model;
	}

	public String getImageUrl() {

		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}

	public String getUnit() {

		return unit;
	}

	public void setUnit(String unit) {

		this.unit = unit;
	}

	public String getScale() {

		return scale;
	}

	public void setScale(String scale) {

		this.scale = scale;
	}

	public BigDecimal getProvinceOriginalPrice() {

		return provinceOriginalPrice;
	}

	public void setProvinceOriginalPrice(BigDecimal provinceOriginalPrice) {

		this.provinceOriginalPrice = provinceOriginalPrice;
	}

	public BigDecimal getProvincePrice() {

		return provincePrice;
	}

	public void setProvincePrice(BigDecimal provincePrice) {

		this.provincePrice = provincePrice;
	}

	public BigDecimal getCityOriginalPrice() {

		return cityOriginalPrice;
	}

	public void setCityOriginalPrice(BigDecimal cityOriginalPrice) {

		this.cityOriginalPrice = cityOriginalPrice;
	}

	public BigDecimal getCityPrice() {

		return cityPrice;
	}

	public void setCityPrice(BigDecimal cityPrice) {

		this.cityPrice = cityPrice;
	}

	public BigDecimal getDistrictOriginalPrice() {

		return districtOriginalPrice;
	}

	public void setDistrictOriginalPrice(BigDecimal districtOriginalPrice) {

		this.districtOriginalPrice = districtOriginalPrice;
	}

	public BigDecimal getDistrictId() {

		return districtId;
	}

	public void setDistrictId(BigDecimal districtId) {

		this.districtId = districtId;
	}

	public Byte getIsOnSale() {

		return isOnSale;
	}

	public void setIsOnSale(Byte isOnSale) {

		this.isOnSale = isOnSale;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	public String getCreatorRole() {

		return creatorRole;
	}

	public void setCreatorRole(String creatorRole) {

		this.creatorRole = creatorRole;
	}

	public Long getCreatorId() {

		return creatorId;
	}

	public void setCreatorId(Long creatorId) {

		this.creatorId = creatorId;
	}

	public Date getUpdateTime() {

		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	public String getUpdaterRole() {

		return updaterRole;
	}

	public void setUpdaterRole(String updaterRole) {

		this.updaterRole = updaterRole;
	}

	public Long getUpdaterId() {

		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {

		this.updaterId = updaterId;
	}

	public String getDetails() {

		return details;
	}

	public void setDetails(String details) {

		this.details = details;
	}

	public BigDecimal getProductPrice() {

		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {

		this.productPrice = productPrice;
	}

	public BigDecimal getProductOriginalPrice() {

		return productOriginalPrice;
	}

	public void setProductOriginalPrice(BigDecimal productOriginalPrice) {

		this.productOriginalPrice = productOriginalPrice;
	}

	@Override
	public String toString() {

		return "CustomerProduct [id=" + id + ", productType=" + productType + ", name=" + name + ", model=" + model + ", imageUrl=" + imageUrl + ", unit=" + unit + ", scale="
				+ scale + ", provinceOriginalPrice=" + provinceOriginalPrice + ", provincePrice=" + provincePrice + ", cityOriginalPrice=" + cityOriginalPrice + ", cityPrice="
						+ cityPrice + ", districtOriginalPrice=" + districtOriginalPrice + ", districtId=" + districtId + ", isOnSale=" + isOnSale + ", createTime=" + createTime
						+ ", creatorRole=" + creatorRole + ", creatorId=" + creatorId + ", updateTime=" + updateTime + ", updaterRole=" + updaterRole + ", updaterId=" + updaterId
						+ ", details=" + details + ", productPrice=" + productPrice + ", productOriginalPrice=" + productOriginalPrice + "]";
	}

}
