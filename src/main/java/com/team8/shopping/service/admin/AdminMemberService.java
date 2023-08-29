package com.team8.shopping.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team8.shopping.dao.admin.AdminMemberDAO;
import com.team8.shopping.vo.WorkerVO;

@Service
public class AdminMemberService {

	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	@Transactional(readOnly = true)
	public WorkerVO loginPro(WorkerVO workerVO) {
		return adminMemberDAO.workerCheck(workerVO);
	}
}
