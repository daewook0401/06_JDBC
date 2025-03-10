package com.kh.mvc.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.model.dao.EmployeeDAO;
import com.kh.mvc.model.dto.EmployeeDTO;
import com.kh.mvc.util.JdbcUtil;

public class EmployeeService {
	private EmployeeDAO employeeDao = new EmployeeDAO();
	public List<EmployeeDTO> selectEmployee(String sql, String[] columnsPart) {
		Connection conn = JdbcUtil.getConnection();
		return employeeDao.selectEmployee(conn, sql, columnsPart);
	}
}
