package idv.hsiehpinghan.querydsljpaassistant.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableTransactionManagement
@Configuration("queryAssistantSpringConfiguration")
@PropertySource("classpath:/querydsl_jpa_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.querydsljpaassistant" })
@EnableJpaRepositories(basePackages = { "idv.hsiehpinghan.querydsljpaassistant.repository" })
public class SpringConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		String driverClass = environment
				.getRequiredProperty("postgresql.driverClass");
		String jdbcUrl = environment.getRequiredProperty("postgresql.jdbcUrl");
		String user = environment.getRequiredProperty("postgresql.user");
		String password = environment
				.getRequiredProperty("postgresql.password");

		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl(jdbcUrl);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		return comboPooledDataSource;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(
			LocalContainerEntityManagerFactoryBean entityManagerFactory)
			throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory
				.getObject());
		return transactionManager;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

//	@Bean
//	public LocalSessionFactoryBean sessionFactory()
//			throws PropertyVetoException {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] { environment
//				.getRequiredProperty("hibernate.packagesToScan") });
//		sessionFactory.setHibernateProperties(getHibernateProperties());
//		return sessionFactory;
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
			throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(environment
				.getRequiredProperty("hibernate.packagesToScan"));
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.default_schema",
				environment.getRequiredProperty("hibernate.default_schema"));
		prop.put("hibernate.dialect",
				environment.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.format_sql",
				environment.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		prop.put("hibernate.show_sql",
				environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.generate_statistics", environment
				.getRequiredProperty("hibernate.generate_statistics"));
		prop.put("hibernate.use_sql_comments",
				environment.getRequiredProperty("hibernate.use_sql_comments"));
		return prop;
	}
}