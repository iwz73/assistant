package idv.hsiehpinghan.springbatchassistant.configuration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableBatchProcessing
@Import({ BatchTestSpringConfiguration.class })
@Configuration("springBatchAssistantSpringConfiguration")
@PropertySource("classpath:/spring_batch_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springbatchassistant" })
public class SpringConfiguration {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private DataSource dataSource;
	// @EnableBatchProcessing instance.
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private JobBuilderFactory jobBuilders;
	@Autowired
	private StepBuilderFactory stepBuilders;

	@PostConstruct
	protected void postConstruct() throws ScriptException, SQLException {
		Connection connection = dataSource.getConnection();
		Resource schemaDropPostgresqlSql = resourceLoader
				.getResource("classpath:/org/springframework/batch/core/schema-drop-postgresql.sql");
		ScriptUtils.executeSqlScript(connection, schemaDropPostgresqlSql);
		Resource schemaPostgresqlSql = resourceLoader
				.getResource("classpath:/org/springframework/batch/core/schema-postgresql.sql");
		ScriptUtils.executeSqlScript(connection, schemaPostgresqlSql);
	}

	@Bean
	public DataSource dataSource(Environment environment)
			throws PropertyVetoException {
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

//	@Bean
//	public JobExplorer jobExplorer(DataSource dataSource) throws Exception {
//		JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
//		jobExplorerFactoryBean.setDataSource(dataSource);
//		jobExplorerFactoryBean.afterPropertiesSet();
//		return jobExplorerFactoryBean.getObject();
//	}
//
//	@Bean
//	public JobRegistry jobRegistry() {
//		JobRegistry jobRegistry = new MapJobRegistry();
//		return jobRegistry;
//	}
//
//	@Bean
//	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(
//			JobRegistry jobRegistry) {
//		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
//		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
//		return jobRegistryBeanPostProcessor;
//	}
//
//	@Bean
//	public JobOperator jobOperator(JobExplorer jobExplorer,
//			JobRegistry jobRegistry) {
//		SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
//		simpleJobOperator.setJobExplorer(jobExplorer);
//		simpleJobOperator.setJobRepository(jobRepository);
//		simpleJobOperator.setJobRegistry(jobRegistry);
//		simpleJobOperator.setJobLauncher(jobLauncher);
//		return simpleJobOperator;
//	}
}