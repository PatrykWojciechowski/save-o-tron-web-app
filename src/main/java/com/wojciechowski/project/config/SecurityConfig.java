package com.wojciechowski.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//a reference to our security data source
	@Autowired
	private DataSource securityDataSource;
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();	
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		return jdbcUserDetailsManager;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
		/**
		Test authentication
        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER");
		**/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/main/**").hasRole("USER")
		.antMatchers("/add-new-snippet").hasRole("USER")
		.antMatchers("/saveCodeSnippet").hasRole("USER")
		.antMatchers("/show-details").hasRole("USER")
		.antMatchers("/show-edit-form").hasRole("USER")
		.antMatchers("/snippet-added-confirmation").hasRole("USER")
		.antMatchers("/delete").hasRole("USER")
		.and()
		.formLogin().loginPage("/loginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
}
