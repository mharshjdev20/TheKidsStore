package com.claim.kidsstore.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.UserRepository;
import com.claim.kidsstore.utils.WebUtils;

@Controller
@SessionAttributes("loggedInUser")
public class UploadController {

	@Autowired
	WebUtils webUtils;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/addimages")
	public String addImages(@RequestParam("file") MultipartFile file, @RequestParam long id, Model model) {
		
		Pattern ext = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg))$)");

		try {
			if(file != null && file.isEmpty()) {
				model.addAttribute("error", "Error: No File Selected");
				return "redirect: profile";
			}
			
			if (file.getSize() > 1073741824) {
				model.addAttribute("error", "File size" + file.getSize() + "KB exceed ax allowed, try another photo");
				return "redirect: profile";
			}

			Matcher match = ext.matcher(file.getOriginalFilename());

			if (!match.matches()) {
				model.addAttribute("error", "Invalid Image Type");
				return "redirect: profile";
			}

			// Save image
			webUtils.addProfilePhoto(file, id, "users");
			model.addAttribute("loggedInUser", userRepository.findById(id).get());
			model.addAttribute("msg", "Upload success" + file.getSize() + "KB");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "profile";
	}

	@PostMapping("/uploadMultipleFiles")
	public String uploadMulitpleFiles(RedirectAttributes redirect, @RequestParam long id,
			@RequestParam("uploadingFiles") MultipartFile[] files) {

		try {
			webUtils.multipleSave(files, id);
			redirect.addFlashAttribute("success", "Images addes size" + files.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/profile";

	}

	public String removeImage(RedirectAttributes redirect, @RequestParam long id, @RequestParam String image,
			Model model) {

		try {
			webUtils.removeFiles(id, image);
			redirect.addFlashAttribute("success", "Image deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/profile";
	}

	@ModelAttribute("user")
	User user() {
		return new User();
	}

}