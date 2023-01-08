package com.example.product.utils;

import java.util.List;

public interface DBUtilities {
	
	public void addToCard(int pid);
	public Integer getCartItems(int idx);
	public void deleteCartItems();
	public int getTotalRows();

}
