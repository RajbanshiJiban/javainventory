package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.example.demo.service.CategoryService;
import com.example.demo.util.FileUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private CategoryService catService;
	@Autowired
	private FileUtil fileUtil;

	@GetMapping("/Category")
	public String getCategory(HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		return "admin/Category";

	}

	@GetMapping("/Category/add")
	public String getCategoryAdd(HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		return "admin/CategoryAdd";

	}

	@GetMapping("/Category/list")
	public String getCategoryList(Model model ,HttpSession session)

	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		model.addAttribute("catList", catService.getAllCats());

		return "admin/CategoryList";

	}

	@PostMapping("/Category/add")
	public String postCat(@ModelAttribute Category cat, @RequestParam("image") MultipartFile image,
			@RequestParam(value = "status", required = false) String status, Model model) {
		LocalDateTime currentDateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateAndTime.format(formatter);
		if (!image.isEmpty()) {
			fileUtil.fileUpload(image);
			cat.setPhoto(image.getOriginalFilename());
			cat.setAddeddate(formattedDateTime);
			cat.setStatus(status != null && status.equals("1") ? 1 : 0);
			catService.addCat(cat);
			model.addAttribute("message", "Upload success");
		} else {
			model.addAttribute("message", "Upload fail");
		}
		return "admin/CategoryAdd";
	}
	
	
	
	
	@GetMapping("/Category/edit")
	public String getedit(@RequestParam int id, Model model,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}

		
		model.addAttribute("catObject",catService.getCatById(id));
		
		return "admin/Categoryedit";
	}

	
	
	
	
	
	
	@PostMapping("/Category/update")
	public String update(@ModelAttribute Category cat, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Category existingCat = catService.getCatById(cat.getCategoryid());
	    if (image.isEmpty()) {
	        cat.setPhoto(existingCat.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        cat.setPhoto(image.getOriginalFilename());
	    }
	    cat.setAddeddate(existingCat.getAddeddate());
	    cat.setStatus(status != null && status.equals("1") ? 1 : 0);
	    catService.updateCat(cat);
	    return "redirect:/admin/Category/list";
	}

	
	
	
	@GetMapping("/Category/delete")
	public String delete(@RequestParam int id) {
		catService.deleteCat(id);
		return "redirect:/admin/Category/list";
	}

	
	
	

}
