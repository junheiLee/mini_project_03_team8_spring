package com.team8.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.service.CartService;
import com.team8.shopping.service.OrderService;
import com.team8.shopping.vo.CartVO;
import com.team8.shopping.vo.MemberVO;
import com.team8.shopping.vo.OrderVO;

@RequestMapping("/order")
@Controller
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;

	@Autowired
	public OrderController(OrderService orderService, CartService cartService) {
		this.orderService = orderService;
		this.cartService = cartService;
	}

	@RequestMapping(value = { "/mypage", "" })
	public String findAllOrderInProgressByUserId(HttpServletRequest request) {

		String userId = getUserIdFromSession(request);

		List<OrderVO> orderList = orderService.findAllOrderInProgressByUserId(userId);

		request.setAttribute("title", "진행 중인 주문 내역");
		request.setAttribute("orderList", orderList);

		return "mypage/orderList";
	}

	@RequestMapping(value = "/now/{oseq}")
	public String findAllOrderNow(@PathVariable Integer oseq, HttpServletRequest request) {

		String userId = getUserIdFromSession(request);
		List<OrderVO> orderList = orderService.findAllDetail(oseq, userId, "1");
		int totalPrice = orderService.calTotalPrice(orderList);

		request.setAttribute("orderList", orderList);
		request.setAttribute("totalPrice", totalPrice);

		return "/mypage/orderList";
	}

	@RequestMapping(value = "/{oseq}")
	public String findAllDetail(@PathVariable Integer oseq, HttpServletRequest request) {

		String userId = getUserIdFromSession(request);
		List<OrderVO> orderList = orderService.findAllDetail(oseq, userId, "%");
		int totalPrice = orderService.calTotalPrice(orderList);

		request.setAttribute("orderDetail", orderList.get(0));
		request.setAttribute("orderList", orderList);
		request.setAttribute("totalPrice", totalPrice);

		return "/mypage/orderDetail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insert(@RequestParam String[] cseq, HttpServletRequest request) {

		String userId = getUserIdFromSession(request);
		int maxOseq = 0;

		if (cseq != null) {
			List<CartVO> cartList = cartService.findAllByUserId(userId);
			List<CartVO> orderin = new ArrayList<CartVO>();

			for (CartVO cart : cartList) {
				for (String seq : cseq) {
					if (cart.getCseq() == Integer.parseInt(seq)) {
						orderin.add(cart);
					}
				}
			}
			maxOseq = orderService.insert(orderin, userId);
		}

		return "redirect:/" + maxOseq;
	}

	@RequestMapping(value = "/now", method = RequestMethod.POST)
	public String insertNow(@ModelAttribute CartVO cartVO, HttpServletRequest request) {
		
		String userId = getUserIdFromSession(request);
		List<CartVO> cartList = new ArrayList<CartVO>();
		cartList.add(cartVO);
		int maxOseq = orderService.insert(cartList, userId);
		
		return "redirect:/";
	}

	private String getUserIdFromSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginMember");

		return loginUser.getId();
	}
}
