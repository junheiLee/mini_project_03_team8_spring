package com.team8.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.service.CartService;
import com.team8.shopping.vo.CartVO;
import com.team8.shopping.vo.MemberVO;

@RequestMapping("/cart")
@Controller
public class CartController {

	private final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping
	public String findAllByUserId(HttpServletRequest request) {

		String userId = getUserIdFromSession(request);
		List<CartVO> cartList = cartService.findAllByUserId(userId);
		int totalPrice = cartService.calTotalPrice(cartList);

		request.setAttribute("cartList", cartList);
		request.setAttribute("totalPrice", totalPrice);

		return "/mypage/cartList";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insert(@ModelAttribute CartVO cartVO, HttpServletRequest request) {

		String userId = getUserIdFromSession(request);

		cartService.insert(cartVO, userId);
		return "r:/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String[] cseq) {
		cartService.delete(cseq);
		return "r:/";
	}

	private String getUserIdFromSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginMember");

		return loginUser.getId();
	}
}
