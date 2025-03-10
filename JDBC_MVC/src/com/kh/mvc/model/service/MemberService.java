package com.kh.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * Service : 비즈니스로직 / 의사결정코드를 작성하는 부분
 * 					 Controller에서는 Service단의 메소드를 호출
 * 					 Service에서 실질적인 동작시켜야하는 코드를 작성
 * 					 => Service단을 추가함으로 DAO는 순수하게 SQL문을
 * 							처리하는 부분만 남겨놓을 것
 */
public class MemberService {
	
	private UserDAO userDao = new UserDAO();
	
	public List<UserDTO> findAll(){
		Connection conn = JdbcUtil.getConnection();
		List<UserDTO> list = userDao.findAll(conn);
		
		return list;
	}
	
	
	public int insertUser(String userId, String userPw, String userName) {
		Connection conn = JdbcUtil.getConnection();
		
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		return userDao.insertUser(conn, user);
	}
	
	public String findUserName(String userId, String userPw) {
		Connection conn = JdbcUtil.getConnection();
		String userName = userDao.findUserName(conn, userId, userPw);
		if (!userName.isBlank()) {
			return userName;
		} else {
			return "이름없음";
		}
	}
	
	public int changePw(String userId, String newPw) {
		Connection conn = JdbcUtil.getConnection();
		
		return userDao.changePw(conn, userId, newPw);
	}
	
	public int deleteUser(String userId, String userPw) {
		Connection conn = JdbcUtil.getConnection();
		return userDao.deleteUser(conn, userId, userPw);
	}
	
	public UserDTO findUserNo(int userNo) {
		Connection conn = JdbcUtil.getConnection();
		return userDao.findUserNo(conn, userNo);
	}
	
	public UserDTO userIdSearch(String userId) {
		Connection conn = JdbcUtil.getConnection();
		return userDao.userIdSearch(conn, userId);
	}
}
