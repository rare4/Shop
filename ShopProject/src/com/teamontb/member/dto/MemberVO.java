package com.teamontb.member.dto;

import java.util.Date;

public class MemberVO {
	private String memberEmail;
	private String memberPassword;
	private String memberAddress;
	private String memberPostNo;
	private String memberPhoneNo;
	private String memberGender;
	private Date memberBirthday;
	private Date joinDate;
	private int admin;
	private int loginStatus;
	private int withdrawal;
	
	public MemberVO(String memberEmail, String memberPassword, String memberAddress, String memberPostNo,
			String memberPhoneNo, String memberGender, Date memberBirthday, Date joinDate, int admin, int loginStatus, int withdrawal) {
		super();
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberAddress = memberAddress;
		this.memberPostNo = memberPostNo;
		this.memberPhoneNo = memberPhoneNo;
		this.memberGender = memberGender;
		this.memberBirthday = memberBirthday;
		this.joinDate = joinDate;
		this.admin = admin;
		this.loginStatus = loginStatus;
		this.withdrawal = withdrawal;
	}
	public Date getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPostNo() {
		return memberPostNo;
	}
	public void setMemberPostNo(String memberPostNo) {
		this.memberPostNo = memberPostNo;
	}
	public String getMemberPhoneNo() {
		return memberPhoneNo;
	}
	public void setMemberPhoneNo(String memberPhoneNo) {
		this.memberPhoneNo = memberPhoneNo;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}
}
