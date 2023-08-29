package com.team8.shopping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.CartVO;
import com.team8.shopping.vo.OrderVO;

@Repository
public class OrderDAO {

	private final SqlSession sqlSession;

	@Autowired
	public OrderDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// selectSeqOrderIng - 현재 진행 중인 주문 내역
	public List<Integer> findOseqInProgressByUserId(String userId) {
		return sqlSession.selectList("mapper.order.findOseqInProgressByUserId", userId);
	}

	// listOrderById - 파라미터 넣어야함
	public List<OrderVO> findAllDetail(OrderVO orderVO) {
		// @Param("userId") String userId,
		// @Param("searchStr") String searchStr,
		// @Param("oseq") int oseq) {
		return sqlSession.selectList("mapper.order.findAllDetail", orderVO);
	}

	public int findMaxOseq() {
		return sqlSession.selectOne("mapper.order.findMaxOseq");
	}

	public void insert(String userId) {

		sqlSession.insert("mapper.order.insert", userId);
	}

	// 파수
	public void insertDetail(CartVO cartVO, int maxOseq) {
		Map<String, Object> temp = new HashMap<String, Object>();
		
		temp.put("pseq", cartVO.getPseq());
		temp.put("quantity", cartVO.getQuantity());
		temp.put("maxOseq", maxOseq);
		
		sqlSession.insert("mapper.order.insertDetail", temp);
	}

	public void updateCart(CartVO cartVO) {
		sqlSession.update("mapper.order.updateCart", cartVO);
	}

}
