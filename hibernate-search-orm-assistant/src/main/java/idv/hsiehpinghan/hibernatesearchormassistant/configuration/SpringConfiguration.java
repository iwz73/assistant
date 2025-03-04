package idv.hsiehpinghan.hibernatesearchormassistant.configuration;

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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration("hibernateSearchOrmAssistantSpringConfiguration")
@PropertySource("classpath:/hibernate_search_orm_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.hibernatesearchormassistant" })
public class SpringConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		String driverClass = environment.getRequiredProperty("mysql.driverClass");
		String jdbcUrl = environment.getRequiredProperty("mysql.jdbcUrl");
		String user = environment.getRequiredProperty("mysql.user");
		String password = environment.getRequiredProperty("mysql.password");
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl(jdbcUrl);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		return comboPooledDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { environment.getRequiredProperty("hibernate.packagesToScan") });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.generate_statistics", environment.getRequiredProperty("hibernate.generate_statistics"));
		prop.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
		prop.put("hibernate.connection.isolation", environment.getRequiredProperty("hibernate.connection.isolation"));
		prop.put("hibernate.jdbc.fetch_size", environment.getRequiredProperty("hibernate.jdbc.fetch_size"));
		prop.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		prop.put("hibernate.search.default.directory_provider",
				environment.getRequiredProperty("hibernate.search.default.directory_provider"));
		prop.put(
				"hibernate.search.idv.hsiehpinghan.hibernatesearchormassistant.entity.RamDirectoryProviderEntity.directory_provider",
				environment.getRequiredProperty(
						"hibernate.search.idv.hsiehpinghan.hibernatesearchormassistant.entity.RamDirectoryProviderEntity.directory_provider"));
		prop.put("hibernate.search.default.indexBase",
				environment.getRequiredProperty("hibernate.search.default.indexBase"));
		return prop;
	}

}