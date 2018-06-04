package com.wojciechowski.project.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojciechowski.project.entity.CodeSnippet;

@Repository
public class CodeSnippetDAOImpl implements CodeSnippetDAO {

	private static final int LIMIT_RESULTS_PER_PAGE = 1;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CodeSnippet> getCodeSnippets(String username, int page) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from CodeSnippet where username = :username");
		query.setFirstResult((page-1) * LIMIT_RESULTS_PER_PAGE); 
		query.setMaxResults(LIMIT_RESULTS_PER_PAGE);
		List<CodeSnippet> codeSnippets = query.setParameter("username", username).getResultList();
		return codeSnippets;
	}

	@Override
	public void saveCodeSnippet(CodeSnippet theCodeSnippet) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCodeSnippet);
	}

	@Override
	public CodeSnippet getCodeSnippet(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		// retrieve/read from database using the primary key
		CodeSnippet theCodeSnippet = currentSession.get(CodeSnippet.class, theId);
		return theCodeSnippet;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from CodeSnippet where id=:snippetId");
				theQuery.setParameter("snippetId", theId);
		theQuery.executeUpdate();	
	}

}
