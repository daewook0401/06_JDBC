package com.kh.mvc.controller;

import java.util.List;
import java.util.Map;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

/**
 * VIEW에서 온 요청을 처리해주는 클래스입니다.
 * 메소드로 전달된 데이터값을 가공처리한 후 DAO로 전달합니다.
 * DAO로부터 반환받은 결과를 사용자가 보게될 VIEW(응답화면)에 반환합니다.
 */
public class UserController {

	private UserDAO userDao = new UserDAO();
	private MemberService userService = new MemberService();
	public List<UserDTO> findAll() {
		return userService.findAll();
	}
	
	public int insertUser(String userId, String userPw, String userName) {

		return userService.insertUser(userId, userPw, userName);
	}
	
	public String findUser(String userId, String userPw){
		return userService.findUserName(userId, userPw);
	}
	public int changePw(String userId, String newPw) {
		return userService.changePw(userId, newPw);
	}
	
	public int deleteUser(String userId, String userPw) {
		return userService.deleteUser(userId, userPw);
	}
	
	public UserDTO findUserNo(int userNo) {
		return userService.findUserNo(userNo);
	}
	
	public UserDTO userIdSearch(String userId) {
		return userService.userIdSearch(userId);
	}
}
