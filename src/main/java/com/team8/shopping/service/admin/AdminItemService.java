package com.team8.shopping.service.admin;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.beans.PageBean;
import com.team8.shopping.dao.admin.AdminItemDAO;
import com.team8.shopping.vo.ItemVO;

@Service
public class AdminItemService {
	
	private int listCnt = 5;
	private int paginationCnt = 5;

	@Autowired
	private AdminItemDAO adminItemDAO;
	
	@Transactional(readOnly = true)
	public List<ItemVO> listProduct(String keyword, int page) {
		List<ItemVO> itemList = null;
		int start = (page - 1) * listCnt;
		RowBounds rowBounds = new RowBounds(start, listCnt);
		if (keyword != null && !keyword.isEmpty()) {
			itemList = adminItemDAO.selectProductByKeyword(keyword, rowBounds);
		} else {
			itemList = adminItemDAO.selectAllProduct(rowBounds);
		}
		return itemList;
	}
	
	@Transactional(readOnly = true)
	public PageBean getPageBean(int currentPage, String keyword) {
		PageBean pageBean = null;
		int productCnt = -1;
		if (keyword != null && !keyword.isEmpty()) {
			productCnt = adminItemDAO.getProductCntByKeyword(keyword);
		} else {
			productCnt = adminItemDAO.getProductCnt();
		}
		pageBean = new PageBean(productCnt, currentPage, listCnt, paginationCnt);
		return pageBean;
	}
	
	@Transactional(readOnly = true)
	public ItemVO getProduct(String pseq) {
		return adminItemDAO.getProduct(pseq);
	}
	
	@Transactional
	public void insertItem(ItemVO itemVO) {
		adminItemDAO.insertItem(itemVO);
	}
	
	@Transactional
	public void updateProduct(ItemVO itemVO) {
		adminItemDAO.updateProduct(itemVO);
	}
}
