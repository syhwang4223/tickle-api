package com.tickle.dao;

import com.tickle.dto.Check;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CheckDAO {

	List<Check> getCheckList(Integer workID);
	
	Check getCheck(Integer stepID);
	
	void createCheck(Check check);

	int checkDone(Integer stepID);

}
