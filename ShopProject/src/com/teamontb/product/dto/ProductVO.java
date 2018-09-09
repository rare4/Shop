package com.teamontb.product.dto;

public class ProductVO {
	private int productNo;
	private int productCategory;
	private String productName;
	private int productCost;
	private int productPrice;
	private String productInformation;
	private String productMainImages;
	private String productSubImage;
	private String productColor;
	private int productWeight;
	private String productSize;
	private String productManufacturer;
	private int productPowerConsumption;
	private int productRatedVoltage;

	public ProductVO() {

	}
	
	

	public ProductVO(int productNo, int productCategory, String productName, int productCost, int productPrice,
			String productInformation, String productMainImages, String productSubImage, String productColor,
			int productWeight, String productSize, String productManufacturer, int productPowerConsumption,
			int productRatedVoltage) {
		super();
		this.productNo = productNo;
		this.productCategory = productCategory;
		this.productName = productName;
		this.productCost = productCost;
		this.productPrice = productPrice;
		this.productInformation = productInformation;
		this.productMainImages = productMainImages;
		this.productSubImage = productSubImage;
		this.productColor = productColor;
		this.productWeight = productWeight;
		this.productSize = productSize;
		this.productManufacturer = productManufacturer;
		this.productPowerConsumption = productPowerConsumption;
		this.productRatedVoltage = productRatedVoltage;
	}


	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productCost=" + productCost + ", productPrice=" + productPrice
				+ ", productInformation=" + productInformation + ", productMainImages=" + productMainImages
				+ ", productSubImage=" + productSubImage + ", productColor=" + productColor + ", productWeight="
				+ productWeight + ", productSize=" + productSize + ", productManufacturer=" + productManufacturer
				+ ", productPowerConsumption=" + productPowerConsumption + ", productRatedVoltage="
				+ productRatedVoltage + "]";
	}



	public String getProductMainImages() {
		return productMainImages;
	}



	public void setProductMainImages(String productMainImages) {
		this.productMainImages = productMainImages;
	}



	public String getProductSubImage() {
		return productSubImage;
	}



	public void setProductSubImage(String productSubImages) {
		this.productSubImage = productSubImages;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductInformation() {
		return productInformation;
	}

	public void setProductInformation(String productInformation) {
		this.productInformation = productInformation;
	}



	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public int getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(int productWeight) {
		this.productWeight = productWeight;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getProductManufacturer() {
		return productManufacturer;
	}

	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public int getProductPowerConsumption() {
		return productPowerConsumption;
	}

	public void setProductPowerConsumption(int productPowerConsumption) {
		this.productPowerConsumption = productPowerConsumption;
	}

	public int getProductRatedVoltage() {
		return productRatedVoltage;
	}

	public void setProductRatedVoltage(int productRatedVoltage) {
		this.productRatedVoltage = productRatedVoltage;
	}



}
