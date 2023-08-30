package com.team8.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.dao.OrderDAO;
import com.team8.shopping.vo.CartVO;
import com.team8.shopping.vo.OrderVO;

@Transactional
@Service
public class OrderService {

	private final OrderDAO orderDAO;

	@Autowired
	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Transactional
	public List<OrderVO> findAllOrderInProgressByUserId(String userId) {
		
		List<Integer> oseqList = orderDAO.findOseqInProgressByUserId(userId);
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		
		for (int oseq : oseqList) {
			List<OrderVO> orderListIng = findAllDetail(oseq, userId, "1");
			OrderVO orderVO = orderListIng.get(0);
			orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");

			int totalPrice = 0;
			for (OrderVO ovo : orderListIng) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);
		}
		
		return orderList;
	}

	@Transactional(readOnly = true)
	public List<OrderVO> findAllDetail(int oseq, String userId, String result) {
		OrderVO orderVO = new OrderVO(oseq, userId, result);
		return orderDAO.findAllDetail(orderVO);
	}

	// insertOrder
	@Transactional
	public int insert(List<CartVO> cartList, String userId) {
		int maxOseq = 0;
		maxOseq = orderDAO.findMaxOseq();
		orderDAO.insert(userId);

		for (CartVO cart : cartList) {
			orderDAO.insertDetail(cart, maxOseq);
			orderDAO.updateCart(cart);
		}

		return maxOseq;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int calTotalPrice(List<OrderVO> orderList) {

		int totalPrice = 0;

		for (OrderVO order : orderList) {
			totalPrice += order.getPrice2() * order.getQuantity();
		}
		return totalPrice;
	}

}
