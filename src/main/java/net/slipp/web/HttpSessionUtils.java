package net.slipp.web;

import javax.servlet.http.HttpSession;

import net.slipp.domain.User;

public class HttpSessionUtils {

		// 대문자로 쓰고, 사이는 underscore로 사용.
		// 상수 지정 시에 static final String 으로 받는다.
		public static final String USER_SESSION_KEY = "sessionedUser";
		
		// 1) 세션의 값에 따라 해당 사용자가 로그인 유무를 판단할 수 있는 메소드
		public static boolean isLoginUser(HttpSession session) {
			Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
			// 로그인하지 않았으면 false를 반환한다.
			if(sessionedUser == null) {
				return false;
			}
			// 로그인 했으면 true를 반환한다.
			return true;
		}
		
		// 2) 로그인 사용자일 경우 또 하나 필요한 메소드: 유저 객체를 쓸 필요가 있어서
		public static User getUserFromSession(HttpSession session) {
			// 로그인 한 유저가 아니라면, null을 반환한다. 
			if(!isLoginUser(session)) {
				return null;
			}
			// 로그인 한 유저일 경우, 유저의 세션정보를 담아 반환한다.
			return (User)session.getAttribute(USER_SESSION_KEY);
		}
}
