package idv.hsiehpinghan.springsecurityassistant.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@PropertySource("classpath:/spring_security_assistant.property")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment environment;
	@Autowired
	private UserDetailsService userDetailsService;

//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeRequests()
//			.antMatchers("/common").permitAll()
//			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.and().formLogin().loginProcessingUrl("/loginProcessingUrl").loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username").passwordParameter("password")
//			.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/common/logoutPage")
//			.permitAll();
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
			.antMatchers("/common").permitAll()
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and().formLogin().loginProcessingUrl("/loginProcessingUrl").loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username").passwordParameter("password")
			.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/common/logoutPage")
			.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
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
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
			throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { environment
				.getRequiredProperty("hibernate.packagesToScan") });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
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
		prop.put("hibernate.connection.isolation", environment
				.getRequiredProperty("hibernate.connection.isolation"));
		prop.put("hibernate.jdbc.fetch_size",
				environment.getRequiredProperty("hibernate.jdbc.fetch_size"));
		prop.put("hibernate.jdbc.batch_size",
				environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		return prop;
	}

}
