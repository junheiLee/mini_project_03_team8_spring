package com.team8.shopping.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.QnaVO;

@Repository
public class AdminQnaDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<QnaVO> selectAllQnas() throws DataAccessException {
		List<QnaVO> qnaList = null;
		qnaList = sqlSession.selectList("mapper.qna.selectAllQnas");
		return qnaList;
	}

	public void resaveQna(QnaVO qnaVO) throws DataAccessException {
		sqlSession.update("mapper.qna.updateQna", qnaVO);		
	}
	
}
