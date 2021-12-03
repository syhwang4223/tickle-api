package com.tickle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickle.dto.Work;
import com.tickle.dto.User;
import com.tickle.service.UserService;
import com.tickle.service.WorkService;

@RestController
public class MainController {

	@Autowired
	private WorkService workModel;

	@Autowired
	private UserService userModel;

	// 전체 작업 리스트 조회
	@GetMapping(path = "/works")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Work> getAllWorks() {
		return workModel.getAllWorks();
	}

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
		return work;
	}

	// 작업 신청?
	@PutMapping(path = "/request/{workID}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int requestApplication(@PathVariable Integer workID) {

		Work work = workModel.getWork(workID);
		work.setEmployeeID(2);
		work.setWorkStatus(3);

		int requestSuccess = workModel.requestApplication(workID);
		return requestSuccess;// 작업 요청 신청이 제대로 되면 1 반환
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
		User user = (User) session.getAttribute("loginUser");
		return workModel.getRegisterdWorks(user.getuID());
	}

	// 내가 신청한 작업 목록 전체
	@GetMapping(path = "/mywork-employee")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Work> getRequestedWork(HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		return workModel.getRequestedWorks(user.getuID());
	}

}
