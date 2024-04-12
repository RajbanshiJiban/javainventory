package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Sell;



public interface SellService {
	
	void addSell(Sell sell);
	Sell getSellById(int sellid);
	List<Sell>getAllSells();

}
