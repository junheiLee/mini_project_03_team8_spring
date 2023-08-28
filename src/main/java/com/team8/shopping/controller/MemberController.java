package com.team8.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.service.MemberService;
import com.team8.shopping.vo.AddressVO;
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
	
	@RequestMapping(value = "/contract")
	public String contract() {
		return "member/contract";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "member/join";
	}
	
	@RequestMapping(value = "/find_zip_num")
	public String findZipNum(@RequestParam(value = "dong", required = false) String dong, Model model) {
		if (dong != null && dong.trim().equals("") == false) {
			List<AddressVO> addressList = memberService.listAddress(dong);
			model.addAttribute("addressList", addressList);
		}
		return "member/findZipNum";
	}
	
	@RequestMapping(value = "/join_pro", method = RequestMethod.POST)
	public String joinPro(@ModelAttribute MemberVO memberVO,
										@RequestParam("addr1") String addr1,
										@RequestParam("addr2") String addr2) {
		memberVO.setAddress(addr1 + " " + addr2);
		boolean success = memberService.joinPro(memberVO);
		if (success) {
			return "member/login";
		} else {
			return "member/join_fail";
		}
	}
	
	@RequestMapping(value = "/find_id_form")
	public String findIdForm() {
		return "member/findIdAndPassword";
	}
	
}
