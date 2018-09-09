package com.teamontb.product.dto;

import java.util.Date;

public class SalesStatusVO {
	private int price;
	private int discount;
	private String discountReason;
	private int quantity;
	private Date sellDate;
	private String status;
	private String statusReason;
	private int productNo;
	private String memberEmail;
	private String productName;
	private String deliveryAddress;
	private String deliveryPostNo;
	private String deliveryPhoneNo;
	private String deliveryName;
	private	int deliveryPrice ;
	private String orderno;
	
	
	



	public SalesStatusVO(int price, int discount, String discountReason, int quantity, Date sellDate, String status,
			String statusReason, int productNo, String memberEmail, String productName, String deliveryAddress,
			String deliveryPostNo, String deliveryPhoneNo, String deliveryName, int deliveryPrice, String orderno) {
		super();
		this.price = price;
		this.discount = discount;
		this.discountReason = discountReason;
		this.quantity = quantity;
		this.sellDate = sellDate;
		this.status = status;
		this.statusReason = statusReason;
		this.productNo = productNo;
		this.memberEmail = memberEmail;
		this.productName = productName;
		this.deliveryAddress = deliveryAddress;
		this.deliveryPostNo = deliveryPostNo;
		this.deliveryPhoneNo = deliveryPhoneNo;
		this.deliveryName = deliveryName;
		this.deliveryPrice = deliveryPrice;
		this.orderno = orderno;
	}



	public String getOrderno() {
		return orderno;
	}



	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}



	public String getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	public String getDeliveryPostNo() {
		return deliveryPostNo;
	}



	public void setDeliveryPostNo(String deliveryPostNo) {
		this.deliveryPostNo = deliveryPostNo;
	}



	public String getDeliveryPhoneNo() {
		return deliveryPhoneNo;
	}



	public void setDeliveryPhoneNo(String deliveryPhoneNo) {
		this.deliveryPhoneNo = deliveryPhoneNo;
	}



	public String getDeliveryName() {
		return deliveryName;
	}



	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}



	public int getDeliveryPrice() {
		return deliveryPrice;
	}



	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}



	public SalesStatusVO() {}
	
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
}
