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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Inventory;

import com.example.demo.model.Sell;
import com.example.demo.service.InventoryService;
import com.example.demo.service.SellService;
import com.example.demo.util.SalePdf;
import com.example.demo.util.SellExcel;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/admin")
public class SellController {
	
	@Autowired
	private InventoryService invser;
	
	@Autowired
	private SellService sellser;
	@GetMapping("/Sell/add")
	public String getSelladd(Model model,HttpSession session)
	
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
		model.addAttribute("invlist",invser.getAllInvs());
		
		return "admin/Selladd";
		
	}
	
	@GetMapping("/Sell/list")
	public String getSelllist(Model model,HttpSession session)
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
		List<Sell>selllist = sellser.getAllSells(); // Fetch all products
	    model.addAttribute("selllist", selllist); // Add productList to the model
	    return "admin/Selllist"; // Return the product list view
		
		
	}
	
	
	
	@PostMapping("/Sell/add")
	public String postSell(@ModelAttribute Sell sell,@RequestParam("invid") int invid, Model model) {
		LocalDateTime currentDateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateAndTime.format(formatter);
		
		
		Inventory inv = invser.getInvById(invid);
		sell.setInventory(inv);
		sell.setAddedDate(formattedDateTime);
		int b = inv.getQuantity();
		int a = sell.getSelquantity();
		

		
		if(b>=a)
		{
		
			int c = b-a;
			inv.setQuantity(c);
			invser.updateInv(inv);	
	      
		
		sellser.addSell(sell);
		model.addAttribute("invlist",invser.getAllInvs());
		model.addAttribute("message","Sucessfull Added:");
		return "admin/Selladd";
		
		} else {
			
			model.addAttribute("message","Not that much quantity in my inventory:");
			model.addAttribute("invlist",invser.getAllInvs());
			return "admin/Selladd";
		}
	}
	
	@GetMapping("admin/ellexcel")
	 public ModelAndView excleView() {
	  ModelAndView mv = new ModelAndView();
	  mv.setView(new SellExcel());
	  mv.addObject("list", sellser.getAllSells());
	  return mv;
	 }
	 
	 
	 @GetMapping("admin/sellpdf")
	 public ModelAndView pdfView() {
	  ModelAndView mv = new ModelAndView();
	  mv.setView(new SalePdf());
	  mv.addObject("list", sellser.getAllSells());
	  return mv;
	 }
	
	
	}
		
		
	
	


