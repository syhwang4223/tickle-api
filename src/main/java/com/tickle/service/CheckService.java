package com.tickle.service;

import java.util.List;

import com.tickle.dto.Check;

public interface CheckService {

	List<Check> getCheckList(Integer workID);

	Check getCheck(Integer stepID);

	void createCheck(Check check);

	int checkDone(Integer stepID);
}
