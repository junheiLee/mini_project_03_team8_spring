package com.team8.shopping.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.team8.shopping.beans.PageBean;
import com.team8.shopping.service.admin.AdminItemService;
import com.team8.shopping.vo.ItemVO;

@Controller
@RequestMapping("/admin/item")
public class AdminItemController {

	@Autowired
	private AdminItemService adminItemService;
	
	@Autowired
    private ServletContext servletContext;

	@RequestMapping(value = "/listProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public String listProduct(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		List<ItemVO> itemList = adminItemService.listProduct(keyword, page);
		PageBean pageBean = adminItemService.getPageBean(page, keyword);
		model.addAttribute("productList", itemList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("keyword", keyword);
		return "admin/product/productList";
	}

	@RequestMapping(value = "/detailProduct")
	public String itemDetail(@RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam("pseq") String pseq, Model model) {
		ItemVO itemVO = adminItemService.getProduct(pseq);
		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		int index = Integer.parseInt(itemVO.getKind().trim());
		model.addAttribute("productVO", itemVO);
		model.addAttribute("tpage", page);
		model.addAttribute("kind", kindList[index]);
		return "admin/product/productDetail";
	}

	@RequestMapping(value = "/writeForm")
	public String writeForm(Model model) {
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		model.addAttribute("kindList", kindList);
		return "admin/product/productWrite";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(ItemVO itemVO, @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException {
        String savePath = servletContext.getRealPath("/resources/static/images/product_images/");
        if (!imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            String newFilename = UUID.randomUUID().toString()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            File file = new File(savePath, newFilename);
            imageFile.transferTo(file);
            itemVO.setImage(newFilename);
        }
        adminItemService.insertItem(itemVO);
        return "redirect:/admin/item/listProduct";
    }
	
	@RequestMapping(value = "/updateForm")
	public String updateForm(@RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam("pseq") String pseq, Model model) {
		ItemVO itemVO = adminItemService.getProduct(pseq);
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers"};
		model.addAttribute("tpage", page);
		model.addAttribute("productVO", itemVO);
		model.addAttribute("kindList", kindList);
		return "admin/product/productUpdate";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ItemVO itemVO, @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException {
		System.out.println(itemVO.getImage() + "========" + itemVO.getUseyn() + "======" + itemVO.getBestyn());
        String savePath = servletContext.getRealPath("/resources/static/images/product_images/");
        if (!imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            String newFilename = UUID.randomUUID().toString()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            File file = new File(savePath, newFilename);
            imageFile.transferTo(file);
            itemVO.setImage(newFilename);
        }
        adminItemService.updateProduct(itemVO);
        return "redirect:/admin/item/listProduct";
    }
}
