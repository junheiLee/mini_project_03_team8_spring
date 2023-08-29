package com.team8.shopping.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<QnaVO> selectQnaById(String id) throws DataAccessException {
		List<QnaVO> qnaList = null;
		qnaList = sqlSession.selectList("mapper.qna.selectQnaById", id);
		return qnaList;
	}

	public QnaVO getQna(int qseq) throws DataAccessException {
		QnaVO qna = null;
		qna = sqlSession.selectOne("mapper.qna.selectQnaByNo", qseq);
		return qna;
	}

	public int insertQna(QnaVO qnaVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.qna.insertQna", qnaVO);
		return result;
	}
	
}
