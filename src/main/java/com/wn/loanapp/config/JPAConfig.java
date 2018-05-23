package com.wn.loanapp.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@EnableTransactionManagement
@EnableJpaRepositories("com.wn.loanapp.repository")
@PropertySource("classpath:database.properties")
public class JPAConfig {
	
	@Autowired
    private Environment env;
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getDataSource());
		lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
		lcemfb.setPackagesToScan("com.wn.loanapp.model");
		lcemfb.setJpaProperties(jpaProperties());
		return lcemfb;
	}
	
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}
	
    @Bean
    //@Bean(destroyMethod = "")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
	    dataSource.setUrl(env.getProperty("database.url"));
	    dataSource.setUsername(env.getProperty("database.username"));
	    dataSource.setPassword(env.getProperty("database.password"));
	    return dataSource;
	}
    
	@Bean(name="transactionManager")
	public PlatformTransactionManager txManager(){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
				getEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
        return properties;        
   }	
}
