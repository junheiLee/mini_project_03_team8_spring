package com.team8.shopping.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.AddressVO;
import com.team8.shopping.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public MemberVO getMemberByIdAndPwd(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.getMemberByIdAndPwd", memberVO);
	}
	
	public MemberVO getMemberById(String id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.getMemberById", id);
	}
	
	public List<AddressVO> selectAddressByDong(String dong) throws DataAccessException {
		return sqlSession.selectList("mapper.member.selectAddressByDong", dong);
	}
	
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		return sqlSession.insert("mapper.member.insertMember", memberVO);
	}
	
	public String findMemberId(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.findMemberId", memberVO);
	}
	
	public String findMemberPassword(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.findMemberPassword", memberVO);
	}
}
