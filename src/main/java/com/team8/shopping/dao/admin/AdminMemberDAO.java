package com.team8.shopping.dao.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team8.shopping.vo.WorkerVO;

@Repository
public class AdminMemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public WorkerVO workerCheck(WorkerVO workerVO) throws DataAccessException {
		System.out.println(workerVO.getId() + "============" + workerVO.getPwd());
		return sqlSession.selectOne("mapper.member.workerCheck", workerVO);
	}
}
