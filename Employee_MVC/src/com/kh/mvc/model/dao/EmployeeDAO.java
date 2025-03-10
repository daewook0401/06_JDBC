package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.EmployeeDTO;
import com.kh.mvc.util.JdbcUtil;

public class EmployeeDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ojdbc 클래스 못찾음");
		}
	}
	public List<EmployeeDTO> selectEmployee(Connection conn, String sql, String[] columnsPart) {
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				for(int i=1; i<=columnsPart.length;i++) {
					employee.setField(columnsPart[i-1], rs.getObject(i));
				}
				employees.add(employee);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.queryClose(pstmt, conn, rs);
		}
		return employees;
	}
}
