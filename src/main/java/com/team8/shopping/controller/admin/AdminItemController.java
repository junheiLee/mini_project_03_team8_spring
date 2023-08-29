package com.team8.shopping.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.beans.PageBean;
import com.team8.shopping.service.admin.AdminItemService;
import com.team8.shopping.vo.ItemVO;

@Controller
@RequestMapping("/admin/item")
public class AdminItemController {
	
	@Autowired
	private AdminItemService adminItemService;

	@RequestMapping(value = "/listProduct", method = {RequestMethod.GET, RequestMethod.POST})
	public String listProduct(@RequestParam(value = "keyword", required = false) String keyword, 
											  @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
											  Model model) {
		List<ItemVO> itemList = adminItemService.listProduct(keyword, page);
		PageBean pageBean = adminItemService.getPageBean(page, keyword);
		model.addAttribute("productList", itemList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("keyword", keyword);
		return "admin/product/productList";
	}
	
	@RequestMapping(value = "/detailProduct")
	public String itemDetail(@RequestParam("page") String page, 
											@RequestParam("pseq") String pseq,
											Model model) {
		List<ItemVO> getProduct = adminItemService.getProduct(pseq);
	    if (!getProduct.isEmpty()) {
	        model.addAttribute("productVO", getProduct.get(0));
	    }
	    return "admin/product/productDetail";
	}
}
