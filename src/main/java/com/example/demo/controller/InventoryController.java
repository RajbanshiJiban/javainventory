package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.model.Inventory;
import com.example.demo.model.Product;
import com.example.demo.model.Sell;
import com.example.demo.service.InventoryService;
import com.example.demo.service.ProductService;
import com.example.demo.util.FileUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class InventoryController {
	@Autowired
	private ProductService prodser;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private InventoryService invser;
	
	@GetMapping("/Inventory/add")
	public String getInventoryadd(Model model,HttpSession session)
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		model.addAttribute("prodlist", prodser.getAllProds());
		return "admin/Inventoryadd";
		
	}
	
	
	@GetMapping("/Inventory/list")
	public String getInventorylist(Model model,HttpSession session)
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		List<Inventory>invlist = invser.getAllInvs(); // Fetch all products
		 model.addAttribute("invlist", invlist); // Add productList to the model
		  
		 
		 
		 
			
			
				return "admin/Inventorylist"; // Return the product list view
			
			
			
			
			
		

			
			
		
		 
		 
	
		
	   
	   
		
	}
	
	
	
	
	@PostMapping("/Inventory/add")
	public String postInv(@ModelAttribute Inventory inv,@RequestParam(value = "status", required = false) String status, @RequestParam("image") MultipartFile image,
			@RequestParam("id") int id, Model model) {
		LocalDateTime currentDateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateAndTime.format(formatter);
		if (!image.isEmpty()) {
			fileUtil.fileUpload(image);
			inv.setPhoto(image.getOriginalFilename());
			System.out.println(id);
			inv.setAddedDate(formattedDateTime);
			inv.setStatus(status != null && status.equals("1") ? 1 : 0);
			Product product = prodser.getProdById(id);
			inv.setProduct(product);
			invser.addInv(inv);
			System.out.println(id);

			model.addAttribute("message", "Upload success");
			model.addAttribute("prodlist",prodser .getAllProds());
		} else {
			model.addAttribute("message", "Upload fail");
		}
		return "admin/Inventoryadd";
	}
	
	
	
	
	@GetMapping("/Inventory/edit")
	public String getedit(@RequestParam int id, Model model,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		
		model.addAttribute("invObject",invser.getInvById(id));
		
		return "admin/Inventoryedit";
	}
	
	
	
	

	@PostMapping("/Inventory/update")
	public String update(@ModelAttribute Inventory inv, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Inventory existingInv = invser.getInvById(inv.getInvid());
	    if (image.isEmpty()) {
	        inv.setPhoto(existingInv.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        inv.setPhoto(image.getOriginalFilename());
	    }
	    inv.setAddedDate(existingInv.getAddedDate());
	    inv.setStatus(status != null && status.equals("1") ? 1 : 0);
	    inv.setProduct(existingInv.getProduct());
	    inv.setSellprice(existingInv.getSellprice());
	    inv.setCostprice(existingInv.getCostprice());
	    inv.setQuantity(existingInv.getQuantity());
	    

	   invser.updateInv(inv);
	    return "redirect:/admin/Inventory/list";
	}
	
	

	@GetMapping("/Inventory/delete")
	public String delete(@ModelAttribute Inventory inv, @RequestParam int id,Model model) {
		Inventory invs = invser.getInvById(id);
		int b = invs.getQuantity();
		if(b==0)
		{
			invser.deleteInv(id);
			
		}else
		{
			
			model.addAttribute("msg", "It cannot be delete because it is still remaining");
			return "redirect:/admin/Inventory/list";
		}
		
		return "redirect:/admin/Inventory/list";
	}
	

}
