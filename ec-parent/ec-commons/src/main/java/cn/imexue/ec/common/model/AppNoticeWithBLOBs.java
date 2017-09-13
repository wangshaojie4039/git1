package cn.imexue.ec.common.model;

public class AppNoticeWithBLOBs extends AppNotice {
    private String uids;

    private String districtIds;

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
}