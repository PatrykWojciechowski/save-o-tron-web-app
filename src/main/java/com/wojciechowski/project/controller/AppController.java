package com.wojciechowski.project.controller;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wojciechowski.project.entity.CodeSnippet;
import com.wojciechowski.project.service.CodeSnippetService;

@Controller
public class AppController {

	@Autowired
	private CodeSnippetService codeSnippetService;
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/main")
	public String showMainPage(@RequestParam(value = "page", required = false) int page,
				Model theModel, Principal thePrincipal) {
		int startPage = (page - 2 > 0)?(page - 2):1;
	    int endPage = startPage + 4;
	    theModel.addAttribute("startpage", startPage);
	    theModel.addAttribute("endpage", endPage);
        String theUserName = thePrincipal.getName();
		List<CodeSnippet> codeSnippets = codeSnippetService.getCodeSnippets(theUserName, page);
		theModel.addAttribute("codeSnippets", codeSnippets);
		return "main";
	}
	
	@GetMapping("/moreInfo")
	public String showMoreInfoPage() {
		return "more-info";
	}
	
	@GetMapping("/aboutMe")
	public String showAboutMePage() {
		return "about-me";
	}
	
	@GetMapping("/add-new-snippet")
	public String showAddNewSnippet(Model theModel){
		
		// create model attribute to bind form data
		CodeSnippet theCodeSnippet = new CodeSnippet();
		
		theModel.addAttribute("newCodeSnippet", theCodeSnippet);
		
		return "add-new-snippet";
	}
	
	@PostMapping("/saveCodeSnippet")
	public String saveCodeSnippet(@Valid @ModelAttribute("newCodeSnippet") CodeSnippet theCodeSnippet,
			BindingResult theBindingResult,
			Principal thePrincipal) {
		
		if(theBindingResult.hasErrors()) {
			return "add-new-snippet";
		}
		
        String theUserName = thePrincipal.getName();
		
        theCodeSnippet.setUsername(theUserName);
        
		// save the code snippet using our service
		codeSnippetService.saveCodeSnippet(theCodeSnippet);	
		
		return "redirect:/main";
	}
	
	@GetMapping("/show-details")
	public String showMore(@RequestParam("snippetId") int theId,
									Model theModel) {
		
		// get the code snippet from our service using id
		CodeSnippet theCodeSnippet = codeSnippetService.getCodeSnippet(theId);
		
		// set code snippet as a model attribute to show details
		theModel.addAttribute("codeSnippet", theCodeSnippet);
			
		return "snippet-details";
	}

	@GetMapping("/show-edit-form")
	public String showEditForm(@RequestParam("snippetId") int theId,
			Model theModel) {
		
		// get the code snippet from our service using id
		CodeSnippet theCodeSnippet = codeSnippetService.getCodeSnippet(theId);
				
		// set code snippet as a model attribute to show details
		theModel.addAttribute("newCodeSnippet", theCodeSnippet);
		
		return "edit-snippet";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("snippetId") int theId,
			Model theModel) {
		
		// delete the customer
		codeSnippetService.deleteCustomer(theId);
		
		return "redirect:/main";
	}
	
}
