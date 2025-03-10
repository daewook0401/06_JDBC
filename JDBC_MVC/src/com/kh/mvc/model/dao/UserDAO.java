package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD)을 전문적으로 담당하는 객체
 * DAO안에 모든 메소드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를 통해 호출된 기능을 수행
 * DB에 직접 접근한 후 SQL문을 수행하고 결과 받기(JDBC)
 */
public class UserDAO {
	
	/*
	 * JDBC 용 객체
	 * 
	 * - Connection : DB 연결정보를 담고있는 객체(IP주소, Port, 사용자명, 비번)
	 * - Statement : Connection이 가지고 있는 연결정보 DB에 SQL문을 전달하고
	 * 							 실행하고 결과도 받아오는 객체
	 * - ResultSet : 실행한 SQL문이 SELECT문일 경우 조회된 결과가 처음 담기는 객체
	 * 
	 * - PreparedStatement : SQL문을 미리 준비하는 개념
	 * 											 ?(placeholder/위치홀더)로 확보해놓은 공간을
	 * 											 사용자가 입력한 값들과 바인딩해서 SQL문을 수행
	 * 
	 * ** 처리 절차
	 * 
	 * 1) JDBC Driver등록 : 해당 DBMC에서 제공하는 클래스를 동적으로 등록
	 * 2) Connection 객체 생성 : 접속하고자하는 DB의 정보를 입력해서 생성
	 * 													 (URL, userName, passWord)
	 * 3_1) PreparedStatement 객체 생성 :
	 * 			Connection 객체를 이용해서 생성(미완성된 SQL문을 미리 전달)
	 * 3_2) 미완성된 SQL문을 완성형태로 만들어주어야 함
	 * => 미완성된 경우에만 해당 / 완성된 경우에는 생략
	 * 
	 * 4) SQL문을 실행 : executeXXX() => SQL을 인자로 전달하지 않음
	 * 									 > SELECT(DQL)	: executeQuery()
	 * 									 > DML					: executeUpdate() 
	 * 
	 * 5) 결과받기 : 
	 * 								> SELECT 	: ResultSet타입 객체(조회데이터담김)
	 * 								> DML 		: int(처리된 행의 개수)
	 * 6_1) ResultSet에 담겨있는 데이터들을 하나하나씩 뽑아서 DTO객체 필드에
	 * 			옮겨담은 후 조회 결과가 여러 행일 경우 List로 관리
	 * 6_2) 트랜잭션 처리
	 * 7(생략될 수 있음)) 자원반납(close) => 생성의 역순으로
	 * 8) 결과반환 -> Controller
	 * 								SELECT > 6_1에서 만든거
	 * 								DML : 처리된 행의 개수
	 * 
	 * 
	 */
	
	static {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ojdbc 클래스 못찾음");
		}
	}
	
	public List<UserDTO> findAll(Connection conn){
		// DB 요청
		/*
		 * VO / DTO / Entity
		 * 테이블의 한행의 데이터를 담기
		 * 
		 * 1명의 회원의 정보는 1개의 UserDTO객체의 필드에 값을 담아야겠다.
		 * 
		 * 문제점 : userDTO가 몇개가 나올지 알 수 없음
		 */
		List<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT "
										+		"USER_NO"
										+	", USER_ID"
										+	", USER_PW"
										+	", USER_NAME"
										+	", ENROLL_DATE "
										+	"FROM TB_USER "
										+ "ORDER "
												+ "BY "
														+ "ENROLL_DATE DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			// 조회 결과 컬럼 값을 DTO필드에 담는 작업 및 리스트에 요소로 추가
				UserDTO user = new UserDTO();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPw(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				list.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.queryClose(pstmt, conn, rset);
		}
		return list;	
	}
	
	/**
	 * 
	 * @param user 사용자가 입력한 아이디 / 비밀번호 / 이름이 각각 필드에 대입되어 있음
	 * @return 아직 뭐 돌려줄지 안정함
	 */
	public int insertUser(Connection conn, UserDTO user) {
//		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO TB_USER VALUES"
							+	 "(SEQ_USER_NO.NEXTVAL, ?, ?, ?, SYSDATE)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.updateClose(pstmt, conn);
		}
			
		return result;
	}
	
	public String findUserName(Connection conn, String userId, String userPw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT USER_NAME FROM TB_USER WHERE USER_ID = ? AND USER_PW = ?";
		String userName = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userName = rs.getString("USER_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.queryClose(pstmt, conn, rs);
		}
		return userName;
	}
	
	public int changePw(Connection conn, String userId, String newPw) {
		PreparedStatement pstmt = null;

		String sql = "UPDATE TB_USER SET USER_PW = ? WHERE USER_ID = ?";
		int rs=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.updateClose(pstmt, conn);
		}
		return rs;
	}
	
	public int deleteUser(Connection conn, String userId, String userPw) {
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM TB_USER WHERE USER_ID = ? AND USER_PW = ?";
		int rs = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.updateClose(pstmt, conn);
		}
		return rs;
	}
	
	public UserDTO findUserNo(Connection conn, int userNo) {
		UserDTO user = new UserDTO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM TB_USER WHERE USER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserNo(rs.getInt("USER_NO"));
				user.setUserId(rs.getString("USER_ID"));
				user.setUserPw(rs.getString("USER_PW"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.queryClose(pstmt, conn, rs);
		}
		return user;
	}
	
	public UserDTO userIdSearch(Connection conn, String userId) {
		UserDTO user = new UserDTO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserNo(rs.getInt("USER_NO"));
				user.setUserId(rs.getString("USER_ID"));
				user.setUserPw(rs.getString("USER_PW"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.queryClose(pstmt, conn, rs);
		}
		return user;
	}
}
