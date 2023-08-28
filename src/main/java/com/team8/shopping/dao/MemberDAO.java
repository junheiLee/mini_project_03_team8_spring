package com.team8.shopping.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public MemberVO getMemberByIdAndPwd(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.getMemberByIdAndPwd", memberVO);
	}
}
