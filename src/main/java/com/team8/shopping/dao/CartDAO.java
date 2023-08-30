package com.team8.shopping.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.CartVO;

@Repository
public class CartDAO {

	private final SqlSession sqlSession;

	@Autowired
	public CartDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// selectCarts
	public List<CartVO> findAllByUserId(String userId) {
		return sqlSession.selectList("mapper.cart.findAllByUserId", userId);
	}

	//insertCart
	public void insert(CartVO cartVO) {
		sqlSession.insert("mapper.cart.insert", cartVO);
	}

	//deleteCart
	public void delete(int cseq) {
		sqlSession.delete("mapper.cart.delete", cseq);
	}

}