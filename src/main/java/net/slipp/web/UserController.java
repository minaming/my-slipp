package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	//여러명의 유저 정보를 받을 수 있는 List 객체를 만든다.
	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println(user);
		//유저가 가입하면 users 리스트에 추가한다.
		users.add(user);
		//그냥 list.html로 이동하는게 아니라 해당 메소드가 완료되면 list로 redirect하라는 의미
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
}
