package com.claim.kidsstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.claim.kidsstore.model.Product;
import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.ProductRepository;
import com.claim.kidsstore.service.impl.ProductServiceImpl;
import com.claim.kidsstore.service.impl.UserServiceImpl;
import com.claim.kidsstore.utils.WebUtils;


@Controller
@SessionAttributes("loggedInUser")
public class AppController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private WebUtils webUtils;
	
	@Autowired
	private ProductRepository productRepository;

	public AppController(UserServiceImpl userService, WebUtils webUtils) {
		super();
		this.userService = userService;
		this.webUtils = webUtils;
	}

	@GetMapping("home")
	public String index(Model model) {
		model.addAttribute("message", "Hello");
		model.addAttribute("all", userService.findAll());
		return "index";
	}

	@GetMapping("about")
	public String about(Model model) {
		model.addAttribute("about", "This is the about page");
		return "about";
	}

	@GetMapping("services")
	public String services(Model model) {
		model.addAttribute("services", "These are our services");
		return "services";
	}
	
	@GetMapping("upload")
	public String product(Model model) {
		model.addAttribute("product", new Product());
		return "upload";

	}

	@PostMapping("upload")
	public String addProduct(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		// model.addAttribute("allProducts", productRepository.findAll());

		return "index";

	}

	@GetMapping("product")
	public String getProduct(@ModelAttribute Product product, Model model) {
		model.addAttribute("msg", "All Products");
		model.addAttribute("allProducts", productRepository.findAll());
		return "product";
	}

	@GetMapping("updateProduct")
	public String updateProduct(Model model, @RequestParam long id) {
		System.out.println("VALUE=" + id);
		productService.findById(id);
		model.addAttribute("msg", "Update");
		model.addAttribute("product", productService.findById(id));
		model.addAttribute("hidden", "hidden");
		model.addAttribute("action", "updateProduct");
		model.addAttribute("id", id);
		return "update";

	}

	@PostMapping("update")
	public String update(Model model, @ModelAttribute Product product) {
		System.out.println("VALUE=" + product.getId());
		Product update = productRepository.findById(product.getId()).get();
		update.setCategory(product.getCategory());
		update.setDescription(product.getDescription());
		update.setInStock(product.getInStock());
		update.setPrice(product.getPrice());
		update.setProductName(product.getProductName());
		productRepository.save(update);

		return "redirect:/product";
	}

	@GetMapping("deleteProduct")
	public String deleteProduct(Model model, @RequestParam long id) {
		productRepository.deleteById(id);

		return "redirect:/product";
	}

	public String name(@RequestParam String id, Model model) {
		index(model);
		model.addAttribute("myname", id);
		return "index";
	}
	
	// Send email method
	@PostMapping("services")
	public String sendEmail(Model model, @RequestParam String email, @RequestParam String name,
			@RequestParam String subject, @RequestParam String message) {
		webUtils.sendMail("mharshjev@gmail.com", message, subject + " " + name);
		model.addAttribute("action", "sendEmail");
		model.addAttribute("message", "Email Sent!");
		return "services";
	}
	
	@ModelAttribute("user")
	User user() {
		return new User();
	}
	
}