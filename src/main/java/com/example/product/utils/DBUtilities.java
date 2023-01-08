package com.example.product.utils;

public interface DBUtilities {
	
	public void addToCard(int pid);
	public Integer getCartItems(int idx);
	public void deleteCartItems();
	public int getTotalRows();

}
