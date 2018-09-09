package com.teamontb.board.dto;

import java.sql.Timestamp;

public class BoardVO {
	private int customer_board_no;
	private String member_email;
	private String board_title;
	private String board_content;
	private String board_images;
	private String board_category;
	private Timestamp written_date;
	private Timestamp modified_date;
	
	
	


	public BoardVO(int customer_board_no, String board_title, String board_content) {
	
		this.customer_board_no = customer_board_no;
		this.board_title = board_title;
		this.board_content = board_content;
	}


	public BoardVO(String member_email, String board_title, String board_content) {
		
		this.member_email = member_email;
		this.board_title = board_title;
		this.board_content = board_content;
	}


	public BoardVO(int customer_board_no, String member_email, String board_title, String board_content,
			String board_images, String board_category, Timestamp written_date, Timestamp modified_date) {
		
		this.customer_board_no = customer_board_no;
		this.member_email = member_email;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_images = board_images;
		this.board_category = board_category;
		this.written_date = written_date;
		this.modified_date = modified_date;
	}



	@Override
	public String toString() {
		return "BoardVO [customer_board_no=" + customer_board_no + ", member_email=" + member_email + ", board_title="
				+ board_title + ", board_content=" + board_content + ", board_images=" + board_images
				+ ", board_category=" + board_category + ", written_date=" + written_date + ", modified_date="
				+ modified_date + "]";
	}


	public int getCustomer_board_no() {
		return customer_board_no;
	}


	public void setCustomer_board_no(int customer_board_no) {
		this.customer_board_no = customer_board_no;
	}


	public String getMember_email() {
		return member_email;
	}


	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	public String getBoard_title() {
		return board_title;
	}


	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}


	public String getBoard_content() {
		return board_content;
	}


	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}


	public String getBoard_images() {
		return board_images;
	}


	public void setBoard_images(String board_images) {
		this.board_images = board_images;
	}


	public String getBoard_category() {
		return board_category;
	}


	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}


	public Timestamp getWritten_date() {
		return written_date;
	}


	public void setWritten_date(Timestamp written_date) {
		this.written_date = written_date;
	}


	public Timestamp getModified_date() {
		return modified_date;
	}


	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}


	public BoardVO() {};
	
	
}
