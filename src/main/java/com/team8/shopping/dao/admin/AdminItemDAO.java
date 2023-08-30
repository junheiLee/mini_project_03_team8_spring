package com.team8.shopping.dao.admin;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.ItemVO;

@Repository
public class AdminItemDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ItemVO> selectAllProduct(RowBounds rowBounds) throws DataAccessException {
		List<ItemVO> itemList = null;
		itemList = sqlSession.selectList("mapper.item.selectAllProduct", null, rowBounds);
		return itemList;
	}
	
	public List<ItemVO> selectProductByKeyword(String keyword, RowBounds rowBounds) throws DataAccessException {
		List<ItemVO> itemList = null;
		itemList = sqlSession.selectList("mapper.item.selectProductByKeyword", keyword, rowBounds);
		return itemList;
	}
	
	public int getProductCnt() throws DataAccessException {
		return sqlSession.selectOne("mapper.item.getProductCnt");
	}
	
	public int getProductCntByKeyword(String keyword) throws DataAccessException {
		return sqlSession.selectOne("mapper.item.getProductCntByKeyword", keyword);
	}
	
	public ItemVO getProduct(String pseq) throws DataAccessException {
		return sqlSession.selectOne("mapper.item.getProduct", pseq);
	}
	
	public void insertItem(ItemVO itemVO) {
		sqlSession.insert("mapper.item.insertItem", itemVO);
	}
	
	public void updateProduct(ItemVO itemVO) {
		sqlSession.update("mapper.item.updateProduct", itemVO);
	}
	
}
