package com.wojciechowski.project.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.wojciechowski.project")
@PropertySource({"classpath:persistence-mysql.properties",
				"classpath:security-persistence-mysql.properties"})
public class AppConfig implements WebMvcConfigurer{
	
	//variable to hold the properties
	@Autowired
	private Environment env;

	// set up logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
			
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
			
		return viewResolver;	
	}
	
	// bean for dataSource
	@Bean
	public DataSource myDataSource() {
		
			// create connection pool
			ComboPooledDataSource myDataSource = new ComboPooledDataSource();
			
			// set the jdbc driver
			try {
				myDataSource.setDriverClass("com.mysql.jdbc.Driver");
			}
			catch (PropertyVetoException exc) {
				throw new RuntimeException(exc);
			}
			
			// for sanity's sake log url and user
			logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
			
			// set database connection props
			myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			myDataSource.setUser(env.getProperty("jdbc.user"));
			myDataSource.setPassword(env.getProperty("jdbc.password"));
			
			// set connection pool props
			myDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			myDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			myDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			myDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
			
			return myDataSource;
	}
	
	//method handles the Hibernate properties
	private Properties getHibernateProperties() {
		
		//set hibernate properties
		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;
	}
	
	// method creates the Hibernate session factory based on the datasource 
	// and configuration properties
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	
	//bean for security datasource
	@Bean
	public DataSource securityDataSource() {
		
				//create connection pool
				ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
				
				//set the jdbc driver
				try {
					securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
				} catch (PropertyVetoException exc) {
					throw new RuntimeException(exc);
				}
				
				// log the connection props
				// just to make sure we are really reading data from properties file
				logger.info(">>> jdbc.url=" + env.getProperty("security.jdbc.url"));
				logger.info(">>> jdbc.user=" + env.getProperty("security.jdbc.user"));
				
				//set database connection props
				securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
				securityDataSource.setUser(env.getProperty("security.jdbc.user"));
				securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
				
				//set connection pool props
				securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.initialPoolSize"));
				securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.minPoolSize"));
				securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.maxPoolSize"));
				securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.maxIdleTime"));
				
				return securityDataSource;
	}
	
	
	// this method configures the Hibernate transaction manager
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	// resource handler because app will use static web resources (css, images, etc)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**")
					.addResourceLocations("/resources/");
	}

	// helper method to read environment property and convert to int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal= Integer.parseInt(propVal);
		return intPropVal;
	}
	
}
