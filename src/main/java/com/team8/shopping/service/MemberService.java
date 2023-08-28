package com.team8.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.dao.MemberDAO;
import com.team8.shopping.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional(readOnly = true)
	public MemberVO loginPro(MemberVO memberVO) {
		return memberDAO.getMemberByIdAndPwd(memberVO);
	}
}
