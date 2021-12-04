package com.tickle.dao;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import com.tickle.dto.Work;

@Repository
public interface WorkDAO {

	List<Work> getAllWorks();

	Work getWork(Integer workID);

	int createNewWork(Work work);

	int requestApplication(Integer workID);
	
	List<Work> getRegisteredWorks(Integer uID);

	List<Work> getRequestedWorks(Integer uID);
	
	int acceptApplication(Integer workID);
	
	int refuseApplication(Integer workID);
	
	int setWorkStatus(Integer workStatus, Integer workID);
}
