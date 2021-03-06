package com.wojciechowski.project.service;

import java.util.List;

import com.wojciechowski.project.entity.CodeSnippet;

public interface CodeSnippetService {
	List<CodeSnippet> getCodeSnippets(Long user_id);
	void saveCodeSnippet(CodeSnippet theCodeSnippet);
	CodeSnippet getCodeSnippet(int theId);
	void deleteCustomer(int theId);
}
