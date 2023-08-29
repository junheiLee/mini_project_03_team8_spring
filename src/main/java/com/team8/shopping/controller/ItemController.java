package com.team8.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.service.ItemService;
import com.team8.shopping.vo.ItemVO;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/detailProduct")
	public String itemDetail(Model model, @RequestParam String pseq){
		List<ItemVO> getProduct = itemService.getProduct(pseq);
	    if (!getProduct.isEmpty()) {
	        model.addAttribute("productVO", getProduct.get(0));
	    }
	    return "product/productDetail";
	}
	
	@RequestMapping(value="/kindProduct")
	public String itemKind(Model model, String kind){
		List<ItemVO> listKindProduct = itemService.listKindProduct(kind);
		model.addAttribute("listKindProduct",listKindProduct);
		if(kind != null) {
			model.addAttribute("listKindProduct",listKindProduct);
		}
		return "product/productKind";
	}
}
