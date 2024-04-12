package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Inventory;


public interface InventoryService {
	

	void addInv(Inventory inv);
	void deleteInv(int invid);
	void updateInv(Inventory inv);
	Inventory getInvById(int invid);
	List<Inventory>getAllInvs();
	
	
	
}
