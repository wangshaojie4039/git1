package cn.imexue.ec.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class AppProduct {
    private Long id;

    private String productType;

    private String name;

    private String model;

    private String imageUrl;

    private String unit;

    private String scale;

    private Integer num;

    private BigDecimal originalPrice;

    private BigDecimal price;

    private BigDecimal provinceRate;

    private BigDecimal cityRate;

    private BigDecimal districtRate;

    private Byte isOnSale;

    private Date createTime;

    private String creatorRole;

    private Long creatorId;

    private Date updateTime;

    private String updaterRole;

    private Long updaterId;

    private String details;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(BigDecimal provinceRate) {
        this.provinceRate = provinceRate;
    }

    public BigDecimal getCityRate() {
        return cityRate;
    }

    public void setCityRate(BigDecimal cityRate) {
        this.cityRate = cityRate;
    }

    public BigDecimal getDistrictRate() {
        return districtRate;
    }

    public void setDistrictRate(BigDecimal districtRate) {
        this.districtRate = districtRate;
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
}