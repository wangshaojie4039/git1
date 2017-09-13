package cn.imexue.ec.web.model.mongo.xc;

import java.util.List;

public class XcAttendanceDetail {

	private List<XcClassDetail> ride;
	
	private List<XcClassDetail> noRide;

	public List<XcClassDetail> getRide() {
		return ride;
	}

	public void setRide(List<XcClassDetail> ride) {
		this.ride = ride;
	}

	public List<XcClassDetail> getNoRide() {
		return noRide;
	}

	public void setNoRide(List<XcClassDetail> noRide) {
		this.noRide = noRide;
	}

}
