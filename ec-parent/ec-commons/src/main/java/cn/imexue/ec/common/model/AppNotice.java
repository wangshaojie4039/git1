package cn.imexue.ec.common.model;

import java.util.Date;

public class AppNotice {
    private Long id;

    private String title;

    private String content;

    private String fromUserId;

    private Byte isToAll;

    private String uids;

    private String districtIds;

    private Byte platform;

    private Byte roleType;

    private Date createTime;

    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Byte getIsToAll() {
        return isToAll;
    }

    public void setIsToAll(Byte isToAll) {
        this.isToAll = isToAll;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public String getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(String districtIds) {
        this.districtIds = districtIds;
    }

    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}