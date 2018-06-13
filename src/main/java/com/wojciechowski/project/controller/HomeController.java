package com.wojciechowski.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/more-info")
	public String showMoreInfoPage() {
		return "more-info";
	}
	
	@GetMapping("/about-me")
	public String showAboutMePage() {
		return "about-me";
	}
	
}
