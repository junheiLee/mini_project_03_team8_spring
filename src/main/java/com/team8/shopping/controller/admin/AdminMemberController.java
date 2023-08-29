package com.team8.shopping.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team8.shopping.service.admin.AdminMemberService;
import com.team8.shopping.vo.MemberVO;
import com.team8.shopping.vo.WorkerVO;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Autowired
	private AdminMemberService adminMemberService;

	@RequestMapping(value = "/login")
	public String login() {
		return "admin/main";
	}

	@RequestMapping(value = "/login_pro", method = RequestMethod.POST)
	public String loginPro(@ModelAttribute WorkerVO workerVO, HttpSession session, Model model) {
		WorkerVO loginWorker = adminMemberService.loginPro(workerVO);
		if (loginWorker != null) {
			session.setAttribute("workerId", workerVO.getId());
			return "redirect:/admin/item/listProduct";
		} else {
			model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "admin/main";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/admin/member/login";
	}
	
	@RequestMapping(value = "/listMember", method = {RequestMethod.GET, RequestMethod.POST})
	public String listMember(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
												Model model) {
		List<MemberVO> memberList = adminMemberService.listMember(keyword);
		model.addAttribute("memberList", memberList);
		return "admin/member/memberList";
	}

}



