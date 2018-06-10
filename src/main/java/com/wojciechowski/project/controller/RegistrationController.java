package com.wojciechowski.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojciechowski.project.user.NormalUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserDetailsManager userDetailsManager;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	public RegistrationController(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("newUser", new NormalUser());
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("newUser") NormalUser theNewUser,
			BindingResult theBindingResult,
			Model theModel) {
		
		// form validation, to make sure the user doesn't enter any invalid data
		if(theBindingResult.hasErrors()) {
			return "registration-form";
		}
		
		//  check the database if user already exists
		if(doesUserExist(theNewUser.getUserName())) {
			theModel.addAttribute("newUser", new NormalUser());
			theModel.addAttribute("userAlreadyExistsError",
					"User with this username already exists.");
			return "registration-form";
		}
	
		// encrypt the password, we make use of the BCrypt password encoder created earlier
		String encodedPassword = passwordEncoder.encode(theNewUser.getPassword());
		
		// prepend the encoding algorithm id
		encodedPassword = "{bcrypt}" + encodedPassword;
		
		// give user default role of "user"
		List<GrantedAuthority> authorities =
				AuthorityUtils.createAuthorityList("ROLE_USER");
		
		// create user details object
		User tempUser = new User(theNewUser.getUserName(), encodedPassword, authorities);
		
		// save user in the database
		userDetailsManager.createUser(tempUser);
		
		return "registration-confirmation";
	}
	
	//	Used in the form validation process. Here we add support to trim empty strings to null.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	//	This helper method makes use of the userDetailsManager bean that was @Autowired
	//	earlier in this RegistrationController.
	private boolean doesUserExist(String userName) {
		
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		return exists;
	}

}
