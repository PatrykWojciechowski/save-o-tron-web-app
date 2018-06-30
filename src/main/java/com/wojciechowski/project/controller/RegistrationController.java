package com.wojciechowski.project.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojciechowski.project.service.UserService;
import com.wojciechowski.project.user.User;


@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserService userService;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
    public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/show-registration-form")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("newUser", new User());
		return "registration-form";
	}

	@PostMapping("/process-registration-form")
	public String processRegistrationForm(
			@Valid @ModelAttribute("newUser") User theNewUser,
			BindingResult theBindingResult,
			Model theModel) {
		
		String username = theNewUser.getUsername();
		logger.info("Processing registration form for: " + username);
		
		if(theBindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User existing = userService.findByUserName(username);
		if (existing != null){
			theModel.addAttribute("newUser", new User());
			theModel.addAttribute("userAlreadyExistsError",
					"User with this username already exists.");
			
			logger.warning("User name already exists.");
			
			return "registration-form";
		}
	
		userService.save(theNewUser);
        
        logger.info("Successfully created user: " + username);
		
		return "registration-confirmation";
	}

}
