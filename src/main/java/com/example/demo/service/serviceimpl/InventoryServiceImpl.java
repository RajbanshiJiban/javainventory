package com.example.demo.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Inventory;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService{
	@Autowired
  private InventoryRepository invrepo;

	@Override
	public void addInv(Inventory inv) {
		invrepo.save(inv);
		
	}

	@Override
	public void deleteInv(int invid) {
		
		invrepo.deleteById(invid);
	}

	@Override
	public void updateInv(Inventory inv) {
		invrepo.save(inv);
		
	}

	@Override
	public Inventory getInvById(int invid) {
		
		return invrepo.findById(invid).get();
	}

	@Override
	public List<Inventory> getAllInvs() {
	
		return invrepo.findAll();
	}

}
