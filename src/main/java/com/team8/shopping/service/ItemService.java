package com.team8.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.dao.ItemDAO;
import com.team8.shopping.vo.ItemVO;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	@Transactional(readOnly = true)
	public List<ItemVO> listNewProduct() {
		return itemDAO.listNewProduct();
	}
	
	@Transactional(readOnly = true)
	public List<ItemVO> listBestProduct() {
		return itemDAO.listBestProduct();
	}
	
	@Transactional(readOnly = true)
	public ItemVO getProduct(String pseq) {
		return itemDAO.getProduct(pseq);
	}
	
	@Transactional(readOnly = true)
	public List<ItemVO> listKindProduct(String kind){
		return itemDAO.listKindProduct(kind);
	}
	
}