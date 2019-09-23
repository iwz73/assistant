package idv.hsiehpinghan.jooqassistant.configuration;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import idv.hsiehpinghan.jooqassistant.listener.MyExecuteListener;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/jooq_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.jooqassistant" })
public class SpringConfiguration {
	@Value("${sqlDialect}")
	private String sqlDialect;
	@Value("${driverClass}")
	private String driverClass;
	@Value("${userName}")
	private String userName;
	@Value("${password}")
	private String password;
	@Value("${url}")
	private String url;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(userName);
		comboPooledDataSource.setPassword(password);
		return comboPooledDataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}

	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSourceProxy(DataSource dataSource) {
		TransactionAwareDataSourceProxy TransactionAwareDataSourceProxy = new TransactionAwareDataSourceProxy(
				dataSource);
		return TransactionAwareDataSourceProxy;
	}

	@Bean
	public ConnectionProvider connectionProvider(TransactionAwareDataSourceProxy transactionAwareDataSourceProxy) {
		DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(
				transactionAwareDataSourceProxy);
		return connectionProvider;
	}

	@Bean
	public org.jooq.Configuration jooqConfiguration(ConnectionProvider connectionProvider) {
		DefaultConfiguration configuration = new DefaultConfiguration();
		configuration.setSQLDialect(SQLDialect.valueOf(sqlDialect));
		configuration.setConnectionProvider(connectionProvider);
		MyExecuteListener myExecuteListener = new MyExecuteListener();
		DefaultExecuteListenerProvider executeListenerProvider = new DefaultExecuteListenerProvider(myExecuteListener);
		configuration.setExecuteListenerProvider(executeListenerProvider);
		return configuration;
	}

	@Bean
	public DSLContext dslContext(org.jooq.Configuration jooqConfiguration) {
		return new DefaultDSLContext(jooqConfiguration);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
