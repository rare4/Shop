package com.teamontb.cart.dto;

public class CartVO {
	private String productboardtitle;
	private int quantity;
	private int currentprice;
	private String productsize;
	private double productweight;
	private String productcolor;
	private int productno;
	private String productsubimage;
	private int productBoardNo;
	private int registeredStock;
	
	
	
	
	public int getProductBoardNo() {
		return productBoardNo;
	}
	public void setProductBoardNo(int productBoardNo) {
		this.productBoardNo = productBoardNo;
	}

	
	public int getRegisteredStock() {
		return registeredStock;
	}
	public void setRegisteredStock(int registeredStock) {
		this.registeredStock = registeredStock;
	}
	@Override
	public String toString() {
		return "CartVO [productboardtitle=" + productboardtitle + ", quantity=" + quantity + ", currentprice="
				+ currentprice + ", productsize=" + productsize + ", productweight=" + productweight + ", productcolor="
				+ productcolor + ", productno=" + productno + ", productsubimage=" + productsubimage
				+ ", productBoardNo=" + productBoardNo + ", registeredStock=" + registeredStock + "]";
	}
	public CartVO(String productboardtitle, int quantity, int currentprice, String productsize, double productweight,
			String productcolor, int productno, String productsubimage, int productBoardNo, int registeredStock) {
		super();
		this.productboardtitle = productboardtitle;
		this.quantity = quantity;
		this.currentprice = currentprice;
		this.productsize = productsize;
		this.productweight = productweight;
		this.productcolor = productcolor;
		this.productno = productno;
		this.productsubimage = productsubimage;
		this.productBoardNo = productBoardNo;
		this.registeredStock = registeredStock;
	}
	public String getProductsubimage() {
		return productsubimage;
	}
	public void setProductsubimage(String productsubimage) {
		this.productsubimage = productsubimage;
	}
	public int getProductno() {
		return productno;
	}
	public void setProductno(int productno) {
		this.productno = productno;
	}
	
	public String getProductboardtitle() {
		return productboardtitle;
	}
	public void setProductboardtitle(String productboardtitle) {
		this.productboardtitle = productboardtitle;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCurrentprice() {
		return currentprice;
	}
	public void setCurrentprice(int current_price) {
		this.currentprice = current_price;
	}
	public String getProductsize() {
		return productsize;
	}
	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}
	public double getProductweight() {
		return productweight;
	}
	public void setProductweight(double productweight) {
		this.productweight = productweight;
	}
	public String getProductcolor() {
		return productcolor;
	}
	public void setProductcolor(String productcolor) {
		this.productcolor = productcolor;
	}
	
	
	
	
	
}
