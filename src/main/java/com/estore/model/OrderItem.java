package com.estore.model;

public class OrderItem {
	private int productId;
	private int quantity;
	private double price;

	private String productName;

	public OrderItem(int productId, int quantity, double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
