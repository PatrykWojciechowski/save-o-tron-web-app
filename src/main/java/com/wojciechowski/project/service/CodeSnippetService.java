package com.wojciechowski.project.service;

import java.util.List;

import com.wojciechowski.project.entity.CodeSnippet;

public interface CodeSnippetService {

	List<CodeSnippet> getCodeSnippets(String theUserName, int page);
	void saveCodeSnippet(CodeSnippet theCodeSnippet);
	CodeSnippet getCodeSnippet(int theId);
	void deleteCustomer(int theId);

}
