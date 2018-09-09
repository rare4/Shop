package com.teamontb.pboard.dto;

import java.util.Date;
import java.util.List;
import com.teamontb.product.dto.ProductVO;

public class ProductBoardVO {
	private int productBoardNo;
	private String productBoardTitle;
	private int currentPrice;
	private int registeredStock;
	private Date registeredDate;
	private List<ProductVO> productList;
	private ProductVO pvo;
	
	
	public ProductBoardVO(int productBoardNo, String productBoardTitle, int currentPrice, int registeredStock,
			Date registeredDate,  ProductVO pvo) {
		super();
		this.productBoardNo = productBoardNo;
		this.productBoardTitle = productBoardTitle;
		this.currentPrice = currentPrice;
		this.registeredStock = registeredStock;
		this.registeredDate = registeredDate;
		
		this.pvo = pvo;
	}


	public ProductVO getPvo() {
		return pvo;
	}


	public void setPvo(ProductVO pvo) {
		this.pvo = pvo;
	}


	public ProductBoardVO() {

	}
	
	
	public ProductBoardVO(int productBoardNo, String productBoardTitle, int currentPrice, int registeredStock,
			Date registeredDate, List<ProductVO> productList) {
		super();
		this.productBoardNo = productBoardNo;
		this.productBoardTitle = productBoardTitle;
		this.currentPrice = currentPrice;
		this.registeredStock = registeredStock;
		this.registeredDate = registeredDate;
		this.productList = productList;
	}


	@Override
	public String toString() {
		return "ProductBoardVO [productBoardNo=" + productBoardNo + ", productBoardTitle=" + productBoardTitle
				+ ", currentPrice=" + currentPrice + ", registeredStock=" + registeredStock + ", registeredDate="
				+ registeredDate + ", productList=" + productList + "]";
	}



	public int getProductBoardNo() {
		return productBoardNo;
	}
	public void setProductBoardNo(int productBoardNo) {
		this.productBoardNo = productBoardNo;
	}
	public String getProductBoardTitle() {
		return productBoardTitle;
	}
	public void setProductBoardTitle(String productBoardTitle) {
		this.productBoardTitle = productBoardTitle;
	}
	public int getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getRegisteredStock() {
		return registeredStock;
	}
	public void setRegisteredStock(int registeredStock) {
		this.registeredStock = registeredStock;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public List<ProductVO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductVO> productList) {
		this.productList = productList;
	}

	

	


}
