package com.teamontb.member.dto;

import java.util.Date;

public class PointVO {
	private int pointCategory;
	private Date pointDate;
	private int pointHistory;
	private String pointReason;
	private int point;
	
	public PointVO() {}
	
	public PointVO(int pointCategory, Date pointDate, int pointHistory, String pointReason, int point) {
		super();
		this.pointCategory = pointCategory;
		this.pointDate = pointDate;
		this.pointHistory = pointHistory;
		this.pointReason = pointReason;
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPointCategory() {
		return pointCategory;
	}
	public void setPointCategory(int pointCategory) {
		this.pointCategory = pointCategory;
	}
	public Date getPointDate() {
		return pointDate;
	}
	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}
	public int getPointHistory() {
		return pointHistory;
	}
	public void setPointHistory(int pointHistory) {
		this.pointHistory = pointHistory;
	}
	public String getPointReason() {
		return pointReason;
	}
	public void setPointReason(String pointReason) {
		this.pointReason = pointReason;
	}
	
}
