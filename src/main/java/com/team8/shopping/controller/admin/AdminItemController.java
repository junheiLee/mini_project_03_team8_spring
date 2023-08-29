package com.team8.shopping.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/item")
public class AdminItemController {

	@RequestMapping(value = "/listProduct")
	public String listProduct(@RequestParam(value = "key", required = false, defaultValue = "") String key, 
											  @RequestParam(value = "tpage", required = false, defaultValue = "1") String tpage, 
											  Model model) {
		model.addAttribute("key", key);
		model.addAttribute("tpage", tpage);
		
		return "admin/product/productList";
	}
}
