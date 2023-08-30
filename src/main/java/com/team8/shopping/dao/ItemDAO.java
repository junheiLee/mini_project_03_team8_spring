package com.team8.shopping.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.ItemVO;

@Repository
public class ItemDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ItemVO> listNewProduct() throws DataAccessException {
		return sqlSession.selectList("mapper.item.listNewProduct");
	}
	
	public List<ItemVO> listBestProduct() throws DataAccessException {
		return sqlSession.selectList("mapper.item.listBestProduct");
	}
	
	public ItemVO getProduct(String pseq) {
		return sqlSession.selectOne("mapper.item.getProduct", pseq);
	}
	
	public List<ItemVO> listKindProduct(String kind){
		return sqlSession.selectList("mapper.item.listKindProduct", kind);
	}
}