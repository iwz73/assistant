package idv.hsiehpinghan.springdatajpaassistatnt.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableTransactionManagement
@Configuration("springDataJpaAssistatntSpringConfiguration")
@PropertySource("classpath:/spring_data_jpa_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springdatajpaassistatnt" })
@EnableJpaRepositories(basePackages = { "idv.hsiehpinghan.springdatajpaassistatnt.repository" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

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
	public PlatformTransactionManager transactionManager()
			throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
			throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(environment
				.getRequiredProperty("jsp.packagesToScan"));
		Properties jpaProperties = new Properties();
		jpaProperties
				.put("hibernate.default_schema", environment
						.getRequiredProperty("jpa.hibernate.default_schema"));
		jpaProperties.put("hibernate.dialect",
				environment.getRequiredProperty("jpa.hibernate.dialect"));
		jpaProperties.put("hibernate.format_sql",
				environment.getRequiredProperty("jpa.hibernate.format_sql"));
		jpaProperties.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("jpa.hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.show_sql",
				environment.getRequiredProperty("jpa.hibernate.show_sql"));
		jpaProperties.put("hibernate.generate_statistics", environment
				.getRequiredProperty("jpa.hibernate.generate_statistics"));
		jpaProperties.put("hibernate.use_sql_comments", environment
				.getRequiredProperty("jpa.hibernate.use_sql_comments"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}