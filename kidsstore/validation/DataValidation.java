package com.claim.kidsstore.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.UserRepository;

@Component
public class DataValidation implements Validator {

	@Autowired
	public UserRepository userRepository;
	
	String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	String phoneRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		
		  if(userRepository.findByLastName(user.getLastName()) != null) {
		  errors.rejectValue("lastName", "size.user.lastName"); }
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if (userRepository.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "size.user.unique");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() <=0 || user.getPassword().length() < 4) {
			errors.rejectValue("password", "size.user.password");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatpass", "NotEmpty");
		if (!user.getRepeatPass().equals(user.getPassword())) {
			errors.rejectValue("repeatpass", "match.user.repeatpass");
		}
		
		if(!user.getEmail().matches(emailRegex)) {
			errors.rejectValue("email", "size.user.email");
		}
		
		if (!user.getPhone().matches(phoneRegex)) {
			errors.rejectValue("phone", "size.user.phone");
		}
		
	}
	
	public void validateUpdate(Object o, Errors errors) {
		User user = (User) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		if (user.getLastName().length() <=0 || user.getLastName().length() < 4) {
			errors.rejectValue("lastName", "size.user.lastName");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() <=0 || user.getPassword().length() < 4) {
			errors.rejectValue("password", "size.user.password");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty");
		if (!user.getPhone().matches(phoneRegex)) {
			errors.rejectValue("phone", "size.user.phone");
		}
	}
}
