package com.wojciechowski.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojciechowski.project.user.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RoleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role findRoleByName(String theRoleName) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
				
		Role theRole = null;
				
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}	
		return theRole;
	}

}
