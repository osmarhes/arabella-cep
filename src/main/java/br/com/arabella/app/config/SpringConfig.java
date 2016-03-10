package br.com.arabella.app.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"br.com.arabella.app"})
@EnableJpaRepositories(basePackages={"br.com.arabella.app.repository"})
@EnableTransactionManagement
public class SpringConfig implements DisposableBean {
	BasicDataSource dataSource;
	
	@Bean(name="mySqlDataSource")
	public DataSource mySqlDataSource(){
		
		DataSource dataSource = null;
		JndiTemplate jndiTemplate = new JndiTemplate();
		try {
			dataSource = (DataSource) jndiTemplate.lookup("java:jboss/datasources/SVCEPDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return dataSource;
		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		
		lcemfb.setDataSource(this.mySqlDataSource());
		lcemfb.setPackagesToScan(new String[]{"br.com.arabella.app"});
		
		lcemfb.setPersistenceUnitName("SVCEPDS");
		
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);
		
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		
		lcemfb.setJpaProperties(props);
		
		lcemfb.afterPropertiesSet();
		
		return lcemfb;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return tm;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	public void destroy(){
		if(this.dataSource != null){
			try {
				this.dataSource.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			};
		}
	}
}
