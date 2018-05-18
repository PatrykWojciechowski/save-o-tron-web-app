package com.wojciechowski.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginPage")
	public String showMyLoginPage() {
		return "plain-login";
	}
	
	@GetMapping("/access-denied")
	public String showMyAccessDeniedPage() {
		return "access-denied";
	}
}
