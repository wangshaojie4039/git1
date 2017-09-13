package cn.imexue.ec.common.model;

public class DynamicWithBLOBs extends Dynamic {
    /**
	 * 
	 */
	private static final long serialVersionUID = 98084233473302384L;

	private String blackList;

    private String whiteList;

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    public String getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }
}