package com.wojciechowski.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wojciechowski.project.dao.CodeSnippetDAO;
import com.wojciechowski.project.entity.CodeSnippet;

@Service
public class CodeSnippetServiceImpl implements CodeSnippetService {

	@Autowired
	private CodeSnippetDAO codeSnippetDAO;
	
	@Transactional
	@Override
	public List<CodeSnippet> getCodeSnippets(String username, int page) {
		return codeSnippetDAO.getCodeSnippets(username, page);
	}
	
	@Transactional
	@Override
	public void saveCodeSnippet(CodeSnippet theCodeSnippet) {
		codeSnippetDAO.saveCodeSnippet(theCodeSnippet);
	}

	@Transactional
	@Override
	public CodeSnippet getCodeSnippet(int theId) {
		return codeSnippetDAO.getCodeSnippet(theId);
	}

	@Transactional
	@Override
	public void deleteCustomer(int theId) {
		codeSnippetDAO.deleteCustomer(theId);
	}

}
