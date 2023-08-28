package com.team8.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team8.shopping.dao.ItemDAO;
import com.team8.shopping.vo.ItemVO;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	public List<ItemVO> listNewProduct() {
		return itemDAO.listNewProduct();
	}
	
	public List<ItemVO> listBestProduct() {
		return itemDAO.listBestProduct();
	}
}
