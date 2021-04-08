package com.claim.kidsstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.UserRepository;
import com.claim.kidsstore.service.UserService;
import com.claim.kidsstore.service.impl.UserServiceImpl;
import com.claim.kidsstore.validation.DataValidation;


@Controller
@SessionAttributes("loggedInUser")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DataValidation dataValidation;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public LoginController(UserServiceImpl userService, DataValidation dataValidation, UserRepository userRepository ) {
		super();
		// TODO Auto-generated constructor stub
		this.userService = userService;
		this.dataValidation = dataValidation;
		this.userRepository = userRepository;
		
	}

	@GetMapping("profile")
	public String profile(Model model) {
		model.addAttribute("msg", "Welcome Back");
		return "profile";
	}

	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("msg", "login");
		return "login";
	}

	@GetMapping("users")
	public String user(Model model) {
		model.addAttribute("msg", "All Users");
		model.addAttribute("alldb", userService.findAll());
		return "users";
	}

	@PostMapping("login")
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
		
		// Initialize a user
		User user = userRepository.findByEmail(email);
		
		if(user != null && password.equals(user.getPassword())) {
			model.addAttribute("msg", "Welcome" + user.getFirstName() + " " + user.getLastName());
			model.addAttribute("loggedInUser", user);
		}else {
			model.addAttribute("error", "Invalid credentials");
			return "login";
		}
		
		return "profile";
	}

	@GetMapping("logout")
	public String logout(Model model, WebRequest request, SessionStatus status, RedirectAttributes redirect) {
		status.setComplete();
		request.removeAttribute("loggedInUser", WebRequest.SCOPE_SESSION);
		redirect.addFlashAttribute("msg", "You have been signed out");
		return "redirect:/login";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("msg", "Register");
		model.addAttribute("hidden", "");
		model.addAttribute("action", "register");
		model.addAttribute("User", new User());
		return "register";
	}
	
	@PostMapping("register")
	public String register(@ModelAttribute User user, Model model, BindingResult result, RedirectAttributes redirect) {
		try {
			dataValidation.validateUpdate(user, result);
			if (result.hasErrors()) {
				model.addAttribute("error", "Required Fields");
				model.addAttribute("hidden", "");
				model.addAttribute("action", "register");
				return "register";
			}
			userService.save(user);
			redirect.addFlashAttribute("success", "User" + user.getFirstName());
		} catch (Exception e) {
			e.getSuppressed();
			}
			return "redirect:/users";
	}
	

	@GetMapping("updateUser")
	public String updateUser(@RequestParam long id, Model model) {
		userService.findById(id);
		model.addAttribute("msg", "Update");
		model.addAttribute("users", userService.findById(id));
		model.addAttribute("hidden", "hidden");
		model.addAttribute("action", "updateUser");
		return "register";
	}
	
	// @PostMapping("profile")
	public String update (@ModelAttribute User user, Model model, BindingResult result) {
		
		dataValidation.validateUpdate(user, result);
		
		if (result.hasErrors()) {
			model.addAttribute("msg", "Error");
			model.addAttribute("error", "Required Fields");
			model.addAttribute("hidden", "hidden");
			model.addAttribute("action", "updateUser");
			return "register";
		}
		
		try {
			User user1 = userRepository.findByEmail(user.getEmail());
			user1.setFirstName(user.getFirstName());
			user1.setLastName(user.getLastName());
			user1.setPhone(user.getPhone());
			userService.save(user1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user";
	}
	
	public String deleteUser(@RequestParam long id, RedirectAttributes redirect) {
		userService.delete(id);
		redirect.addFlashAttribute("success", "Deleted Successfully");
		
		return "redirect:/user";
	}
	
	@PostMapping("search")
	public String search(@RequestParam String name, Model model) {
		model.addAttribute("msg", userService.findByName(name).size() + "Found");
		model.addAttribute("alldb", userService.findByName(name));
		
		return "user";
	}
	
	@ModelAttribute("user")
	User user() {
		return new User();
	}
	
}				
			
			
		