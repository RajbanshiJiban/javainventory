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

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.util.FileUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ProductService prodser;

	@Autowired
	private CategoryService catser;
	@Autowired
	private FileUtil fileUtil;

	@GetMapping("/Product/add")
	public String getcategorytAdd(Model model,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		model.addAttribute("catlist", catser.getAllCats());

		return "admin/Productadd";

	}

	@GetMapping("/Product/list")
	public String getProdList(Model model,HttpSession session) {
		
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
	    List<Product> productList = prodser.getAllProds(); // Fetch all products
	    model.addAttribute("ProductList", productList); // Add productList to the model
	    return "admin/Productlist"; // Return the product list view
	}

	@PostMapping("/Product/add")
	public String postProduct(@ModelAttribute Product product,
			@RequestParam(value = "status", required = false) String status, @RequestParam("image") MultipartFile image,
			@RequestParam("categoryid") int categoryid, Model model) {
		LocalDateTime currentDateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateAndTime.format(formatter);
		if (!image.isEmpty()) {
			fileUtil.fileUpload(image);
			product.setPhoto(image.getOriginalFilename());
			product.setAddedDate(formattedDateTime);
			product.setStatus(status != null && status.equals("1") ? 1 : 0);
			Category category = catser.getCatById(categoryid);
			product.setCategory(category);
			prodser.addProd(product);
			model.addAttribute("message", "Upload success");
			model.addAttribute("catlist", catser.getAllCats());
		} else {
			model.addAttribute("message", "Upload fail");
		}
		return "admin/productadd";
	}
	
	
	
	
	
	
	@GetMapping("/Product/delete")
	public String delete(@RequestParam int id) {
		prodser.deleteProd(id);
		return "redirect:/admin/Product/list";
	}
	
	
	
	@GetMapping("/Product/edit")
	public String getedit(@RequestParam int id, Model model,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
		
		model.addAttribute("prodObject",prodser.getProdById(id));
		
		return "admin/Productedit";
	}
	
	
	
	
	@PostMapping("/Product/update")
	public String update(@ModelAttribute Product prod, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Product existingProd = prodser.getProdById(prod.getId());
	    if (image.isEmpty()) {
	        prod.setPhoto(existingProd.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        prod.setPhoto(image.getOriginalFilename());
	    }
	    prod.setAddedDate(existingProd.getAddedDate());
	    prod.setStatus(status != null && status.equals("1") ? 1 : 0);
	    prod.setCategory(existingProd.getCategory());

	    prodser.updateProd(prod);
	    return "redirect:/admin/Product/list";
	}




}
