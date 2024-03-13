package com.appsdeveloperblog.ws.productsMicroservice.model;

import java.math.BigDecimal;

public class CreateProductRestModel {
	private String title;
	private BigDecimal price;
	private Integer quantity;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String tittle) {
		this.title = tittle;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
