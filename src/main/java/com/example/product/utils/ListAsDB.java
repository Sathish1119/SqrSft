package com.example.product.utils;

import java.util.ArrayList;
import java.util.List;

public class ListAsDB implements DBUtilities{
	
	List<Integer> lst = new ArrayList<>();

	public ListAsDB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addToCard(int pid) {
		lst.add(pid);
		
	}

	@Override
	public Integer getCartItems(int idx) {
		return lst.get(idx);
	}

	@Override
	public void deleteCartItems() {
		lst = new ArrayList<>();
		
	}

	@Override
	public int getTotalRows() {
		return lst.size();
	}

}
