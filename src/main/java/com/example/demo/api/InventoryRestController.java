package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;



@RestController
public class InventoryRestController {
	@Autowired
	private InventoryService invser;
	@GetMapping("/api/inv/list")
	public List<Inventory> getAll()
	{
		return invser.getAllInvs();
	}
	
	@GetMapping("/api/inv/{id}")
	public Inventory getId(@PathVariable int id)
	{
		return invser.getInvById(id);
	}
	@PostMapping("/api/inv/add")
	public String add(@RequestBody Inventory inv)
	{
		invser.addInv(inv);
		
		return "sucess";
	}
	@PutMapping("/api/inv/update")
	public String update(@RequestBody Inventory inv)
	{
		invser.addInv(inv);
		
		return "Success";
	}
	
	@DeleteMapping("/api/inv/delete/{id}")
	public String delete(@PathVariable int id)
	{
		invser.deleteInv(id);
		
		return "Success";
	}

}
