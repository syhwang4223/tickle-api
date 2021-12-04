package com.tickle.controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickle.dto.Work;
import com.tickle.dto.Check;
import com.tickle.dto.User;
import com.tickle.service.CheckService;
import com.tickle.service.UserService;
import com.tickle.service.WorkService;

@RestController
public class MainController {

	@Autowired
	private WorkService workModel;

	@Autowired
	private UserService userModel;

	@Autowired
	private CheckService checkModel;

	// 전체 작업 리스트 조회
	@GetMapping(path = "/works")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Work> getAllWorks() {
		return workModel.getAllWorks();
	}

	// 체크리스트 조회
	@GetMapping(path = "/checklist/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Check> getCheckList(@PathVariable Integer workID) {
		return checkModel.getCheckList(workID);
	}

	// 하나의 체크 조회
	@GetMapping(path = "/check/{stepID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Check getCheck(@PathVariable Integer stepID) {
		return checkModel.getCheck(stepID);
	}
	//체크
	@PutMapping(path = "/check/{stepID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int checkDone(@PathVariable Integer stepID) {
		
		int success = checkModel.checkDone(stepID);
		return success;
	}
	/*
	// 체크 등록
	@PostMapping(path="check-form")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Check createCheck(@RequestBody Check check) {
		checkModel.createCheck(check);
		return check;
	}*/
	
	// 하나의 작업 정보 열람
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/works/{workID}")
	public Work getWork(@PathVariable Integer workID) {
		return workModel.getWork(workID);
	}

	// 작업 등록
	@PostMapping(path = "/work-form")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Work createNewWork(@RequestBody Work work) {

		work.setEmployerID(1);
		workModel.createNewWork(work);
		Check[] checklist = work.getChecklist();
		
		for (int i = 0;i<3;i++) {
			checklist[i].setWorkID(work.getWorkID());
			checkModel.createCheck(checklist[i]);
		}

		
		
		return work;
	}

	// 작업 신청?
	@PutMapping(path = "/request/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String requestApplication(@PathVariable Integer workID) {
		workModel.requestApplication(workID);
		return userModel.getUserName(2);
		// 작업 선공 신청 시 신청자의 이름 반환
	}

	// 작업 신청 수락
	@PutMapping(path = "/accept/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int acceptApplication(@PathVariable Integer workID) {
		int reqeustSuccess = workModel.acceptApplication(workID);
		return reqeustSuccess;
		// 신청 수락 성공 시 1 반환
	}

	// 작업 신청 거절
	@PutMapping(path = "/refuse/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int refuseApplication(@PathVariable Integer workID) {
		int reqeustSuccess = workModel.refuseApplication(workID);
		return reqeustSuccess;
		// 신청 수락 성공 시 1 반환
	}

	// 작업 완료 요청
	@PutMapping(path = "/lookover-request/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int lookoverRequest(@PathVariable Integer workID) {
		int success = workModel.setWorkStatus(4, workID);
		return success;
	}

	// 작업 완료 요청 수락
	@PutMapping(path = "/lookover-accept/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int lookoverAccept(@PathVariable Integer workID) {
		int success = workModel.setWorkStatus(2, workID);
		return success;
	}

	// 작업 완료 요청 거절
	@PutMapping(path = "/lookover-refuse/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int lookoverRefuse(@PathVariable Integer workID) {
		int success = workModel.setWorkStatus(1, workID);
		return success;
	}

	// 보수지급
	@PutMapping(path = "/payment/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int paymentApplication(@PathVariable Integer workID) {
		int success = workModel.setWorkStatus(5, workID);
		return success;
	}

	// 로그인
	@PostMapping(path = "/login")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int login(@RequestBody User user, HttpServletRequest req) {

		HttpSession session = req.getSession();
		User u = userModel.login(user);

		if (u != null) {
			session.setAttribute("loginUser", u);
			return 1;
		}

		else {
			session.setAttribute("loginUser", null);
			return 0;
		}
	}

	// 로그아웃
	@GetMapping(path = "/logout")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}

	// 로그인 멤버 테스트용
	@GetMapping(path = "/mypage")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public User login(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		return loginUser;
	}

	// 내가 등록한 작업 목록 전체
	@GetMapping(path = "/mywork-employer")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Work> getRegisteredWork(HttpSession session) {
		return workModel.getRegisterdWorks(1);
	}

	// 내가 신청한 작업 목록 전체
	@GetMapping(path = "/mywork-employee")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Work> getRequestedWork(HttpSession session) {
		return workModel.getRequestedWorks(2);
	}

}
