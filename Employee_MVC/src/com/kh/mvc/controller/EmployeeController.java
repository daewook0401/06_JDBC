package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dto.EmployeeDTO;
import com.kh.mvc.model.service.EmployeeService;

public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	public List<EmployeeDTO> selectEmployee(String sql, String[] columnsPart){
		return employeeService.selectEmployee(sql, columnsPart);
	}
}
