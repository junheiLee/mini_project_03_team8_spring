package com.team8.shopping.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.team8.shopping.dao.QnaDAO;
import com.team8.shopping.dao.admin.AdminQnaDAO;
import com.team8.shopping.vo.QnaVO;


@Service
public class AdminQnaService {
	
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private AdminQnaDAO adminQnaDAO;

	public List<QnaVO> selectAllQnas() {
		return adminQnaDAO.selectAllQnas();
	}

	public QnaVO getQna(int qseq) {
		return qnaDAO.getQna(qseq);
	}

	public void resaveQna(QnaVO qnaVO) {
		adminQnaDAO.resaveQna(qnaVO);
		
	}
	
	
}
