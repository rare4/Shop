package com.teamontb.product.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ReviewVO {
	private int productBoardNo;
	private String memberEmail;
	private String review_content;
	private Timestamp review_date;
	private String review_images;
	private int review_star;
	
	public ReviewVO(int productBoardNo, String memberEmail, String review_content, Timestamp review_date,
			int review_star) {
		
		this.productBoardNo = productBoardNo;
		this.memberEmail = memberEmail;
		this.review_content = review_content;
		this.review_date = review_date;
		this.review_star = review_star;
	}
	public ReviewVO(int productBoardNo, String memberEmail, String review_content, String review_images,
			int review_star) {
		super();
		this.productBoardNo = productBoardNo;
		this.memberEmail = memberEmail;
		this.review_content = review_content;
		this.review_images = review_images;
		this.review_star = review_star;
	}
	public ReviewVO(int productBoardNo, String memberEmail, String review_content, Timestamp review_date,
			String review_images, int review_star) {
	
		this.productBoardNo = productBoardNo;
		this.memberEmail = memberEmail;
		this.review_content = review_content;
		this.review_date = review_date;
		this.review_images = review_images;
		this.review_star = review_star;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public int getProductBoardNo() {
		return productBoardNo;
	}
	public void setProductBoardNo(int productBoardNo) {
		this.productBoardNo = productBoardNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	public String getReview_images() {
		return review_images;
	}
	public void setReview_images(String review_images) {
		this.review_images = review_images;
	}
	public ReviewVO(int productBoardNo, String memberEmail, String review_content, Timestamp review_date,
			String review_images) {
		super();
		this.productBoardNo = productBoardNo;
		this.memberEmail = memberEmail;
		this.review_content = review_content;
		this.review_date = review_date;
		this.review_images = review_images;
	}
	public ReviewVO(){
		
	}
	
}
