package com.wojciechowski.project.dao;

import java.util.List;

import com.wojciechowski.project.entity.CodeSnippet;

public interface CodeSnippetDAO {
	List<CodeSnippet> getCodeSnippets(Long user_id);
	void saveCodeSnippet(CodeSnippet theCodeSnippet);
	CodeSnippet getCodeSnippet(int theId);
	void deleteCustomer(int theId);
}
