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

	private SessionFactory sessionFactory;
	
	@Autowired
	public CodeSnippetDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<CodeSnippet> getCodeSnippets(Long user_id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from CodeSnippet where user_id = :user_id order by updateTime desc");
		List<CodeSnippet> codeSnippets = query.setParameter("user_id", user_id).getResultList();
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
