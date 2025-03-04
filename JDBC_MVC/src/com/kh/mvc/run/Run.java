package com.kh.mvc.run;

import com.kh.mvc.view.UserView;

public class Run {
	public static void main(String[] args) {
		// EmtryPoint(진입점) / (jvm이 main함수를 부름)
		/*
		 * Model 			: 데이터 관련된 모든 작업
		 * View 			: 화면 상 입 / 출력(얘는 힘 안 줄 것)
		 * Controller	:	View에서의 요청을 받아서 처리해주는 역할
		 */
		// 상속보다는 컴포지션(합성)을 더 권장함
		new UserView().mainMenu();
	}
}
