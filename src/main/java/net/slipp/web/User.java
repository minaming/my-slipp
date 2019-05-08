package net.slipp.web;

// 클라에서 전송받는 data가 많아서, method에서 받아야 할 인자가 많아진다 하면
// 그냥 이렇게 User 클래스 따로 만들어가지고 setter, toString 만들어서 쓰는게 나음
// html의 form 태그의 name property과 변수이름이 같으면 됨
public class User {
	private String userId;
	private String password;
	private String userName;
	private String userEmail;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", userEmail="
				+ userEmail + "]";
	}
}
