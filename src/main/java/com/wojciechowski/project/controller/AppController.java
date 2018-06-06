package com.wojciechowski.project.controller;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
	public String showMainPage(@RequestParam(value = "page", required = false) Integer page,
				Model theModel, Principal thePrincipal) {
        String theUserName = thePrincipal.getName();
		List<CodeSnippet> snippets = codeSnippetService.getCodeSnippets(theUserName);
		PagedListHolder<CodeSnippet> codeSnippets = new PagedListHolder<>(snippets);
		codeSnippets.setPageSize(4);
		theModel.addAttribute("maxPages", codeSnippets.getPageCount());
		if(page == null || page < 1 || page > codeSnippets.getPageCount()){
			page=1;
		}
		theModel.addAttribute("page", page);
	    if(page == null || page < 1 || page > codeSnippets.getPageCount()){
	    	codeSnippets.setPage(0);
	        theModel.addAttribute("codeSnippets", codeSnippets.getPageList());
	    }
	    else if(page <= codeSnippets.getPageCount()) {
	    	codeSnippets.setPage(page-1);
	        theModel.addAttribute("codeSnippets", codeSnippets.getPageList());
	    }
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
		CodeSnippet theCodeSnippet = new CodeSnippet();
		theModel.addAttribute("newCodeSnippet", theCodeSnippet);
		return "add-new-snippet";
	}
	
	@PostMapping("/saveCodeSnippet")
	public String saveCodeSnippet(@Valid @ModelAttribute("newCodeSnippet") CodeSnippet theCodeSnippet,
			BindingResult theBindingResult, Principal thePrincipal) {
		if(theBindingResult.hasErrors()) {
			return "add-new-snippet";
		}
        String theUserName = thePrincipal.getName();
        theCodeSnippet.setUsername(theUserName);
		codeSnippetService.saveCodeSnippet(theCodeSnippet);	
		return "redirect:/snippet-added-confirmation";
	}
	
	@GetMapping("/show-details")
	public String showMore(@RequestParam("snippetId") int theId,
			Model theModel) {
		CodeSnippet theCodeSnippet = codeSnippetService.getCodeSnippet(theId);
		theModel.addAttribute("codeSnippet", theCodeSnippet);
		return "snippet-details";
	}

	@GetMapping("/show-edit-form")
	public String showEditForm(@RequestParam("snippetId") int theId,
			Model theModel) {
		CodeSnippet theCodeSnippet = codeSnippetService.getCodeSnippet(theId);
		theModel.addAttribute("newCodeSnippet", theCodeSnippet);
		return "edit-snippet";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("snippetId") int theId,
			Model theModel) {
		codeSnippetService.deleteCustomer(theId);
		return "redirect:/main";
	}
	
	@GetMapping("/snippet-added-confirmation")
	public String showSnippetAddedConfirmation() {
		return "snippet-added-confirmation";
	}
	
}
