package com.wojciechowski.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojciechowski.project.user.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User findByUsername(String theUsername) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery = currentSession.createQuery("from User where username=:uname", User.class);
		theQuery.setParameter("uname", theUsername);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		return theUser;
	}

	@Override
	public void save(User theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
	}

}
