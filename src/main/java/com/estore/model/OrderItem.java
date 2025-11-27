package com.estore.model;

public class OrderItem {
	private long productId;
	private int quantity;
	private double price;

	private String productName;

	public OrderItem(long productId, int quantity, double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setProductId(long productId) {
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
