package com.team8.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.team8.shopping.dao.CartDAO;
import com.team8.shopping.vo.CartVO;

@Service
public class CartService {

	private final CartDAO cartDAO;

	@Autowired
	public CartService(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	// selectCarts
	public List<CartVO> findAllByUserId(String userId) {
		return cartDAO.findAllByUserId(userId);
	}

	// insertCart
	public void insert(CartVO cartVO, String userId) {
		cartVO.setId(userId);
		cartDAO.insert(cartVO);
	}

	// deleteCart
	public void delete(String[] cseqs) {
		for (String cseq : cseqs) {
			cartDAO.delete(Integer.parseInt(cseq.trim()));
		}
	}
	
	public int calTotalPrice(List<CartVO> cartList) {
		
		int totalPrice = 0;
		
		for(CartVO cart : cartList) {
			totalPrice += cart.getPrice2() * cart.getQuantity();
		}
		return totalPrice;
	}
}
