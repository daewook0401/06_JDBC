package com.kh.mvc.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;


/**
 * MemberView 클래스는 JDBC실습을 위해 생성하였으며,
 * 사용자에게 입력 및 출력을 수행하는 메소드를 제공합니다.
 * 
 * @author : 종로 C강의장
 * @version : 0.1
 * @date : 2025-03-04
 */
public class UserView {
	
	private Scanner sc= new Scanner(System.in); 
	private UserController userController = new UserController();
	// 코드의 중복은 유지보수를 힘들게 한다.
	
	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력해주는 메소드입니다.
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("--- USER테이블 관리 프로그램 ---");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가"); // 값을 입력할 수 있도록 유도를 해야함
			System.out.println("3. 비밀번호수정");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 번호로 단일회원 조회");
			System.out.println("6. 회원 아이디 검색");
			System.out.println("9. 프로그램 종료");
			System.out.print("이용할 메뉴를 선택해주세요 > ");
			int menuNo = 0;
			try {
				menuNo = sc.nextInt();
			} catch(InputMismatchException e) {
				sc.nextLine();
				continue;
			}
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : findAll(); break;
			case 2 : insertUser(); break;
			case 3 : updatePassword(); break;
			case 4 : deleteUser(); break;
			case 5 : findUserNo(); break;
			case 6 : userIdSearch(); break;
			case 9 : System.out.println("프로그램 종료"); return;
			default : System.out.println("잘못된 메뉴 선택");
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void findAll(){
		System.out.println("\n--- 회원 전체 목록 ---");
		List<UserDTO> list = userController.findAll();
		
		System.out.println("\n조회된 총 회원의 수는 " + list.size() + "명 입니다.");			
		
		if(!list.isEmpty()) {
			
			System.out.println("==============================");
			for(UserDTO user: list) {
				System.out.println(user.getUserName() + "님의 정보");
				System.out.print("아이디 : " + user.getUserId());
				System.out.println("\t\t가입일 : " + user.getEnrollDate());
				System.out.println();
			}
			System.out.println("=======================================");
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}
		
	}
	
	/**
	 * TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	 */
	private void insertUser() {
		System.out.println("--- 회원 추가 페이지입니다. ---");
		
		System.out.print("아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		/*
		while(true) {
			if(userId.length() > 30) {
				System.out.println("아이디는 30자 이내로 입력해주세요.");
			}
		}
		*/
		// UNIQUE했다고 치고 입력받은 아이디 가지고 DB가서 WHERE조건절에다가 사용자가 입력한 아이디 넣어서
		// 조회 결과있으면 혼쭐내주기
		
		System.out.print("비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();
		System.out.print("이름을 입력하세요 > ");
		String userName = sc.nextLine();
		
		int result = userController.insertUser(userId, userPw, userName);
		if(result > 0) {
			System.out.println(userName + "님 가입에 성공하셨습니다.");
			
		} else {
			System.out.println("회원 추가에 실패했습니다. 다시 시도해주세요");
		}
	}
	
	private void updatePassword() {
		System.out.println("--- 비밀번호 수정 페이지 ---");
		String userId;
		String userPw;
		String userName;
		while(true) {
			System.out.print("아이디를 입력하세요 > ");
			userId = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 > ");
			userPw = sc.nextLine();
			userName = userController.findUser(userId, userPw);
			if (userName.equals("이름없음")) {
				System.out.println("아이디 비번이 맞지 않습니다.");
				continue;
			} else {
				break;
			}
		}
		while(true) {
			System.out.print(userName + "님의 수정할 비밀번호를 입력하세요 > ");
			String newPw = sc.nextLine();
			System.out.print("수정할 비밀번호를 다시 입력하세요 > ");
			String newPwCon = sc.nextLine();
			if(!newPw.equals(newPwCon)) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			int result = userController.changePw(userId, newPw);
			if (result == 1) {
				System.out.println("수정완료");
				break;
			} else {
				System.out.println("실패하였습니다. 재시도 하시겠습니까? Y / N");
				String an = sc.nextLine();
				if (an.equals("Y")) continue;
				else break;
			}
		}
	}
	private void deleteUser() {
		System.out.println("--- 회원탈퇴 페이지 ---");
		String userId;
		String userPw;
		String userName;
		int rs = 0;
		while(true) {
			System.out.print("아이디를 입력하세요 > ");
			userId = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 > ");
			userPw = sc.nextLine();
			userName = userController.findUser(userId, userPw);
			if (userName.equals("이름없음")) {
				System.out.println("아이디 비번이 맞지 않습니다.");
				continue;
			} else {
				break;
			}
		}
		System.out.print(userName + " 회원님을 삭제 하시겠습니까? 예 : Y / 아니요 : Y제외 아무키 > ");
		String answer = sc.nextLine();
		if (answer.toUpperCase().equals("Y")) {
			rs = userController.deleteUser(userId, userPw);
		}
		else {
			System.out.println("삭제를 취소합니다."); 
			return;
		}
		if (rs == 1) System.out.println("삭제하였습니다.");
		else System.out.println("삭제 실패하였습니다.");
	}
	
	private void findUserNo() {
		System.out.println("--- 회원번호로 단일회원 조회 페이지 ---");
		System.out.print("회원 번호를 입력하세요 > ");
		int userNo = sc.nextInt();
		UserDTO user = userController.findUserNo(userNo);
		if (user.getUserNo() == 0) {
			System.out.println("없는 회원번호 입니다.");
			return;
		}
		System.out.println(user.getUserNo() + "번님의 회원 정보");
		System.out.println("이름 : " + user.getUserName());
		System.out.print("아이디 : " + user.getUserId());
		System.out.println("\t\t가입일 : " + user.getEnrollDate());
	}
	
	private void userIdSearch() {
		
		System.out.println("--- 회원 아이디로 회원 조회 페이지 ---");
		System.out.print("회원 아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		UserDTO user = userController.userIdSearch(userId);
		if (user.getUserNo() == 0) {
			System.out.println("없는 회원 아이디 입니다.");
			return;
		}
		System.out.println(user.getUserNo() + "번님의 회원 정보");
		System.out.println("이름 : " + user.getUserName());
		System.out.print("아이디 : " + user.getUserId());
		System.out.println("\t\t가입일 : " + user.getEnrollDate());
	
	}
}
