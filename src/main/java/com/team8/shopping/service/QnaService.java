package com.team8.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.team8.shopping.dao.QnaDAO;
import com.team8.shopping.vo.QnaVO;


@Service
public class QnaService {
	
	@Autowired
	private QnaDAO qnaDAO;

	public List<QnaVO> listQna(String id) throws DataAccessException {
		return qnaDAO.selectQnaById(id);
	}

	public QnaVO getQna(int qseq) throws DataAccessException {
		QnaVO qna = null;
		qna = qnaDAO.getQna(qseq);
		return qna;
	}

	public int addQna(QnaVO qnaVO) throws DataAccessException {
		return qnaDAO.insertQna(qnaVO);
	}
}
