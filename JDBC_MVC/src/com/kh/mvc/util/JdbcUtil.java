package com.kh.mvc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	/*
	private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	private final String USERNAME = "KH04_KDW";
	private final String PASSWORD = "KH1234";
	*/
	/* 
	 * JDBC API 사용 중 중복 코드가 너무 많음
	 * 중복된 코드를 메소드로 분리하여 필요할 때 마다 '재사용'하자
	 */
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			final String USERNAME = "KH04_KDW";
			final String PASSWORD = "KH1234";
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void updateClose(PreparedStatement stmt, Connection conn) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void queryClose(PreparedStatement stmt, Connection conn, ResultSet rs) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
