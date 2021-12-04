package com.tickle.service.impl;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickle.dao.WorkDAO;
import com.tickle.dto.Work;
import com.tickle.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkDAO dao;
	
	@Override
	public List<Work> getAllWorks(){
		return dao.getAllWorks();
	}

	@Override
	public Work getWork(Integer workID) {
		return dao.getWork(workID);
	}

	@Override
	public int createNewWork(Work work) {
		return dao.createNewWork(work);
		
	}

	@Override
	public int requestApplication(Integer workID) {
		
		return dao.requestApplication(workID);
	}

	@Override
	public List<Work> getRegisterdWorks(Integer uID) {
		return dao.getRegisteredWorks(uID);
	}

	@Override
	public List<Work> getRequestedWorks(Integer uID) {
		return dao.getRequestedWorks(uID);
	}

	@Override
	public int acceptApplication(Integer workID) {
		return dao.acceptApplication(workID);
	}

	@Override
	public int refuseApplication(Integer workID) {
		return dao.refuseApplication(workID);
	}

	@Override
	public int setWorkStatus(Integer workStatus, Integer workID) {
		return dao.setWorkStatus(workStatus, workID);
	}

	

}
