package com.tickle.service;

import java.util.List;

import org.apache.catalina.User;

import com.tickle.dto.Work;

public interface WorkService {
	List<Work> getAllWorks();
	
	Work getWork(Integer workID);

	void createNewWork(Work work);

	int requestApplication(Integer workID);
	
	List<Work> getRegisterdWorks(Integer uID);

	List<Work> getRequestedWorks(Integer uID);

}
