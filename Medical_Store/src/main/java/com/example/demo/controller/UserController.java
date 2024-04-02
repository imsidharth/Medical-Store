package com.example.demo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Dto.UserDto;
import com.example.demo.Service.UserService;



@Controller
public class UserController {
	
	
	
	
	@Autowired
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/home")
	public String home(Model model ) {
		
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model , UserDto userDto) {
		model.addAttribute("user" , userDto);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model , UserDto userDto) {
		model.addAttribute("user" , userDto);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userDto) {
		userService.save(userDto);
		return "redirect:/register?success";
	}

	
	
}


	