package com.team8.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.dao.MemberDAO;
import com.team8.shopping.vo.AddressVO;
import com.team8.shopping.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional(readOnly = true)
	public MemberVO loginPro(MemberVO memberVO) {
		return memberDAO.getMemberByIdAndPwd(memberVO);
	}
	
	@Transactional(readOnly = true)
	public boolean checkIdAvailability(String id) {
		MemberVO memberVO = memberDAO.getMemberById(id);
		return memberVO == null;
	}
	
	@Transactional(readOnly = true)
	public List<AddressVO> listAddress(String dong) {
		return memberDAO.selectAddressByDong(dong);
	}
	
	@Transactional
	public boolean joinPro(MemberVO memberVO) {
		int result = memberDAO.insertMember(memberVO);
		return result > 0;
	}
	
	@Transactional(readOnly = true)
	public String findMemberId(MemberVO memberVO) {
		return memberDAO.findMemberId(memberVO);
	}
	
	@Transactional(readOnly = true)
	public String findMemberPassword(MemberVO memberVO) {
		return memberDAO.findMemberPassword(memberVO);
	}
}
