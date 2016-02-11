package idv.hsiehpinghan.springjdbcassistant.configuration;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration("springJdbcAssistantSpringConfiguration")
@PropertySource("classpath:/spring_jdbc_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springjdbcassistant" })
public class SpringConfiguration {

	@Autowired
	private Environment environment;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private DataSource dataSource;

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
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@PostConstruct
	protected void postConstruct() throws ScriptException, SQLException {
		Resource resource = resourceLoader
				.getResource("classpath:/script/postgresql.sql");
		ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
	}
}