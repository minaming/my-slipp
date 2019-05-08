package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/helloworld")
	public String welcome(String name, int age, Model model) {
		System.out.println("name: " + name + " / age: " + age);
		
		// 본 controller에서 model에 데이터를 저장.
		// 받은 name 값을 model에 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		// view인 welcome.html로 데이터를 날린다.
		return "welcome";
	}
}
