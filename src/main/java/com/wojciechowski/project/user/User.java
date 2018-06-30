package com.wojciechowski.project.user;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.wojciechowski.project.entity.CodeSnippet;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message="Username is required.")
	@Length(min=5, max =20, message="Your unsername should be between 5 and 20 characters.")
	@Pattern(regexp="^[a-z A-Z 0-9]{5,20}", 
		message="Name must contain only letters and numbers.")
	@Column(name = "username")
	private String username;
	
	@NotNull(message="Password is required.")
	@Length(min=5, max=80, message="Your password should be between 5 and 20 characters.")
	@Column(name = "password")
	private String password;
	
	@NotNull(message = "First name is required.")
	@Size(min = 5, max=50, message = "Your first name should be between 5 and 50 characters.")
	@Pattern(regexp="^[a-z A-Z]{5,50}", 
	message="First name must contain only letters.")
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "Last name is required.")
	@Size(min = 5, max=50, message = "Your first name should be between 5 and 50 characters.")
	@Pattern(regexp="^[a-z A-Z 0-9]{5,50}", 
	message="Last name must contain only letters.")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Email is required")
	@Column(name = "email")
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", 
	message="Provided email is not valid.")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@OneToMany(mappedBy="user")
	private Collection<CodeSnippet> snippets;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<CodeSnippet> getSnippets() {
		return snippets;
	}

	public void setSnippets(Collection<CodeSnippet> snippets) {
		this.snippets = snippets;
	}
	
}