package example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample1 {
	public static void main(String[] args) {
		
		/* 입력 받은 아이디가 포함된 사용자의
		 * 사용자 번호, 아이디, 이름, 가입일을 
		 * 회원 번호 오름차순으로 조회(SELECT)
		 * 
		 */
		
		/* 1. JDBC 객체 참조 변수 선언 */
		Connection conn = null; // DB 연결 정보를 가지고 연결하는 객체
		Statement stmt = null; // SQL 수행, 결과 반환 받는 객체
		ResultSet rs = null; // SELECT 결과를 저장하고 1행씩 접근하는 객체
		
		try {
			/* 2. DriverManager 객체를 이용해 Connection 객체 생성하기 */
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH04_KDW";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			/* 3. SQL 작성 */
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할 아이디 입력 : ");
			String idInput = sc.next();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECt USER_NO, USER_ID, USER_NAME, ENROLL_DATE FROM TB_USER ");
			sb.append("WHERE USER_ID LIKE '%");
			sb.append(idInput);
			sb.append("%' ORDER BY USER_NO ASC");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) { // 커서를 다음 행으로 이동, 행이 있으면 true
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String name = rs.getString("USER_NAME");
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				System.out.printf("%d / %s / %s / %s \n", userNo, userId, name, enrollDate.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
}
