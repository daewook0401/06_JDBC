package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		// EMPLOYEE 테이블에서 모든 사원의 사번, 이름, 급여를 급여 오름차순으로 조회
		
		/* 1.JDBC 객체 참조 변수 선언 */
		Connection 	conn = null; // DB 연결 정보, 연결하는 객체
		Statement 	stmt = null; // SQL 수행, 결과 반환받는 객체
		ResultSet 	rs = null;	 // SELECT 수행 결과 저장 객체
		
		try {
			/* 2. DriverManager 객체를 이용해 Connection 객체 생성 */
			
			// Oracle JDBC Driver를 메모리에 적재 == 객체로 만듦
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB 연결 정보
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH04_KDW";
			String password = "KH1234";
			
			conn = DriverManager.getConnection(
					type + host + port + dbName,
					userName, password
					);
			
			/* 3. SQL 작성 */
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT EMP_ID, EMP_NAME, SALARY ");
			sb.append("FROM EMPLOYEE ");
			sb.append("ORDER BY SALARY ASC");
			
			String sql = sb.toString();
			
			/* 4. sql을 전달하고 결과를 받아올 Statement 객체 생성 */
			stmt = conn.createStatement();
			
			/* 5. Statement 객체를 이용해서 SQL을 DB로 전달 후 수행 
			 * 1) SELECT문 : executeQuery() ResultSet 반환
			 * 2) DML 문 : executeUpdate()
			 *  */
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				// rs.next() : ResultSet의 Cursor를 다음 행으로 이동
				// 다음 행이 있으면 true, 없으면 false
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("Salary");
				System.out.printf("%s / %s / %d \n",empId, empName,salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
