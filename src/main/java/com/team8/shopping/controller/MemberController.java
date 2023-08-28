package com.team8.shopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team8.shopping.service.MemberService;
import com.team8.shopping.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value = "/login_pro", method = RequestMethod.POST)
	public String loginPro(@ModelAttribute MemberVO memberVO, HttpSession session) {
		MemberVO loginMember = memberService.loginPro(memberVO);
		if (loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			return "redirect:/";
		} else {
			return "member/login_fail";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
