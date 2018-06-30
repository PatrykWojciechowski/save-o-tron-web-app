package com.wojciechowski.project.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wojciechowski.project.dao.RoleDAO;
import com.wojciechowski.project.dao.UserDAO;
import com.wojciechowski.project.user.Role;
import com.wojciechowski.project.user.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	private RoleDAO roleDao;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDAO userDao, RoleDAO roleDao, BCryptPasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public void save(User user) {
		
		User userToSave = new User();

		userToSave.setUsername(user.getUsername());
		userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
		userToSave.setFirstName(user.getFirstName());
		userToSave.setLastName(user.getLastName());
		userToSave.setEmail(user.getEmail());

		userToSave.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

		userDao.save(userToSave);
	}

}
