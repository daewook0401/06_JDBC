package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.EmployeeController;
import com.kh.mvc.model.dto.EmployeeDTO;

public class EmployeeView {
	Scanner sc = new Scanner(System.in);
	EmployeeController employeeController  = new EmployeeController();
	/* EMPLOYEE테이블에 SELECT, INSERT, UPDATE, DELETE하는 프로그램을 MVC패턴으로 구현 */
	public void mainMenu() {

		while(true) {
			try {
				System.out.println("1. SELECT문");
				System.out.println("2. INSERT문");
				System.out.println("3. UPDATE문");
				System.out.println("4. DELETE문");
				System.out.println("9. 종료");
				System.out.print("입력 > ");
				int input = sc.nextInt();
				
				switch(input) {
				case 1: selectEmployee(); break;
				case 2: break;
				case 3: break;
				case 4: break;
				case 9: return;
				default: System.out.println("잘못 입력함");
				}
			} catch(InputMismatchException e) {
				sc.nextLine();
				e.printStackTrace();
			} 
		}
	}
	
	private void selectEmployee() {
		
		System.out.println("SELECT sql문을 작성하십시오. 맨 앞에 :wq 입력시 종료");
		StringBuilder sb = new StringBuilder();
		while(true) {
			String st = sc.nextLine();
			if (st.length() >= 3 && st.substring(0, 3).equals(":wq")) {
				break;
			}
			sb.append(" ");
			sb.append(st);
		}
		String sql = sb.toString();
		String columnsPartStr = sql.substring(
        sql.toUpperCase().indexOf("SELECT") + "SELECT".length(),
        sql.toUpperCase().indexOf("FROM")
    ).trim();
		String[] columnsPart = columnsPartStr.split(",");
		for (int i = 0; i < columnsPart.length; i++) {
      // 공백 제거
      String col = columnsPart[i].trim();
      // 대소문자 구분 없이 'AS' 기준으로 분리
      if (col.toUpperCase().contains("AS")) {
          col = col.split("(?i)AS")[0].trim(); // (?i)는 대소문자 무시 옵션
      }
      // 필요한 경우, 산술연산 등으로 인해 복잡한 경우 추가 처리할 수 있음.
      columnsPart[i] = col;
  }
//		String[] columnsPart = sql.substring(
//				sql.indexOf("SELECT ") + "SELECT ".length(),
//		    sql.indexOf(" FROM")).replace(" ", "").split(",");
		List<EmployeeDTO> employees = employeeController.selectEmployee(sql, columnsPart);
		for(String column : columnsPart) {
			System.out.print(column);
			System.out.print("\t\t");
		}
		System.out.println();
		for(EmployeeDTO employee : employees) {
			for(String col : columnsPart) {
				System.out.print(employee.getField(col));
				System.out.print("\t\t");
			}
			System.out.println();
		}
	}
}
