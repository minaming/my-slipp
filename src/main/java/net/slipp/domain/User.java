package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// 클라에서 전송받는 data가 많아서, method에서 받아야 할 인자가 많아진다 하면
// 그냥 이렇게 User 클래스 따로 만들어가지고 setter, toString 만들어서 쓰는게 나음
// html의 form 태그의 name property과 변수이름이 같으면 됨
@Entity // database와 연결하는 녀석이라는 것을 나타냄
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=true, length=20, unique=true) // nullable = true가 디폴트
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
	
	public Long getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", userEmail="
				+ userEmail + "]";
	}
	
	// 유저정보 UPDATE시 사용되는 메소드
	public void updateInfo(User newUser) {
		this.password = newUser.password;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}
}
