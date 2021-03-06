package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	//여러명의 유저 정보를 받을 수 있는 List 객체를 만든다.
	// private List<User> users = new ArrayList<User>();
	
	// 땡겨다 쓰기
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		// templates> user> login.html 호출
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		// 1. user가 입력한 정보를 토대로 DB에 존재하는지를 찾는다.
		// findByUserId를 UserRepository interface 내에 작성한다.
		User user = userRepository.findByUserId(userId);
		// userId을 토대로 DB 조회 시 null이면 loginform으로 redirect
		if(user == null) {
			System.out.println("Login Failed");
			return "redirect:/users/loginForm";
		}

		// 위 if문에 걸리지 않았을 때(!null) password가 같은지 여부 확인
		// password가 같지 않을 때 실행
		// 이렇게 user에서 자꾸 값을 꺼내는 것보다는 (get으로) 
		// matchPassword와같은 메소드를 User 안에 만들어서 쓰는것이 좋다.
		if(!user.matchPassword(password)) {
			System.out.println("Login Failed");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login Successfully");
		// 로그인이 되었음을 어딘가에 남겨놔야함 (세션)
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("LOG OUT Successfully");
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);		
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	// conventional 하게 /users 라고 한다.
//	@PostMapping("/user/create")
	// 위에 requestmapping("/users")를 기본으로하고, 거기 뒤에 들어가는거를 postmapping에 써준다
	@PostMapping("")
	public String create(User user) {
		System.out.println("user : " + user);
		//유저가 가입하면 users 리스트에 추가한다.
		// users.add(user);
		userRepository.save(user);
		//그냥 list.html로 이동하는게 아니라 해당 메소드가 완료되면 list로 redirect하라는 의미
		// return "redirect:/user/list";
		return "redirect:/users";
	}
	
	// conventional 하게 user/list가 아닌, users라고 한다.
//	@GetMapping("/user/list")
	@GetMapping("")
	public String list(Model model) {
		// model에다가 repository의 모든 정보를 담는다.
		// model.addAttribute("이름", "넘길 값")
//		model.addAttribute("users", users);
		// userRepository 만들고 난 후에는 이렇게 쓸 수 있다.
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	// id
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		// 로그인 되었는가 체크하는 부분
		// if(tempUser == null) {
		if(HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		// User sessionedUser = (User)tempUser;
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
		// if(!id.equals(sessionedUser.getId())){
			throw new IllegalStateException("Access not allowed.");
		}
		
		// model 객체를 이용해, 해당 id에 속하는 데이터를 user에 담고
		// 해당 데이터를 view로 전달한다 (view 파일을 리턴한다)
		model.addAttribute("user", userRepository.findById(id).get());
		return "/user/updateForm";
	}
	
	// UPDATE 영역
	// @PostMapping("/{id}") updateForm에서 input type으로 put 지정해주면 putmapping으로 쓸 수 있다.
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) { //param으로 받는 user는 새롭게 입력한 정보의 user
		
		// Object tempUser = session.getAttribute("sessionedUser");
		// if(tempUser == null) {
		
		if(HttpSessionUtils.isLoginUser(session)){
			return "redirect:/users/loginForm";
		}
		
		// User sessionedUser = (User)tempUser;
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
		// if(!id.equals(sessionedUser.getId())){
			throw new IllegalStateException("ACCESS NOT ALLOWED");
		}
		
		//이 user는 원래 DB에 저장되어 있는 정보의 user
		User user = userRepository.findById(id).get();
		user.updateInfo(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
