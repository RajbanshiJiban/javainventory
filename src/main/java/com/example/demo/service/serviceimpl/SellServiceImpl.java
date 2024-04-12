package com.example.demo.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sell;
import com.example.demo.repository.SellRepository;
import com.example.demo.service.SellService;
@Service
public class SellServiceImpl implements SellService {
	@Autowired
   private SellRepository sellrepo;
	@Override
	public void addSell(Sell sell) {
	
		sellrepo.save(sell);
	}

	@Override
	public Sell getSellById(int sellid) {

		return sellrepo.findById(sellid).get();
	}

	@Override
	public List<Sell> getAllSells() {
		return sellrepo.findAll();
	}

}
