package com.tickle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickle.dao.CheckDAO;
import com.tickle.dto.Check;
import com.tickle.service.CheckService;

@Service
public class CheckServiceImpl implements CheckService {

	@Autowired
	private CheckDAO dao;
	
	@Override
	public List<Check> getCheckList(Integer workID) {
		return dao.getCheckList(workID);
	}

	@Override
	public Check getCheck(Integer stepID) {
		return dao.getCheck(stepID);
	}

	@Override
	public void createCheck(Check check) {
		dao.createCheck(check);
		
	}

	@Override
	public int checkDone(Integer stepID) {
		return dao.checkDone(stepID);
	}

}
